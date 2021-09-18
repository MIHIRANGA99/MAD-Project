package com.example.woofnew;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class DogRVModel implements Parcelable {
    private String dogName;
    private String dogAge;
    private String dogGender;
    private String dogBreed;
    private String dogWeight;
    private String dogID;

    public DogRVModel(){

    }

    public DogRVModel(String dogName, String dogAge, String dogGender, String dogBreed, String dogWeight, String dogID) {
        this.dogName = dogName;
        this.dogAge = dogAge;
        this.dogGender = dogGender;
        this.dogBreed = dogBreed;
        this.dogWeight = dogWeight;
        this.dogID = dogID;
    }

    protected DogRVModel(Parcel in) {
        dogName = in.readString();
        dogAge = in.readString();
        dogGender = in.readString();
        dogBreed = in.readString();
        dogWeight = in.readString();
        dogID = in.readString();
    }

    public static final Creator<DogRVModel> CREATOR = new Creator<DogRVModel>() {
        @Override
        public DogRVModel createFromParcel(Parcel in) {
            return new DogRVModel(in);
        }

        @Override
        public DogRVModel[] newArray(int size) {
            return new DogRVModel[size];
        }
    };

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogAge() {
        return dogAge;
    }

    public void setDogAge(String dogAge) {
        this.dogAge = dogAge;
    }

    public String getDogGender() {
        return dogGender;
    }

    public void setDogGender(String dogGender) {
        this.dogGender = dogGender;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public String getDogWeight() {
        return dogWeight;
    }

    public void setDogWeight(String dogWeight) {
        this.dogWeight = dogWeight;
    }

    public String getDogID() {
        return dogID;
    }

    public void setDogID(String dogID) {
        this.dogID = dogID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dogName);
        parcel.writeString(dogAge);
        parcel.writeString(dogGender);
        parcel.writeString(dogBreed);
        parcel.writeString(dogWeight);
        parcel.writeString(dogID);
    }
}
