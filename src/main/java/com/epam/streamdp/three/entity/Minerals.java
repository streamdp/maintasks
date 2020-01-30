package com.epam.streamdp.three.entity;

import com.epam.streamdp.three.enums.*;

import java.util.Objects;

public class Minerals {
    private CategoriesOfMineral categoriesOfMineral;
    private String name;
    private MineralColors color;
    private TypesOfShine shine;
    private TypeOfTransparency transparency;
    private double mohsScaleHardness;
    private double weight;
    private double price;

    public Minerals() {
    }

    public Minerals(CategoriesOfMineral categoriesOfMineral, String name, MineralColors color,
                    TypesOfShine shine, TypeOfTransparency transparency, double mohsScaleHardness, double weight,
                    double price) {
        this.categoriesOfMineral = categoriesOfMineral;
        this.name = name;
        this.color = color;
        this.shine = shine;
        this.transparency = transparency;
        this.mohsScaleHardness = mohsScaleHardness;
        this.weight = weight;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public double getCost() { return weight*price; }

    public double getWorth() {return price/weight;}

    public void setPrice(double price) {
        this.price = price;
    }

    public CategoriesOfMineral getCategoriesOfMineral() {
        return categoriesOfMineral;
    }

    public void setCategoriesOfMineral(CategoriesOfMineral categoriesOfMineral) {
        this.categoriesOfMineral = categoriesOfMineral;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MineralColors getColor() {
        return color;
    }

    public void setColor(MineralColors color) {
        this.color = color;
    }

    public TypesOfShine getShine() {
        return shine;
    }

    public void setShine(TypesOfShine shine) {
        this.shine = shine;
    }

    public double getMohsScaleHardness() {
        return mohsScaleHardness;
    }

    public void setMohsScaleHardness(double mohsScaleHardness) {
        this.mohsScaleHardness = mohsScaleHardness;
    }

    public TypeOfTransparency getTransparency() {
        return transparency;
    }

    public void setTransparency(TypeOfTransparency transparency) {
        this.transparency = transparency;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Minerals)) return false;
        Minerals minerals = (Minerals) o;
        return Double.compare(minerals.mohsScaleHardness, mohsScaleHardness) == 0 &&
                Double.compare(minerals.weight, weight) == 0 &&
                Double.compare(minerals.price, price) == 0 &&
                categoriesOfMineral == minerals.categoriesOfMineral &&
                name.equals(minerals.name) &&
                color == minerals.color &&
                shine == minerals.shine &&
                transparency == minerals.transparency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoriesOfMineral, name, color, shine, transparency, mohsScaleHardness, weight, price);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nMinerals{");
        sb.append("categoriesOfMineral=").append(categoriesOfMineral);
        sb.append(", name='").append(name).append('\'');
        sb.append(", color=").append(color);
        sb.append(", shine=").append(shine);
        sb.append(", transparency=").append(transparency);
        sb.append(", mohsScaleHardness=").append(mohsScaleHardness);
        sb.append(", weight=").append(weight);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    public static boolean isGem(Minerals mineral){
        boolean exists = true;
        try {
            GemsNames.valueOf(mineral.getName());
            if (mineral.getName().equals("AMBER") && mineral.getWeight() < 5000) {
                exists = false;
            }
        } catch (IllegalArgumentException e) {
            exists = false;
        }
        return exists;
    }

    public static boolean isSemiPreciousGem(Minerals mineral){
        boolean exists = true;
        try {
            SemiPreciousGemsNames.valueOf(mineral.getName());
        } catch (IllegalArgumentException e) {
            exists = false;
        }
        return exists;
    }
}
