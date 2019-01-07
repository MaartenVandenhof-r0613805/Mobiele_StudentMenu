package com.example.maartenvandenhof.studentmenu;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.graphics.TypefaceCompatUtil.getTempFile;

public class Menu {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private String description;
    private String recipe;
    private double price = 0;
    private int rating;
    private ImageView imageToUpload;
    private ArrayList<String> ingedrientsDataBase;

    public Menu(String name, ArrayList<Ingredient> ingredients, double price, String recipe, ImageView imageToUpload) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.imageToUpload = imageToUpload;
        setRecipe(recipe);
    }

    public Menu(String name, ArrayList<Ingredient> ingredients, double price) {

        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        setRecipe("N/A");
    }

    public Menu(String name, ArrayList<Ingredient> ingredients, String description) {

        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        setRecipe("N/A");
    }



    public Menu(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = getPrice();
        setRecipe("N/A \n \n \n Test Test Test");
    }







    public Menu( ArrayList<String> ingedrientsDataBase, String name, double price, String recipe, ImageView imageToUpload) {
        this.name = name;
        this.ingedrientsDataBase = ingedrientsDataBase;
        this.price = price;
        this.imageToUpload = imageToUpload;
        setRecipe(recipe);
    }

    public Menu(ArrayList<String> ingedrientsDataBase,String name, double price) {

        this.name = name;
        this.ingedrientsDataBase = ingedrientsDataBase;
        this.price = price;
        setRecipe("N/A");
    }

    public Menu(ArrayList<String> ingedrientsDataBase, String name,  String description) {

        this.name = name;
        this.ingedrientsDataBase = ingedrientsDataBase;
        this.description = description;
        setRecipe("N/A");
    }





    public Menu(){
        setRecipe("N/A \n \n \n Test Test Test");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredient() {


        return ingredients;
    }

    public ArrayList<String> getIngredientsString(){
        ArrayList<String> list = new ArrayList<>();
        for(Ingredient i:ingredients){
            list.add(i.getName());
        }
        return list;
    }
    public void setIngredient(ArrayList<String> ingedrientsDataBase)

    {
        this.ingedrientsDataBase = ingedrientsDataBase;
    }

    public void setIngredients(ArrayList<Ingredient> list){
        this.ingredients = list;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice(){
        if (this.price == 0){
            for(Ingredient i : ingredients){
                this.price = price + i.getPrice();
            }
        }
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ImageView getImageToUpload() {
        return imageToUpload;
    }

    public void setImageToUpload(ImageView imageToUpload) {
        this.imageToUpload = imageToUpload;
    }

    public ArrayList<String> getAllergies(){
        ArrayList<String> allergies = new ArrayList<>();

        for (Ingredient i:ingredients){
            allergies.addAll(i.getAllergies());
        }
        return allergies;
    }

}