const API_BASE_URL = '/api/auth';

function handleResponse(response) {
    if (response.ok) {
        if (response.status === 204) {
            return null;
        }
        return response.json();
    }
    
    return response.json().then(errorData => {
        throw {
            status: errorData.status || response.status,
            mensaje: errorData.mensaje || 'Error desconocido',
            timestamp: errorData.timestamp
        };
    });
}

function showError(message, details) {
    const errorDiv = document.getElementById('errorDisplay');
    const errorMessage = document.getElementById('errorMessage');
    const errorDetails = document.getElementById('errorDetails');
    
    errorDiv.classList.remove('hidden');
    errorMessage.textContent = message;
    
    if (details) {
        errorDetails.textContent = 'Código: ' + details.status + ' | Timestamp: ' + new Date().toLocaleString();
        errorDetails.classList.remove('hidden');
    } else {
        errorDetails.classList.add('hidden');
    }
}

function hideError() {
    const errorDiv = document.getElementById('errorDisplay');
    errorDiv.classList.add('hidden');
}

function showLoading(button) {
    button.classList.add('loading');
    button.disabled = true;
}

function hideLoading(button) {
    button.classList.remove('loading');
    button.disabled = false;
}

function displayUser(user) {
    const resultContent = document.getElementById('resultContent');
    const emptyState = document.getElementById('emptyState');
    const userInfo = document.getElementById('userInfo');
    const successBadge = document.getElementById('successBadge');
    
    emptyState.classList.add('hidden');
    userInfo.classList.remove('hidden');
    successBadge.classList.remove('hidden');
    successBadge.classList.remove('badge-error');
    successBadge.classList.add('badge-success');
    successBadge.textContent = 'Operación exitosa';
    
    document.getElementById('userId').textContent = user.id || '-';
    document.getElementById('userNombre').textContent = user.nombre || '-';
    document.getElementById('userEmail').textContent = user.email || '-';
    document.getElementById('userEdad').textContent = user.edad || '-';
    
    resultContent.classList.remove('hidden');
    hideError();
}

function showEmptyState() {
    const resultContent = document.getElementById('resultContent');
    const emptyState = document.getElementById('emptyState');
    const userInfo = document.getElementById('userInfo');
    
    resultContent.classList.remove('hidden');
    emptyState.classList.remove('hidden');
    userInfo.classList.add('hidden');
}

function showSuccess(message) {
    const resultContent = document.getElementById('resultContent');
    const emptyState = document.getElementById('emptyState');
    const userInfo = document.getElementById('userInfo');
    const successBadge = document.getElementById('successBadge');
    
    emptyState.classList.add('hidden');
    userInfo.classList.add('hidden');
    successBadge.textContent = message;
    successBadge.classList.remove('hidden');
    successBadge.classList.remove('badge-error');
    successBadge.classList.add('badge-success');
    
    resultContent.classList.remove('hidden');
    hideError();
}
