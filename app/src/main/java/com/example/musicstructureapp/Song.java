package com.example.musicstructureapp;

class Song {
    /** Song name*/
    private String mSongName;

    /** Artist Name*/
    private String mArtistName;

    Song(String SongName, String ArtistName){
        mSongName = SongName;
        mArtistName = ArtistName;
    }

    String getSongName() {
        return mSongName;
    }

    String getArtistName() {
        return mArtistName;
    }
}
