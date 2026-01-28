package proyecto09;

public class Central {
    private static Perfiles perfiles;
    static {
        perfiles = new Perfiles();
    }
    public static Perfiles getPerfiles() {
        return perfiles;
    }
}
