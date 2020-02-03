package com.technocite.ERos.model;

import java.util.Objects;

public class Company {

    private int id;
    private String name;
    private double assets;
    private String town;

    public Company(){

    }

    public Company(int id, String name, double assets, String town) {
        this.id = id;
        this.name = name;
        this.assets = assets;
        this.town = town;
    }

    public Company(String name, double assets, String town) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id &&
                Double.compare(company.assets, assets) == 0 &&
                name.equals(company.name) &&
                town.equals(company.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, assets, town);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", assets=" + assets +
                ", town='" + town + '\'' +
                '}';
    }
}
