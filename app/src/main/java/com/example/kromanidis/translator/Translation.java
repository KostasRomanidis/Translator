package com.example.kromanidis.translator;

/**
 * Created by kromanidis on 12/3/2016.
 */
public class Translation {
    private String word;
    private int audioID;
    private int photoID;

    public Translation(String word, int audioID, int photoID) {
        this.word = word;
        this.audioID = audioID;
        this.photoID = photoID;
    }

    public String getText() {
        return word;
    }

    public int getAudioID() {
        return audioID;
    }

    public int getPhotoID() {
        return photoID;
    }
}
