package proyecto10;

public class Main {
    public static void main(String[] args) {
        System.out.println("Proyecto 10 - Conteo de visitas a páginas");
        VisitasWeb misVisitasWeb = new VisitasWeb();
        misVisitasWeb.registrarVisita("https://www.youtube.com/");
        misVisitasWeb.registrarVisita("https://github.com/");
        misVisitasWeb.registrarVisita("https://www.youtube.com/");
        misVisitasWeb.registrarVisita("https://music.youtube.com/");
        misVisitasWeb.registrarVisita("https://github.com/");
        misVisitasWeb.registrarVisita("https://chatgpt.com/");
        misVisitasWeb.registrarVisita("https://www.youtube.com/");

        misVisitasWeb.mostrarEstadisticas();
    }
}
