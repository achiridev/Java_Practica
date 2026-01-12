package proyecto03;

import java.util.HashMap;

public class Cache<K,V> {
    private HashMap<K,V> mapa;

    public Cache() {
        this.mapa = new HashMap<>();
    }

    public void put(K clave, V valor) {
        this.mapa.put(clave, valor);
    }
    public V get(K clave) {
        return this.mapa.get(clave);
    }
    public boolean contieneClave(K claveBuscada) {
        for (K clave : mapa.keySet()) {
            if (clave == claveBuscada) return true;
        }
        return false;
    }
}
