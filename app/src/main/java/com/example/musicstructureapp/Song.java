package com.example.musicstructureapp;

class Song {
    /**
     * Song name
     */
    private String mSongName;
    /**
     * Song Id
     */

    private long mSongId;
    /**
     * Song Artist Name
     */

    private String mArtistName;
    /**
     * Song Duration
     */
    private int duration;

    /**
     * Get Song Name
     */
    String getmSongName() {
        return mSongName;
    }

    /**
     * Set Song Name
     */
    void setmSongName(String mSongName) {
        this.mSongName = mSongName;
    }

    /**
     * Get Artist Name
     */
    String getmArtistName() {

        return mArtistName;
    }

    /**
     * Set Artist Name
     */
    void setmArtistName(String mArtistName) {
        this.mArtistName = mArtistName;
    }

    /**
     * Get Song Id
     */
    long getmSongId() {
        return mSongId;
    }

    /**
     * Set Song Id
     */
    void setmSongId(int mSongId) {
        this.mSongId = mSongId;
    }

    /**
     * Get Song Duration
     */
    int getDuration() {
        return duration;
    }

    /**
     * Set Song Duration
     */
    void setDuration(int duration) {
        this.duration = duration;
    }


}
