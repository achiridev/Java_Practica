package com.achiridev.Ejercicio03;

public class Metadata {
    private String ip;
    private String device;

    public Metadata() {}
    public Metadata(String ip, String device) {
        this.ip = ip;
        this.device = device;
    }

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getDevice() {
        return device;
    }
    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Metadata { IP : ").append(ip)
                .append(", Device : ").append(device)
                .append(" }");
        return sb.toString();
    }
}
