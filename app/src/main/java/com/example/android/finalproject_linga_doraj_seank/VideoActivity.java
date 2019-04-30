package com.example.android.finalproject_linga_doraj_seank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class VideoActivity extends YouTubeBaseActivity {

    private static final String TAG = "VideoActivity";

    YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: Starting....");
        mYouTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlayer);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadPlaylist("PLgCYzUzKIBE-eHpqt44Ea-Mi_iAUkpOdq");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick: Fail to initialize ....");
            }
        };

        mYouTubePlayerView.initialize(YoutubeConfig.getApiKey(), mOnInitializedListener);

    }

}
