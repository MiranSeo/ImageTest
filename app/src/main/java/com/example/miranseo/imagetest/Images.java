package com.example.miranseo.imagetest;

/**
 * Created by Miran Seo on 15. 1. 21.
 */

public class Images {

    //private variables
    String _photo;

    // Empty constructor
    public Images(){

    }

    // constructor
    public Images(String photo){
        this._photo= photo;
    }


    // getting name
    public String getPhoto(){
        return this._photo;
    }

    // setting name
    public void setPhoto(String photo){
        this._photo = photo;
    }

}