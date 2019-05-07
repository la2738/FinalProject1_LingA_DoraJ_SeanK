package com.example.android.finalproject_linga_doraj_seank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.util.Strings;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class MakePencilBoxActivity extends YouTubeBaseActivity  {

    private static final String TAG = "MakePencilBoxActivity";

    private YouTubePlayerView mYouTubePlayerView ;
    private YouTubePlayer.OnInitializedListener mOnInitializedListener ;

    private Button btnSend ;
    private EditText txtContent ;
    private ListView messageListView;
    private List<CommentInfo> comments ;
    private EditText commentInput;

    private DatabaseReference commentRef;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private CommentAdapter mCommentAdapter;
    private ChildEventListener mChildEventListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_pencil_box);

        this.mYouTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlayer);
        initYouTubePlayer();

        btnSend = (Button)findViewById(R.id.sendButton);
        txtContent = (EditText)findViewById(R.id.messageEditText) ;
        comments = new ArrayList<>();
        messageListView = (ListView)findViewById(R.id.messageListView);
        commentInput = (EditText)findViewById(R.id.messageEditText);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        commentRef = mFirebaseDatabase.getReference().child("Comments");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mCommentAdapter = new CommentAdapter(this,R.layout.item_message,comments);
        messageListView.setAdapter(mCommentAdapter);

        btnSend.isEnabled();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentInfo commentInfo= new CommentInfo(commentInput.getText().toString(),mFirebaseAuth.getCurrentUser().getDisplayName());
                commentRef.child(mFirebaseAuth.getCurrentUser().getDisplayName()).push().setValue(commentInfo);
                commentInput.setText("");
            }
        });

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                CommentInfo commentInfo = dataSnapshot.getValue(CommentInfo.class);
                mCommentAdapter.add(commentInfo);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        commentRef.addChildEventListener(mChildEventListener);
    }

    private void initYouTubePlayer() {
        Log.d(TAG, "onCreate: Starting");
        this.mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onInitializationSuccess: Done initializing .....");

                youTubePlayer.loadVideo("8ainxpWPt_4");

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onInitializationFailure: Fail to  initialize youTube player .....");
            }
        };

        Log.d(TAG, "onClick: initialize youtube player");
        mYouTubePlayerView.initialize(YouTubeConfig.getAPIKey(), mOnInitializedListener);

    }



}





