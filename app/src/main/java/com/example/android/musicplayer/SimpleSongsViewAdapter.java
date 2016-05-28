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
                // launch new activity
                // and pass information to new activity
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

        TextView textViewSongName = (TextView) holder.itemView.findViewById(R.id.song_name);
        TextView textViewSongArtist = (TextView) holder.itemView.findViewById(R.id.song_artist);
        TextView textViewSongLength = (TextView) holder.itemView.findViewById(R.id.song_length);

        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.album_image);

        Song song = songs.get(position);
        textViewSongName.setText(song.name);
        textViewSongArtist.setText(song.artist);
        textViewSongLength.setText(song.length);
        int i = Songs.getImageResId(context, song.image);
        if (i != -1) {
            imageView.setImageResource(i);
        }
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {

        public SongViewHolder(View itemView) {
            super(itemView);

        }
    }
}
