const inputIdDelete = document.querySelector(".inputIdDelete");

const eliminarPedidoBtn = document.querySelector(".eliminarPedido");
const mensajeEliminacion = document.querySelector(".mensajeEliminacion");

eliminarPedidoBtn.addEventListener("click", async (e) => {
    e.preventDefault();
    try {
        const idPedido = inputIdDelete.value.trim();

        if (idPedido === '') {
            alert('Por favor, ingresa un ID de pedido válido.');
            return;
        }

        const response = await fetch(`/api/pedidos/${idPedido}`, {
            method: 'DELETE',
        });

        if (!response.ok) {
            throw new Error('Error al eliminar el pedido');
        }

        mensajeEliminacion.textContent = `Pedido con ID ${idPedido} eliminado exitosamente.`;
        mensajeEliminacion.classList.add('activo');

        inputIdDelete.value = '';
    } catch (error) {
        alert(error.message);
    }
});
