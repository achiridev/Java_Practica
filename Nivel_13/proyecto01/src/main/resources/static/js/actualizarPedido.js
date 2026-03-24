const inputIdPut = document.querySelector(".inputIdPut");
const inputEstadoPut = document.querySelector(".inputEstadoPut");

const editarPedidoBtn = document.querySelector(".editarPedido");
const mensajeEdicion = document.querySelector(".mensajeEdicion");

editarPedidoBtn.addEventListener("click", async (e) => {
    e.preventDefault();
    try {
        const idPedido = inputIdPut.value.trim();
        const nuevoEstado = inputEstadoPut.value.trim().toUpperCase();

        if (idPedido === '' || nuevoEstado === '') {
            alert('Por favor, ingresa un ID de pedido y un estado válidos.');
            return;
        }

        const response = await fetch(`/api/pedidos/${idPedido}?estado=${nuevoEstado}`, {
            method: 'PATCH',
        });

        if (!response.ok) {
            throw new Error('Error al actualizar el pedido');
        }

        mensajeEdicion.textContent = `Pedido con ID ${idPedido} actualizado a estado: ${nuevoEstado}`;
        mensajeEdicion.classList.add('activo');

        inputIdPut.value = '';
        inputEstadoPut.value = '';
    } catch (error) {
        alert(error.message);
    }
});