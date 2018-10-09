package com.example.musicstructureapp;

public class Song {
    /** Song name*/
    private String mSongName;

    /** Artist Name*/
    private String mArtistName;

    public Song(String SongName,String ArtistName){
        mSongName = SongName;
        mArtistName = ArtistName;
    }

    public String getmSongName() {
        return mSongName;
    }

    public String getmArtistName() {
        return mArtistName;
    }
}
