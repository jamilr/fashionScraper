package org.fscraper.helpers;

public enum ImgType {

    JPG("jpg"),
    PNG("png"),
    BMP("bmp");

    private String ext;

    ImgType(String ext) {
        this.ext = ext;
    }


    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}