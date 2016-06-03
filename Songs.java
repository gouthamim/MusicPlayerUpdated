package com.example.android.musicplayer;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gouthami.m on 21/05/16.
 */
public class Songs {

    public static List<Song> get(Context context) {
        Resources res = context.getResources();
        String[] names = res.getStringArray(R.array.songName_array);

        String[] artists = res.getStringArray(R.array.artist_array);

        String[] length = res.getStringArray(R.array.length_array);

        String[] lyrics = res.getStringArray(R.array.lyrics_array);

        String[] images = res.getStringArray(R.array.image_array);

        String[] song = res.getStringArray(R.array.song_array);

        List<Song> songs = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            songs.add(new Song(names[i], artists[i], length[i], i, lyrics[i], images[i], song[i]));
        }
        return songs;
    }

    /**
     * Method to fetch the photo of the artist
     * @param context
     * @param itemName
     * @return
     */
    public static int getImageResId(Context context, String itemName) {
        return context.getResources().getIdentifier(itemName, "drawable", context.getPackageName());
    }

    /**
     * Method to fetch the song name
     * @param context
     * @param itemName
     * @return
     */
    public static int getNameResId(Context context, String itemName) {
        return context.getResources().getIdentifier(itemName, "raw", context.getPackageName());
    }
}
