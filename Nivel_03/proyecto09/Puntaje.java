package proyecto09;

import java.util.Objects;

public class Puntaje {
    private final Integer valor;
    private String jugador;

    public Puntaje(Integer valor, String jugador) {
        this.valor = valor;
        this.jugador = jugador;
    }

    public Integer getValor() { return valor; }
    public String getJugador() { return jugador; }
    public void setJugador(String jugador) { this.jugador = jugador; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Puntaje { Valor: ").append(valor)
                .append(", Jugador: ").append(jugador)
                .append(" }");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puntaje p = (Puntaje) o;
        return valor.equals(p.getValor());
    }
    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
