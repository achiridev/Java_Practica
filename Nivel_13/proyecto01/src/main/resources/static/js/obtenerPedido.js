const bloque_tarjetas = document.querySelector('.tarjetas');

const inputIdGet = document.querySelector('.inputIdGet');
const buscarBtnGet = document.querySelector('.buscarPorId');

const inputEstadoGet = document.querySelector('.inputEstadoGet');
const inputPage = document.querySelector('.inputPage');
const inputSize = document.querySelector('.inputSize');
const buscarPorEstadoBtn = document.querySelector('.buscarPorEstado');

const limpiarBusquedaBtn = document.querySelector('.limpiarBusqueda');

buscarBtnGet.addEventListener('click', async (e) => {
    e.preventDefault();
    try {
        const idPedido = inputIdGet.value.trim();
        if (idPedido == '') {
            alert('Por favor, ingresa un ID de pedido válido.');
        }
        else {
            const response = await fetch(`/api/pedidos/${idPedido}`);
            if (!response.ok) {
                throw new Error('Pedido no encontrado');
            }
            const datosPedido = await response.json();

            const tarjeta = crearTarjetaPedido(datosPedido);
            bloque_tarjetas.appendChild(tarjeta);
        }
    } catch (error) {
        alert(error.message);
    }
});

buscarPorEstadoBtn.addEventListener('click', async (e) => {
    e.preventDefault();
    try {
        const estadoPedido = inputEstadoGet.value.trim().toUpperCase();
        const page = inputPage.value.trim() || 0;
        const size = inputSize.value.trim() || 5;
        if (estadoPedido == '') {
            alert('Por favor, ingresa un estado de pedido válido.');
        }
        else {
            const response = await fetch(`/api/pedidos?estado=${estadoPedido}&page=${page}&size=${size}`);
            if (!response.ok) {
                throw new Error('No se encontraron pedidos con ese estado');
            }
            const pedidos = await response.json();

            bloque_tarjetas.innerHTML = '';
            pedidos.forEach(pedido => {
                const tarjeta = crearTarjetaPedido(pedido);
                bloque_tarjetas.appendChild(tarjeta);
            });
        }
    } catch (error) {
        alert(error.message);
    }
});

const crearTarjetaPedido = (pedido) => {
    const tarjeta = document.createElement('div');
    tarjeta.classList.add("targeta_pedido");
    tarjeta.innerHTML = `
        <h2> Pedido #${pedido.id}</h2>
        <p>Cliente: ${pedido.cliente}</p>
        <p>Total: $${pedido.total.toFixed(2)}</p>
        <p>Estado: ${pedido.estado}</p>
    `;
    return tarjeta;
}

const limpiarBusqueda = () => {
    bloque_tarjetas.innerHTML = '';
    inputIdGet.value = '';
    inputEstadoGet.value = '';
    inputPage.value = '';
    inputSize.value = '';
}

limpiarBusquedaBtn.addEventListener('click', (e) => {
    e.preventDefault();
    limpiarBusqueda();
});
