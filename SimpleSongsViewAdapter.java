package com.example.android.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by gouthami.m on 19/05/16.
 */
public class SimpleSongsViewAdapter extends RecyclerView.Adapter<SimpleSongsViewAdapter.SongViewHolder> {


    private final List<Song> songs;
    private Context context;


    public SimpleSongsViewAdapter(Context context, List<Song> songs) {
        this.songs = songs;
        this.context = context;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_playlist_simple, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                Intent i = new Intent(v.getContext(), PlayerActivity.class);
                i.putExtra(PlayerActivity.POSITION, position);
                v.getContext().startActivity(i);
            }
        });
        return new SongViewHolder(view);

    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {

        holder.itemView.setTag(position);



        Song song = songs.get(position);
        holder.textViewSongName.setText(song.name);
        holder.textViewSongArtist.setText(song.artist);
        holder.textViewSongLength.setText(song.length);
        int i = Songs.getImageResId(context, song.image);
        if (i != -1) {
            holder.imageView.setImageResource(i);
        }
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewSongName;
        public TextView textViewSongArtist;
        public TextView textViewSongLength;
        public ImageView imageView;
        public SongViewHolder(View itemView) {
            super(itemView);

            textViewSongName = (TextView) itemView.findViewById(R.id.song_name);
            textViewSongArtist = (TextView) itemView.findViewById(R.id.song_artist);
            textViewSongLength = (TextView) itemView.findViewById(R.id.song_length);
            imageView = (ImageView) itemView.findViewById(R.id.album_image);
        }
    }
}
