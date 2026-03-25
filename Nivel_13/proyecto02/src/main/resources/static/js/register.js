async function registerUser(formData) {
    const button = document.getElementById('btnRegister');
    showLoading(button);
    hideError();
    
    try {
        const response = await fetch(API_BASE_URL + '/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                nombre: formData.nombre,
                email: formData.email,
                password: formData.password,
                edad: parseInt(formData.edad)
            })
        });
        
        const data = await handleResponse(response);
        displayUser(data);
        
    } catch (error) {
        showError(error.mensaje || 'Error al registrar usuario', error);
    } finally {
        hideLoading(button);
    }
}

function setupRegisterForm() {
    const form = document.getElementById('registerForm');
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const formData = {
            nombre: document.getElementById('regNombre').value.trim(),
            email: document.getElementById('regEmail').value.trim(),
            password: document.getElementById('regPassword').value,
            edad: document.getElementById('regEdad').value
        };
        
        if (!formData.nombre || !formData.email || !formData.password || !formData.edad) {
            showError('Todos los campos son obligatorios');
            return;
        }
        
        const edad = parseInt(formData.edad);
        if (edad < 18 || edad > 100) {
            showError('La edad debe estar entre 18 y 100 años');
            return;
        }
        
        if (formData.password.length < 8 || formData.password.length > 20) {
            showError('La contraseña debe tener entre 8 y 20 caracteres');
            return;
        }
        
        registerUser(formData);
    });
}
