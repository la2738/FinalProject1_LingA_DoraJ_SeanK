package com.example.android.finalproject_linga_doraj_seank;

import android.content.Context;
import android.support.annotation.NonNull;
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
    private String rootNodeName = "comments";
    private EditText txtContent ;
    private ListView messageListView;
    private List<CommentInfo> comments ;
    private CommentArrayAdapter adapter ;

    private DatabaseReference refRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_pencil_box);

//        Intent i = getIntent();
        this.mYouTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtubePlayer);
        initYouTubePlayer();


        this.btnSend = (Button)findViewById(R.id.sendButton);
//        this.btnSend.setEnabled(FirebaseAuth.getInstance().getCurrentUser() != null);
        this.btnSend.setEnabled(true);  //for testing only
        this.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                if (user == null) {
//                    Snackbar.make(findViewById(android.R.id.content), "You have to sign in first.", Snackbar.LENGTH_LONG).show();
//                }
//                else {

                    Editable x = txtContent.getEditableText();
                    if (x != null) {
                        if (Strings.isEmptyOrWhitespace(x.toString())) {
                            Snackbar.make(findViewById(android.R.id.content), "Comment is required.", Snackbar.LENGTH_LONG).show();
                        }
                        else {
                            String content = x.toString();
                            CommentInfo cmt = new CommentInfo("kevin", content);
                            String key = UUID.randomUUID().toString();
                            refRoot.child(rootNodeName).child(key).setValue(cmt);
//                            refRoot.push().child(key).setValue(cmt);
                        }
                    }
//                }
            }
        });


        this.txtContent = (EditText)findViewById(R.id.messageEditText) ;


        this.comments = new ArrayList<>();
        this.messageListView = (ListView)findViewById(R.id.messageListView);
//        adapter = new CommentArrayAdapter(this);
//        this.messageListView.setAdapter(adapter);

    }

    private void initYouTubePlayer() {
        Log.d(TAG, "onCreate: Starting");
        this.mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onInitializationSuccess: Done initializing .....");

                youTubePlayer.loadVideo("8ainxpWPt_4"); //載入影片

                 initFirebaseRealtimeDB();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onInitializationFailure: Fail to  initialize youTube player .....");
                // initFirebaseRealtimeDB();
            }
        };

        Log.d(TAG, "onClick: initialize youtube player");
        mYouTubePlayerView.initialize(YouTubeConfig.getAPIKey(), mOnInitializedListener);

    }

    private void initFirebaseRealtimeDB() {
        refRoot = FirebaseDatabase.getInstance().getReference();
//        refRoot = FirebaseDatabase.getInstance().getReference(this.rootNodeName);
        refRoot.child(rootNodeName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                comments = new ArrayList<>();

                for (DataSnapshot commentSnapshot : dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    CommentInfo comment = commentSnapshot.getValue(CommentInfo.class);
                    comments.add(comment);
                }

                if (adapter == null) {
                    adapter = new CommentArrayAdapter(MakePencilBoxActivity.this);
                    messageListView.setAdapter(adapter);
                }
                else {
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "read comments error : " + databaseError.getMessage());
                Snackbar.make(findViewById(android.R.id.content), "read comments failed", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private class CommentArrayAdapter extends ArrayAdapter<CommentInfo> {

        private Context context;

        public CommentArrayAdapter(Context context) {
            super(context, -1, comments);
            this.context = context ;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.item_message, parent, false);
            TextView txtMessage = (TextView) rowView.findViewById(R.id.txtMessage);
            TextView txtName = (TextView) rowView.findViewById(R.id.txtName);
            TextView txtTime = (TextView) rowView.findViewById(R.id.txtTime);

            ImageView imageView = (ImageView) rowView.findViewById(R.id.photoImageView);
            CommentInfo comment = comments.get(position);

            txtMessage.setText(comment.content);
            txtName.setText(comment.name);
            txtName.setText(comment.name);

            // change the icon for Windows and iPhone
//            String s = values[position];
            String s = comment.content ;
            if (s.startsWith("iPhone")) {
                //imageView.setImageResource(R.drawable.no);
            } else {
                //imageView.setImageResource(R.drawable.ok);
            }

            return rowView;
        }

//        @Override
//        public long getItemId(int position) {
//            String item = getItem(position);
//            return mIdMap.get(item);
//        }

//        @Override
//        public boolean hasStableIds() {
//            return true;
//        }

    }


}





