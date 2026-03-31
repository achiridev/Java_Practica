const API_BASE = '/api';

const api = {
    async request(endpoint, options = {}) {
        const url = `${API_BASE}${endpoint}`;
        const config = {
            headers: {
                'Content-Type': 'application/json',
                ...options.headers
            },
            ...options
        };

        if (options.body && typeof options.body === 'object') {
            config.body = JSON.stringify(options.body);
        }

        try {
            const response = await fetch(url, config);
            const data = await response.json().catch(() => null);

            if (!response.ok) {
                throw { status: response.status, message: data?.message || 'Error desconocido' };
            }

            return data;
        } catch (error) {
            console.error('API Error:', error);
            throw error;
        }
    },

    cliente: {
        async crear(data) {
            return api.request('/cliente', { method: 'POST', body: data });
        },

        async login(data) {
            return api.request('/cliente/login', { method: 'POST', body: data });
        }
    },

    producto: {
        async crear(data) {
            return api.request('/producto', { method: 'POST', body: data });
        },

        async listar(page = 0, size = 100) {
            return api.request(`/productos?page=${page}&size=${size}`);
        }
    },

    pedido: {
        async crear(data) {
            return api.request('/pedido', { method: 'POST', body: data });
        },

        async listar(page = 0, size = 10) {
            return api.request(`/pedidos?page=${page}&size=${size}`);
        },

        async buscarPorCliente(clienteId) {
            return api.request(`/pedidos/${clienteId}`);
        },

        async topMonto() {
            return api.request('/pedidos/TopMonto');
        }
    }
};
