package com.example.android.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by gouthami.m on 20/05/16.
 */
public class PlayerActivity extends AppCompatActivity {

    public static final String POSITION = "position";
    SeekBar SeekBar;
    MediaPlayer mPlayer;
    boolean isStart;
    private int startTime = 0;
    private int currentTime = 0;
    private int finalTime;
    private int FORWARD_TIME = 5000;
    private int BACKWARD_TIME = 5000;
    Handler handler;
    int progressBarValue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);
        int position = getIntent().getIntExtra(POSITION, 0);
        Song song = Songs.get(getApplicationContext()).get(position);

        TextView songName = ((TextView) findViewById(R.id.songname_textview));
        TextView songArtist = ((TextView) findViewById(R.id.songartist_textview));
        TextView songDuration = ((TextView) findViewById(R.id.songduration_textview));
        TextView songLyrics = ((TextView) findViewById(R.id.songLyrics_textview));

        if(songName != null)
        songName.setText(song.name);
        if(songArtist != null)
        songArtist.setText(song.artist);
        if(songDuration != null)
        songDuration.setText(song.length);
        if(songLyrics != null)
        songLyrics.setText(song.lyrics);


        //To enable the back action on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //To set the song title as text on app bar
        getSupportActionBar().setTitle(song.name);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        //Fetching the images and displaying the artist image
        int id = Songs.getImageResId(getApplicationContext(), song.image);
        if (id != -1) {
            imageView.setImageResource(id);
        }

        SeekBar = (SeekBar) findViewById(R.id.seekBar);
        SeekBar.setEnabled(false);
        final ImageButton button = (ImageButton) findViewById(R.id.play);
        int songId = Songs.getNameResId(getApplicationContext(), song.song);

        mPlayer = MediaPlayer.create(getApplicationContext(), songId);

        isStart = true;
            button.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    if (mPlayer.isPlaying() && isStart) {
                        mPlayer.pause();
                        button.setImageResource(android.R.drawable.ic_media_play);
                        isStart = false;
                    } else {
                        mPlayer.start();
                        button.setImageResource(android.R.drawable.ic_media_pause);
                        isStart = true;
                        handler.sendEmptyMessage(0);
                    }
                }
            });

        finalTime = mPlayer.getDuration();
        SeekBar.setMax(finalTime);

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (isStart) {
                    if (SeekBar.getProgress() >= finalTime) {
                        button.setImageResource(android.R.drawable.ic_media_play);
                        SeekBar.setProgress(0);
                        progressBarValue = 0;
                        isStart = false;

                    } else {
                        progressBarValue = progressBarValue + 500;
                        SeekBar.setProgress(progressBarValue);
                    }
                    if (mPlayer.isPlaying()) {
                        handler.sendEmptyMessageDelayed(0, 500);
                    }
                }
            }
        };

        //Clicking on the fastForward button forwards the song by 5 seconds
        ImageButton fastForward = (ImageButton) findViewById(R.id.ff);
        if (fastForward != null) {
            fastForward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int temp = (int) startTime;

                    if ((temp + FORWARD_TIME) <= finalTime) {
                        startTime = startTime + FORWARD_TIME;
                        currentTime = mPlayer.getCurrentPosition();
                        currentTime = currentTime + FORWARD_TIME;
                        mPlayer.seekTo((int) currentTime);
                        progressBarValue = currentTime;
                    }

                    if (SeekBar.getProgress() == finalTime) {
                        button.setImageResource(android.R.drawable.ic_media_play);
                        isStart = true;
                        SeekBar.setProgress(0);
                    }

                }
            });
        }

        //Clicking on the rewind button rewinds the song by 5 seconds
        ImageButton rewind = (ImageButton) findViewById(R.id.rew);
        if (rewind != null) {
            rewind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int temp = (int) startTime;

                    if ((temp - BACKWARD_TIME) > 0) {
                        startTime = startTime - BACKWARD_TIME;
                        currentTime = mPlayer.getCurrentPosition();
                        currentTime = currentTime - BACKWARD_TIME;
                        mPlayer.seekTo((int) currentTime);
                        progressBarValue = currentTime;
                        //    Toast.makeText(getApplicationContext(), "You have Jumped backward 5 seconds", Toast.LENGTH_SHORT).show();
                    }

                    if (SeekBar.getProgress() == 0) {
                        button.setImageResource(android.R.drawable.ic_media_play);
                        isStart = true;
                        SeekBar.setProgress(0);
                    }
                }
            });
        }
    }

    /**
    **Clicking the back button of the device pauses and stops the song playing
    **/
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            mPlayer.stop();
        }
    }

    /**
    *On click of the back button on the app bar pauses and stops the song and moves back to the previous activity
     **/
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            mPlayer.stop();
        }
        finish();
        return true;
    }
}