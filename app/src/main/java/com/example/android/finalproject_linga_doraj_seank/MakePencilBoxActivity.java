package com.example.android.finalproject_linga_doraj_seank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class MakePencilBoxActivity extends YouTubeBaseActivity {

    private static final String TAG = "MakePencilBoxActivity";

    private YouTubePlayerView mYouTubePlayerView ;

    private YouTubePlayer.OnInitializedListener mOnInitializedListener ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_pencil_box);

//        Intent i = getIntent();
        this.mYouTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlayer);

        Log.d(TAG, "onCreate: Starting");
        this.mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onClick: Done initializing .....");

                youTubePlayer.loadVideo("W4hTJybfU7s"); //載入影片，這是測試影片代碼，需要改成想要的 youtube 影片的代碼。
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick: Fail to  initialize youTube player .....");
            }
        };


        Log.d(TAG, "onClick: initialize youtube player");
        mYouTubePlayerView.initialize(YouTubeConfig.getAPIKey(), mOnInitializedListener);

    }

}


