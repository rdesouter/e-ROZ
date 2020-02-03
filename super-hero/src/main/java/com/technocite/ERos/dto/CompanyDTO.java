package com.technocite.ERos.dto;

public class CompanyDTO {

    private int id;
    private String name;
    private double assets;
    private String town;

    public CompanyDTO(){

    }

    public CompanyDTO(int id, String name, double assets, String town) {
        this.id = id;
        this.name = name;
        this.assets = assets;
        this.town = town;
    }

    public CompanyDTO(String name, double assets, String town) {
        this.name = name;
        this.assets = assets;
        this.town = town;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAssets() {
        return assets;
    }

    public void setAssets(double assets) {
        this.assets = assets;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
