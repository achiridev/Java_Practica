const App = {
    session: {
        usuario: null,
        
        iniciar(datos) {
            this.usuario = datos;
            localStorage.setItem('usuario', JSON.stringify(datos));
        },
        
        cerrar() {
            this.usuario = null;
            localStorage.removeItem('usuario');
        },
        
        obtener() {
            if (!this.usuario) {
                const stored = localStorage.getItem('usuario');
                if (stored) this.usuario = JSON.parse(stored);
            }
            return this.usuario;
        },
        
        estaActiva() {
            return this.obtener() !== null;
        }
    },

    notificaciones: {
        container: null,
        
        init() {
            this.container = document.getElementById('notification-container');
            if (!this.container) {
                this.container = document.createElement('div');
                this.container.id = 'notification-container';
                this.container.className = 'notification-container';
                document.body.appendChild(this.container);
            }
        },
        
        show(message, type = 'info') {
            if (!this.container) this.init();
            
            const notification = document.createElement('div');
            notification.className = `notification ${type}`;
            
            const icons = {
                success: '✓',
                error: '✕',
                warning: '⚠',
                info: 'ℹ'
            };
            
            notification.innerHTML = `
                <span>${icons[type] || 'ℹ'}</span>
                <span>${message}</span>
                <button class="notification-close" onclick="this.parentElement.remove()">×</button>
            `;
            
            this.container.appendChild(notification);
            
            setTimeout(() => {
                notification.style.animation = 'slideInRight 0.3s reverse';
                setTimeout(() => notification.remove(), 300);
            }, 4000);
        },
        
        success(msg) { this.show(msg, 'success'); },
        error(msg) { this.show(msg, 'error'); },
        warning(msg) { this.show(msg, 'warning'); },
        info(msg) { this.show(msg, 'info'); }
    },

    tabs: {
        switch(tabId) {
            document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
            document.querySelectorAll('.tab-content').forEach(c => c.classList.remove('active'));
            
            document.querySelector(`.tab[data-tab="${tabId}"]`)?.classList.add('active');
            document.getElementById(tabId)?.classList.add('active');
        }
    },

    utils: {
        formToJSON(form) {
            const formData = new FormData(form);
            const data = {};
            for (let [key, value] of formData.entries()) {
                data[key] = value;
            }
            return data;
        },
        
        formatCurrency(amount) {
            return new Intl.NumberFormat('es-CO', { 
                style: 'currency', 
                currency: 'COP' 
            }).format(amount);
        },
        
        formatDate(dateStr) {
            const date = new Date(dateStr);
            return new Intl.DateTimeFormat('es-CO', {
                year: 'numeric',
                month: 'short',
                day: 'numeric',
                hour: '2-digit',
                minute: '2-digit'
            }).format(date);
        }
    }
};

document.addEventListener('DOMContentLoaded', () => {
    App.notificaciones.init();
});
