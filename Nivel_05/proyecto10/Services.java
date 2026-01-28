package proyecto10;

public class Services {
    private static LogRotationService logRotationService;

    static {
        logRotationService = new LogRotationService();
    }
    
    public static LogRotationService getLogRotationService() {
        return logRotationService;
    }
}
