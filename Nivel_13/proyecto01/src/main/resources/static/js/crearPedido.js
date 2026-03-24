const inputClientePost = document.querySelector('.inputClientePost');
const inputTotalPost = document.querySelector('.inputTotalPost');
const inputEstadoPost = document.querySelector('.inputEstadoPost');
const btnCrearPedido = document.querySelector('.crearPedido');
const mensajeCreacion = document.querySelector('.mensajeCreacion');

btnCrearPedido.addEventListener('click', async (e) => {
    e.preventDefault();
    try {
        const cliente = inputClientePost.value.trim();
        const total = parseFloat(inputTotalPost.value.trim());
        const estado = inputEstadoPost.value.trim().toUpperCase();

        if (cliente === '' || isNaN(total) || estado === '') {
            alert('Por favor, completa todos los campos correctamente.');
            return;
        }

        const nuevoPedido = { cliente, total, estado };

        const response = await fetch('/api/pedidos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuevoPedido)
        });

        if (!response.ok) {
            throw new Error('Error al crear el pedido');
        }

        const pedidoCreado = await response.json();
        mensajeCreacion.textContent = `Pedido creado con ID: ${pedidoCreado.id}`;
        mensajeCreacion.classList.add('activo');

        inputClientePost.value = '';
        inputTotalPost.value = '';
        inputEstadoPost.value = '';
    } catch (error) {
        alert(error.message);
    }
});
