package com.example.android.musicplayer;

/**
 * Song
 *
 * @author gouthami.m
 */
public class Song {
    public final int position;
    public final String name;
    public final String artist;
    public final String length;
    public final String lyrics;
    public final String image;
    public final String song;

    public Song(String name, String artist, String length, int position, String lyrics, String image, String song) {
        this.position = position;
        this.name = name;
        this.artist = artist;
        this.length = length;
        this.lyrics = lyrics;
        this.image = image;
        this.song = song;
    }
}
