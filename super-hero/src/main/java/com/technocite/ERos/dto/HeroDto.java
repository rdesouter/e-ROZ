package com.technocite.ERos.dto;

import com.technocite.ERos.model.Status;

import java.util.Objects;

public class HeroDto {

    private int id;
    private String name;
    private String ability;
    private String description;
    private Status status;
    private Integer strength;
    private Double balance;
    private Double deathInsurrance;
    private int cost;

    public HeroDto() {
    }

    public HeroDto(String name, String ability, String description, Status status, Integer strength, Double balance, Double deathInsurrance) {
        this.name = name;
        this.ability = ability;
        this.description = description;
        this.status = status;
        this.strength = strength;
        this.balance = balance;
        this.deathInsurrance = deathInsurrance;
    }

    public HeroDto(int id, String name, String ability, String description, Status status, Integer strength, Double balance, Double deathInsurrance, int cost) {
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


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbility() {
        return ability;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getStrength() {
        return strength;
    }

    public Double getBalance() {
        return balance;
    }

    public Double getDeathInsurrance() {
        return deathInsurrance;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "HeroesDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ability='" + ability + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", strenght=" + strength +
                ", balance=" + balance +
                ", deathInsurrance=" + deathInsurrance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeroDto)) return false;
        HeroDto heroDto = (HeroDto) o;
        return id==(heroDto.id) &&
                name.equals(heroDto.name) &&
                ability.equals(heroDto.ability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ability);
    }
}
