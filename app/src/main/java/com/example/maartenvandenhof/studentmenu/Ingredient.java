package com.example.maartenvandenhof.studentmenu;

import java.util.ArrayList;

public class Ingredient {
    private String name;
    private double price;
    private String description;
    private ArrayList<String> allergies;


    public Ingredient(){

    }

    public Ingredient(String name, double price) {
        this.name = name;
        this.price = price;
        this.allergies = new ArrayList<>();
    }
    public Ingredient(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.allergies = new ArrayList<>();
        setDescription(description);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addAllergy(String allergy){ this.allergies.add(allergy); }

    public ArrayList<String> getAllergies(){ return this.allergies; }

    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

}
