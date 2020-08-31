package com.example.swapnil.parkit;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class helpActivity extends AppCompatActivity {
Button button;
VideoView videoView;
MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        button=(Button)findViewById(R.id.play);
        videoView=(VideoView)findViewById(R.id.videoView);
        mediaController=new MediaController(this);

    }

    public void play(View view) {
            String resources="android.resource://com.example.swapnil.parkit/"+R.raw.help;
            Uri uri= Uri.parse(resources);
            videoView.setVideoURI(uri);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);
            videoView.start();

    }
}
