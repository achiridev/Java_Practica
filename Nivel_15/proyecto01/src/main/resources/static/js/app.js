const API_BASE = '/api';

const AuthService = {
    getToken() {
        return localStorage.getItem('jwt_token');
    },

    setToken(token) {
        localStorage.setItem('jwt_token', token);
    },

    removeToken() {
        localStorage.removeItem('jwt_token');
    },

    isAuthenticated() {
        return !!this.getToken();
    },

    async request(endpoint, method, body = null) {
        const headers = {
            'Content-Type': 'application/json'
        };

        const token = this.getToken();
        if (token) {
            headers['Authorization'] = `Bearer ${token}`;
        }

        const config = {
            method: method,
            headers: headers
        };

        if (body) {
            config.body = JSON.stringify(body);
        }

        const response = await fetch(`${API_BASE}${endpoint}`, config);
        return response;
    },

    async login(email, password) {
        const response = await this.request('/auth/login', 'POST', { email, password });
        
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || error.mensaje || 'Error en el login');
        }
        
        const data = await response.json();
        this.setToken(data.token);
        return data;
    },

    async register(name, email, password) {
        const response = await this.request('/auth/register', 'POST', { name, email, password });
        
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || error.mensaje || 'Error en el registro');
        }
        
        return response.json();
    },

    async getPerfil() {
        const response = await this.request('/user/perfil', 'GET');
        
        if (!response.ok) {
            throw new Error('Error al obtener perfil');
        }
        
        return response.json();
    },

    logout() {
        this.removeToken();
    }
};

const UIPages = {
    showPage(pageId) {
        document.querySelectorAll('.page').forEach(page => {
            page.classList.remove('active');
        });
        document.getElementById(pageId).classList.add('active');
        
        document.querySelectorAll('nav button').forEach(btn => {
            btn.classList.remove('active');
        });
        
        const navBtn = document.querySelector(`nav button[data-page="${pageId}"]`);
        if (navBtn) {
            navBtn.classList.add('active');
        }
    },

    updateNav() {
        const isAuth = AuthService.isAuthenticated();
        const btnLogin = document.getElementById('btn-login-nav');
        const btnRegister = document.getElementById('btn-register-nav');
        const btnLogout = document.getElementById('btn-logout-nav');
        const btnPerfil = document.getElementById('btn-perfil-nav');
        const btnTest = document.getElementById('btn-test-nav');

        if (isAuth) {
            btnLogin.classList.add('hidden');
            btnRegister.classList.add('hidden');
            btnLogout.classList.remove('hidden');
            btnPerfil.classList.remove('hidden');
            btnTest.classList.remove('hidden');
        } else {
            btnLogin.classList.remove('hidden');
            btnRegister.classList.remove('hidden');
            btnLogout.classList.add('hidden');
            btnPerfil.classList.add('hidden');
            btnTest.classList.add('hidden');
        }
    },

    showMessage(elementId, message, type = 'error') {
        const el = document.getElementById(elementId);
        el.className = `message ${type}`;
        el.textContent = message;
        el.classList.remove('hidden');
        
        if (type === 'success') {
            setTimeout(() => el.classList.add('hidden'), 5000);
        }
    },

    hideMessage(elementId) {
        const el = document.getElementById(elementId);
        el.classList.add('hidden');
    }
};

document.addEventListener('DOMContentLoaded', () => {
    UIPages.updateNav();

    if (AuthService.isAuthenticated()) {
        UIPages.showPage('page-perfil');
        setTimeout(() => document.getElementById('btn-load-perfil')?.click(), 500);
    }

    document.getElementById('btn-login-nav').addEventListener('click', () => {
        UIPages.showPage('page-login');
    });

    document.getElementById('btn-register-nav').addEventListener('click', () => {
        UIPages.showPage('page-register');
    });

    document.getElementById('btn-perfil-nav').addEventListener('click', () => {
        UIPages.showPage('page-perfil');
    });

    document.getElementById('btn-test-nav').addEventListener('click', () => {
        UIPages.showPage('page-test');
    });

    document.getElementById('btn-logout-nav').addEventListener('click', () => {
        AuthService.logout();
        UIPages.updateNav();
        UIPages.showPage('page-login');
    });

    document.getElementById('form-login').addEventListener('submit', async (e) => {
        e.preventDefault();
        UIPages.hideMessage('login-message');

        const email = document.getElementById('login-email').value;
        const password = document.getElementById('login-password').value;

        try {
            await AuthService.login(email, password);
            UIPages.showMessage('login-message', 'Login exitoso', 'success');
            UIPages.updateNav();
            UIPages.showPage('page-perfil');
            setTimeout(() => document.getElementById('btn-load-perfil')?.click(), 500);
        } catch (error) {
            UIPages.showMessage('login-message', error.message);
        }
    });

    document.getElementById('form-register').addEventListener('submit', async (e) => {
        e.preventDefault();
        UIPages.hideMessage('register-message');

        const name = document.getElementById('register-name').value;
        const email = document.getElementById('register-email').value;
        const password = document.getElementById('register-password').value;

        try {
            await AuthService.register(name, email, password);
            UIPages.showMessage('register-message', 'Registro exitoso. Ahora puedes iniciar sesión.', 'success');
            setTimeout(() => {
                UIPages.showPage('page-login');
            }, 2000);
        } catch (error) {
            UIPages.showMessage('register-message', error.message);
        }
    });

    document.getElementById('btn-load-perfil').addEventListener('click', async () => {
        const btn = document.getElementById('btn-load-perfil');
        btn.disabled = true;
        btn.textContent = 'Cargando...';

        try {
            const perfil = await AuthService.getPerfil();
            document.getElementById('perfil-email').textContent = perfil.email || 'N/A';
            
            const rolesContainer = document.getElementById('perfil-roles');
            rolesContainer.innerHTML = '';
            if (perfil.roles && perfil.roles.length > 0) {
                perfil.roles.forEach(rol => {
                    const badge = document.createElement('span');
                    badge.className = 'role-badge';
                    badge.textContent = rol.nombre || rol;
                    rolesContainer.appendChild(badge);
                });
            } else {
                rolesContainer.textContent = 'Sin roles';
            }

            document.getElementById('perfil-data').classList.remove('hidden');
            UIPages.hideMessage('perfil-message');
        } catch (error) {
            UIPages.showMessage('perfil-message', error.message);
            document.getElementById('perfil-data').classList.add('hidden');
        } finally {
            btn.disabled = false;
            btn.textContent = 'Cargar Perfil';
        }
    });

    document.getElementById('btn-public-info').addEventListener('click', async () => {
        const resultEl = document.getElementById('result-public-info');
        resultEl.className = 'test-result pending';
        resultEl.textContent = 'Probando...';

        try {
            const response = await fetch(`${API_BASE}/public/info`);
            const data = await response.text();
            
            if (response.ok) {
                resultEl.className = 'test-result success';
                resultEl.textContent = data;
            } else {
                resultEl.className = 'test-result error';
                resultEl.textContent = `Error: ${response.status} - ${data}`;
            }
        } catch (error) {
            resultEl.className = 'test-result error';
            resultEl.textContent = `Error: ${error.message}`;
        }
    });

    document.getElementById('btn-auth-login').addEventListener('click', async () => {
        const resultEl = document.getElementById('result-auth-login');
        resultEl.className = 'test-result pending';
        resultEl.textContent = 'Probando...';

        const email = document.getElementById('test-login-email').value;
        const password = document.getElementById('test-login-password').value;

        if (!email || !password) {
            resultEl.className = 'test-result error';
            resultEl.textContent = 'Por favor ingresa email y contraseña';
            return;
        }

        try {
            const response = await fetch(`${API_BASE}/auth/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                const data = await response.json();
                resultEl.className = 'test-result success';
                resultEl.textContent = `Token recibido: ${data.token.substring(0, 50)}...`;
            } else {
                const error = await response.text();
                resultEl.className = 'test-result error';
                resultEl.textContent = `Error: ${response.status} - ${error}`;
            }
        } catch (error) {
            resultEl.className = 'test-result error';
            resultEl.textContent = `Error: ${error.message}`;
        }
    });

    document.getElementById('btn-auth-register').addEventListener('click', async () => {
        const resultEl = document.getElementById('result-auth-register');
        resultEl.className = 'test-result pending';
        resultEl.textContent = 'Probando...';

        const name = document.getElementById('test-register-name').value;
        const email = document.getElementById('test-register-email').value;
        const password = document.getElementById('test-register-password').value;

        if (!name || !email || !password) {
            resultEl.className = 'test-result error';
            resultEl.textContent = 'Por favor completa todos los campos';
            return;
        }

        try {
            const response = await fetch(`${API_BASE}/auth/register`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name, email, password })
            });

            if (response.ok) {
                const data = await response.json();
                resultEl.className = 'test-result success';
                resultEl.textContent = `Usuario creado: ID=${data.id}, Nombre=${data.name}, Email=${data.email}`;
            } else {
                const error = await response.text();
                resultEl.className = 'test-result error';
                resultEl.textContent = `Error: ${response.status} - ${error}`;
            }
        } catch (error) {
            resultEl.className = 'test-result error';
            resultEl.textContent = `Error: ${error.message}`;
        }
    });

    document.getElementById('btn-user-perfil').addEventListener('click', async () => {
        const resultEl = document.getElementById('result-user-perfil');
        resultEl.className = 'test-result pending';
        resultEl.textContent = 'Probando...';

        const token = document.getElementById('test-perfil-token').value;

        if (!token) {
            resultEl.className = 'test-result error';
            resultEl.textContent = 'Por favor ingresa el token JWT';
            return;
        }

        try {
            const response = await fetch(`${API_BASE}/user/perfil`, {
                method: 'GET',
                headers: { 
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const data = await response.json();
                const rolesText = data.roles.map(r => r.nombre).join(', ');
                resultEl.className = 'test-result success';
                resultEl.textContent = `Email: ${data.email}\nRoles: ${rolesText}`;
            } else {
                const error = await response.text();
                resultEl.className = 'test-result error';
                resultEl.textContent = `Error: ${response.status} - ${error}`;
            }
        } catch (error) {
            resultEl.className = 'test-result error';
            resultEl.textContent = `Error: ${error.message}`;
        }
    });

    document.getElementById('btn-test-all').addEventListener('click', async () => {
        const endpoints = [
            { id: 'result-public-info', method: 'GET', path: '/public/info' },
            { id: 'result-auth-login', method: 'POST', path: '/auth/login', needsBody: true },
            { id: 'result-auth-register', method: 'POST', path: '/auth/register', needsBody: true },
            { id: 'result-user-perfil', method: 'GET', path: '/user/perfil', needsToken: true }
        ];

        const token = document.getElementById('test-perfil-token').value;

        for (const endpoint of endpoints) {
            const resultEl = document.getElementById(endpoint.id);
            resultEl.className = 'test-result pending';
            resultEl.textContent = 'Probando...';

            try {
                const options = {
                    method: endpoint.method,
                    headers: { 'Content-Type': 'application/json' }
                };

                if (endpoint.needsBody) {
                    if (endpoint.path === '/auth/login') {
                        const email = document.getElementById('test-login-email').value;
                        const password = document.getElementById('test-login-password').value;
                        if (email && password) {
                            options.body = JSON.stringify({ email, password });
                        } else {
                            resultEl.className = 'test-result error';
                            resultEl.textContent = 'Faltan credenciales de login';
                            continue;
                        }
                    } else if (endpoint.path === '/auth/register') {
                        const name = document.getElementById('test-register-name').value;
                        const email = document.getElementById('test-register-email').value;
                        const password = document.getElementById('test-register-password').value;
                        if (name && email && password) {
                            options.body = JSON.stringify({ name, email, password });
                        } else {
                            resultEl.className = 'test-result error';
                            resultEl.textContent = 'Faltan datos de registro';
                            continue;
                        }
                    }
                }

                if (endpoint.needsToken && token) {
                    options.headers['Authorization'] = `Bearer ${token}`;
                }

                const response = await fetch(`${API_BASE}${endpoint.path}`, options);

                if (response.ok) {
                    const data = endpoint.method === 'GET' ? await response.text() : await response.json();
                    resultEl.className = 'test-result success';
                    resultEl.textContent = typeof data === 'object' ? JSON.stringify(data, null, 2) : data;
                } else {
                    const error = await response.text();
                    resultEl.className = 'test-result error';
                    resultEl.textContent = `Error ${response.status}: ${error}`;
                }
            } catch (error) {
                resultEl.className = 'test-result error';
                resultEl.textContent = `Error: ${error.message}`;
            }
        }
    });
});