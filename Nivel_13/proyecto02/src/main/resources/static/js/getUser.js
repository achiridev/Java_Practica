async function getUserById(id) {
    const button = document.getElementById('btnSearch');
    showLoading(button);
    hideError();
    
    try {
        const response = await fetch(API_BASE_URL + '/' + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        
        const data = await handleResponse(response);
        displayUser(data);
        
    } catch (error) {
        showError(error.mensaje || 'Error al buscar usuario', error);
    } finally {
        hideLoading(button);
    }
}

function setupGetUserForm() {
    const form = document.getElementById('searchForm');
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const id = document.getElementById('searchId').value.trim();
        
        if (!id) {
            showError('El ID es obligatorio');
            return;
        }
        
        const idNum = parseInt(id);
        if (isNaN(idNum) || idNum < 1) {
            showError('El ID debe ser un número válido');
            return;
        }
        
        getUserById(idNum);
    });
}
