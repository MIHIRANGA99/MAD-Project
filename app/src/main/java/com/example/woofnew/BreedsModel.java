package com.example.woofnew;

public class BreedsModel {

    String Name;
    String Image;
    String Details;

    public BreedsModel() {
    }

    public BreedsModel(String name, String image, String details) {
        Name = name;
        Image = image;
        Details = details;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
