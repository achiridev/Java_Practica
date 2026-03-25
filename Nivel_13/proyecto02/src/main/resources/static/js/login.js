async function loginUser(formData) {
    const button = document.getElementById('btnLogin');
    showLoading(button);
    hideError();
    
    try {
        const response = await fetch(API_BASE_URL + '/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: formData.email,
                password: formData.password
            })
        });
        
        const data = await handleResponse(response);
        displayUser(data);
        
    } catch (error) {
        showError(error.mensaje || 'Error al iniciar sesión', error);
    } finally {
        hideLoading(button);
    }
}

function setupLoginForm() {
    const form = document.getElementById('loginForm');
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const formData = {
            email: document.getElementById('loginEmail').value.trim(),
            password: document.getElementById('loginPassword').value
        };
        
        if (!formData.email || !formData.password) {
            showError('Todos los campos son obligatorios');
            return;
        }
        
        loginUser(formData);
    });
}
