package ru.melon_egoist.graph.classes;

public class GraphResponse {
    private String x;
    private String y;
    private String r;

    private String inArea;

    public GraphResponse(String x, String y, String r, String inArea) {
        this.x = x;
        this.y = y;
        this.r = r;

        this.inArea = inArea;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setR(String r) {
        this.r = r;
    }

    public void setInArea(String inArea) {
        this.inArea = inArea;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getR() {
        return r;
    }

    public String getInArea() {
        return inArea;
    }
}
