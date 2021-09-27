package com.example.woofnew;

public class Uploadpics {
    private String mName;
    private String mImageURL;

    public  Uploadpics(){
        //Empty constructor required
    }

    public Uploadpics(String mName, String mImageURL) {
        if(mName.trim().equals("")){
            mName = "";
        }

        this.mName = mName;
        this.mImageURL = mImageURL;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public void setmImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }
}
