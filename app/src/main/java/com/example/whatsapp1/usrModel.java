package com.example.whatsapp1;

public class usrModel {
    private String imguri;
    private String name;
    private String phnum;
    public usrModel(){

    }

    public usrModel(String imguri, String name, String phnum) {
        this.imguri = imguri;
        this.name = name;
        this.phnum = phnum;
    }

    public String getImguri() {
        return imguri;
    }

    public void setImguri(String imguri) {
        this.imguri = imguri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhnum() {
        return phnum;
    }

    public void setPhnum(String phnum) {
        this.phnum = phnum;
    }
}
