async function deleteUserById(id) {
    const button = document.getElementById('btnDelete');
    showLoading(button);
    hideError();
    
    try {
        const response = await fetch(API_BASE_URL + '/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        
        await handleResponse(response);
        showSuccess('Usuario eliminado correctamente');
        
    } catch (error) {
        showError(error.mensaje || 'Error al eliminar usuario', error);
    } finally {
        hideLoading(button);
    }
}

function setupDeleteUserForm() {
    const form = document.getElementById('deleteForm');
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const id = document.getElementById('deleteId').value.trim();
        
        if (!id) {
            showError('El ID es obligatorio');
            return;
        }
        
        const idNum = parseInt(id);
        if (isNaN(idNum) || idNum < 1) {
            showError('El ID debe ser un número válido');
            return;
        }
        
        if (!confirm('¿Está seguro de que desea eliminar este usuario?')) {
            return;
        }
        
        deleteUserById(idNum);
    });
}
