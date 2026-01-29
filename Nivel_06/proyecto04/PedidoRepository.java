package proyecto04;

import java.util.List;
import java.util.ArrayList;

public class PedidoRepository {
    private List<Pedido> listaPedidos;

    public PedidoRepository() {
        listaPedidos = new ArrayList<>();
    }
    public PedidoRepository(List<Pedido> listaInicial) {
        listaPedidos = new ArrayList<>(listaInicial);
    }

    public Pedido getPedidoByID(int id) {
        return listaPedidos.stream()
                .filter(pedido -> pedido.getId() == id)
                .findFirst()
                .orElseGet(() -> new Pedido(0, 0,0));
                // Esto para no implementar el Service
    }
}
