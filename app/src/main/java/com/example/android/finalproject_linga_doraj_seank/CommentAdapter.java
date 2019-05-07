package com.example.android.finalproject_linga_doraj_seank;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends ArrayAdapter<CommentInfo> {

    private ArrayList<CommentInfo> commentInfos;

    public CommentAdapter(final DatabaseReference commentRef, Context context, int resource, List<CommentInfo> object){
        super (context, resource, object);

        commentInfos = new ArrayList<>();
        commentRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                CommentInfo c = dataSnapshot.getValue(CommentInfo.class);
                commentInfos.add(c);
                notifyDataSetChanged();
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
        });
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView ==null){
            convertView= ((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_message,parent,false);
        }
        TextView message = (TextView)convertView.findViewById(R.id.txtMessage);
        TextView name = (TextView) convertView.findViewById(R.id.txtName);
        CommentInfo commentInfo = getItem(position);

        message.setText(commentInfo.getContent());
        name.setText(commentInfo.getName());
        return convertView;
    }
}
