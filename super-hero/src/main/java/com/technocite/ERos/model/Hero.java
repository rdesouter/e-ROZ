package com.technocite.ERos.model;

import java.util.Objects;

public class Hero {
    private Integer id;
    private String name;
    private String ability;
    private String description;
    private Status status;
    private Integer strength;
    private Double balance;
    private Double deathInsurrance;
    private int cost;

    public Hero() {
    }

    public Hero(Integer id, String name, String ability, String description, Status status, Integer strength, Double balance, Double deathInsurrance, int cost) {
        this.id = id;
        this.name = name;
        this.ability = ability;
        this.description = description;
        this.status = status;
        this.strength = strength;
        this.balance = balance;
        this.deathInsurrance = deathInsurrance;
        this.cost = cost;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getDeathInsurrance() {
        return deathInsurrance;
    }

    public void setDeathInsurrance(Double deathInsurrance) {
        this.deathInsurrance = deathInsurrance;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return id.equals(hero.id) &&
                name.equals(hero.name) &&
                ability.equals(hero.ability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ability);
    }

    @Override
    public String toString() {
        return "Heroes{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ability='" + ability + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", strenght=" + strength +
                ", balance=" + balance +
                ", deathInsurrance=" + deathInsurrance +
                '}';
    }
}
