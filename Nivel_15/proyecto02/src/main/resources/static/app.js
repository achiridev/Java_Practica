const API_BASE = '/auth';
const API_PRODUCTOS = '/api';

let token = localStorage.getItem('token');
let userData = null;

document.addEventListener('DOMContentLoaded', () => {
    if (token) {
        showDashboard();
    } else {
        initAuth();
    }
});

function initAuth() {
    const tabs = document.querySelectorAll('.tab-btn');
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');
            
            if (tab.dataset.tab === 'login') {
                loginForm.classList.remove('hidden');
                registerForm.classList.add('hidden');
            } else {
                loginForm.classList.add('hidden');
                registerForm.classList.remove('hidden');
            }
        });
    });

    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;
        
        try {
            const response = await fetch(`${API_BASE}/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });
            
            if (!response.ok) {
                const error = await response.json();
                throw new Error(error.message || 'Error al iniciar sesión');
            }
            
            const data = await response.json();
            token = data.token;
            localStorage.setItem('token', token);
            showNotification('Sesión iniciada correctamente', 'success');
            showDashboard();
        } catch (error) {
            showNotification(error.message, 'error');
        }
    });

    registerForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const username = document.getElementById('registerUsername').value;
        const email = document.getElementById('registerEmail').value;
        const password = document.getElementById('registerPassword').value;
        
        try {
            const response = await fetch(`${API_BASE}/register`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, email, password })
            });
            
            if (!response.ok) {
                const error = await response.json();
                throw new Error(error.message || 'Error al registrar usuario');
            }
            
            showNotification('Usuario creado. Por favor inicia sesión.', 'success');
            document.querySelector('[data-tab="login"]').click();
            registerForm.reset();
        } catch (error) {
            showNotification(error.message, 'error');
        }
    });
}

function parseJwt(token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)).join(''));
    return JSON.parse(jsonPayload);
}

async function showDashboard() {
    const jwtData = parseJwt(token);
    userData = {
        username: jwtData.sub,
        roles: jwtData.roles
    };
    
    document.body.innerHTML = `
        <div class="container">
            <div class="dashboard">
                <div class="dashboard-header">
                    <h1>Proyecto02</h1>
                    <div class="user-info">
                        <span class="user-badge">${userData.username} (${userData.roles.join(', ')})</span>
                        <button class="btn-logout" onclick="logout()">Cerrar Sesión</button>
                    </div>
                </div>
                
                <div class="nav-tabs">
                    <button class="nav-btn active" data-view="productos">Productos</button>
                    <button class="nav-btn" data-view="compras">Mis Compras</button>
                </div>
                
                <div id="view-productos" class="view">
                    <div class="card">
                        <div class="card-header">
                            <h2>Lista de Productos</h2>
                            <button class="btn-primary" id="btn-crear">+ Nuevo Producto</button>
                        </div>
                        <div id="productos-container">
                            <div class="loading"><div class="spinner"></div></div>
                        </div>
                        <div class="pagination" id="pagination"></div>
                    </div>
                </div>
                
                <div id="view-compras" class="view hidden">
                    <div class="card">
                        <div class="card-header">
                            <h2>Mis Compras</h2>
                        </div>
                        <div id="compras-container">
                            <div class="loading"><div class="spinner"></div></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="notification" class="notification hidden"></div>
    `;
    
    initDashboard();
    loadProductos();
    loadCompras();
}

function initDashboard() {
    const navBtns = document.querySelectorAll('.nav-btn');
    const views = document.querySelectorAll('.view');
    const crearBtn = document.getElementById('btn-crear');
    
    const hasModRole = userData.roles.some(r => r === 'MODERATOR' || r === 'ADMIN');
    const hasAdminRole = userData.roles.includes('ADMIN');
    const hasUserRole = userData.roles.includes('USER');
    
    if (!hasModRole) {
        crearBtn.classList.add('hidden');
    }
    
    if (!hasUserRole && !hasModRole && !hasAdminRole) {
        showNotification('Sin los permisos requeridos', 'warning');
    }
    
    navBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            navBtns.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');
            
            views.forEach(v => v.classList.add('hidden'));
            document.getElementById(`view-${btn.dataset.view}`).classList.remove('hidden');
        });
    });
    
    crearBtn.addEventListener('click', () => showModalProducto());
}

let currentPage = 0;
const pageSize = 10;

async function loadProductos() {
    const container = document.getElementById('productos-container');
    container.innerHTML = '<div class="loading"><div class="spinner"></div></div>';
    
    try {
        const response = await fetch(`${API_PRODUCTOS}/public/productos?page=${currentPage}&size=${pageSize}`);
        
        if (!response.ok) throw new Error('Error al cargar productos');
        
        const data = await response.json();
        
        if (data.content.length === 0) {
            container.innerHTML = `
                <div class="empty-state">
                    <h3>No hay productos</h3>
                    <p>Crea un nuevo producto para comenzar</p>
                </div>
            `;
        } else {
            container.innerHTML = `
                <div class="product-grid">
                    ${data.content.map(p => `
                        <div class="product-card">
                            <h3>${p.nombre}</h3>
                            <div class="product-details">
                                <span class="product-price">$${p.precio}</span>
                                <span class="product-stock">Stock: ${p.stock}</span>
                            </div>
                            ${getProductActions(p)}
                        </div>
                    `).join('')}
                </div>
            `;
            
            setupPagination(data.totalPages, data.number);
        }
    } catch (error) {
        container.innerHTML = `<div class="empty-state"><h3>Error</h3><p>${error.message}</p></div>`;
    }
}

function getProductActions(producto) {
    const hasModRole = userData.roles.some(r => r === 'MODERATOR' || r === 'ADMIN');
    const hasAdminRole = userData.roles.includes('ADMIN');
    
    let actions = '';
    
    if (hasModRole) {
        actions += `<button class="btn-sm btn-edit" onclick="editProducto(${producto.id}, '${producto.nombre}', ${producto.precio}, ${producto.stock})">Editar</button>`;
    }
    
    if (hasAdminRole) {
        actions += `<button class="btn-sm btn-delete" onclick="deleteProducto(${producto.id})">Eliminar</button>`;
    }
    
    if (actions) {
        return `<div class="product-actions">${actions}</div>`;
    }
    
    return '';
}

function setupPagination(totalPages, current) {
    const pagination = document.getElementById('pagination');
    
    if (totalPages <= 1) {
        pagination.innerHTML = '';
        return;
    }
    
    let html = '';
    
    html += `<button ${current === 0 ? 'disabled' : ''} onclick="changePage(${current - 1})">←</button>`;
    
    for (let i = 0; i < totalPages; i++) {
        html += `<button class="${i === current ? 'active' : ''}" onclick="changePage(${i})">${i + 1}</button>`;
    }
    
    html += `<button ${current === totalPages - 1 ? 'disabled' : ''} onclick="changePage(${current + 1})">→</button>`;
    
    pagination.innerHTML = html;
}

function changePage(page) {
    currentPage = page;
    loadProductos();
}

window.editProducto = function(id, nombre, precio, stock) {
    showModalProducto(id, { nombre, precio, stock });
};

async function deleteProducto(id) {
    if (!confirm('¿Estás seguro de que deseas eliminar este producto?')) return;
    
    try {
        const response = await fetch(`${API_PRODUCTOS}/admin/productos/${id}`, {
            method: 'DELETE',
            headers: { 'Authorization': `Bearer ${token}` }
        });
        
        if (response.status === 403) {
            showNotification('Sin los permisos requeridos', 'warning');
            return;
        }
        
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || 'Error al eliminar');
        }
        
        showNotification('Producto eliminado correctamente', 'success');
        loadProductos();
    } catch (error) {
        showNotification(error.message, 'error');
    }
}

function showModalProducto(producto = null) {
    const isEdit = producto !== null && producto.id;
    const hasModRole = userData.roles.some(r => r === 'MODERATOR' || r === 'ADMIN');
    
    if (!hasModRole) {
        showNotification('Sin los permisos requeridos', 'warning');
        return;
    }
    
    const overlay = document.createElement('div');
    overlay.className = 'modal-overlay';
    overlay.innerHTML = `
        <div class="modal">
            <h2>${isEdit ? 'Editar' : 'Nuevo'} Producto</h2>
            <form id="producto-form">
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" required minlength="3" maxlength="100" value="${producto?.nombre || ''}">
                </div>
                <div class="form-group">
                    <label for="precio">Precio</label>
                    <input type="number" id="precio" step="0.01" min="0.01" required value="${producto?.precio || ''}">
                </div>
                <div class="form-group">
                    <label for="stock">Stock</label>
                    <input type="number" id="stock" min="0" required value="${producto?.stock || ''}">
                </div>
                <div class="modal-actions">
                    <button type="button" class="btn-secondary" onclick="closeModal()">Cancelar</button>
                    <button type="submit" class="btn-primary">${isEdit ? 'Actualizar' : 'Crear'}</button>
                </div>
            </form>
        </div>
    `;
    
    document.body.appendChild(overlay);
    
    overlay.addEventListener('click', (e) => {
        if (e.target === overlay) closeModal();
    });
    
    document.getElementById('producto-form').addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const nombre = document.getElementById('nombre').value;
        const precio = parseFloat(document.getElementById('precio').value);
        const stock = parseInt(document.getElementById('stock').value);
        
        const url = isEdit ? `${API_PRODUCTOS}/mod/productos/${producto.id}` : `${API_PRODUCTOS}/mod/productos`;
        const method = isEdit ? 'PUT' : 'POST';
        
        try {
            const response = await fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({ nombre, precio, stock })
            });
            
            if (response.status === 403) {
                showNotification('Sin los permisos requeridos', 'warning');
                closeModal();
                return;
            }
            
            if (!response.ok) {
                const error = await response.json();
                throw new Error(error.message || 'Error al guardar');
            }
            
            showNotification(isEdit ? 'Producto actualizado correctamente' : 'Producto creado correctamente', 'success');
            closeModal();
            loadProductos();
        } catch (error) {
            showNotification(error.message, 'error');
        }
    });
}

window.closeModal = function() {
    const overlay = document.querySelector('.modal-overlay');
    if (overlay) overlay.remove();
};

async function loadCompras() {
    const container = document.getElementById('compras-container');
    
    try {
        const response = await fetch(`${API_PRODUCTOS}/user/compras`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
        
        if (response.status === 403) {
            container.innerHTML = `
                <div class="compras-card">
                    <h3>Sin los permisos requeridos</h3>
                    <p>No tienes permiso para ver esta sección</p>
                </div>
            `;
            return;
        }
        
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || 'Error al cargar compras');
        }
        
        const data = await response.text();
        
        container.innerHTML = `
            <div class="compras-card">
                <h3>${data}</h3>
                <p>Esta es un endpoint de ejemplo para el rol USER</p>
            </div>
        `;
    } catch (error) {
        container.innerHTML = `
            <div class="compras-card">
                <h3>Error</h3>
                <p>${error.message}</p>
            </div>
        `;
    }
}

function showNotification(message, type = 'success') {
    let notification = document.getElementById('notification');
    
    if (!notification) {
        notification = document.createElement('div');
        notification.id = 'notification';
        document.body.appendChild(notification);
    }
    
    notification.className = `notification ${type}`;
    notification.textContent = message;
    notification.classList.remove('hidden');
    
    setTimeout(() => {
        notification.classList.add('hidden');
    }, 4000);
}

window.logout = function() {
    localStorage.removeItem('token');
    token = null;
    userData = null;
    location.reload();
};