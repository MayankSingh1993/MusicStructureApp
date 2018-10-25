package com.example.musicstructureapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    SongAdapter(Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Song currentSong = getItem(position);

        TextView songNameTextView = listItemView.findViewById(R.id.songNameTextView);

        songNameTextView.setText(currentSong.getSongName());

        TextView artistNameTextView = listItemView.findViewById(R.id.artistNameTextView);

        artistNameTextView.setText(currentSong.getArtistName());

        return listItemView;
    }


}