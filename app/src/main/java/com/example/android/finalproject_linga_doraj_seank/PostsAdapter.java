package com.example.android.finalproject_linga_doraj_seank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private ArrayList<Post> posts;
    private Context context;

    public PostsAdapter(ArrayList<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_grid_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        Glide.with(context)
                .load(posts.get(i).photoUrl)
                .apply(requestOptions)
                .into(viewHolder.image);

        viewHolder.title.setText(posts.get(i).title);
        viewHolder.author.setText(posts.get(i).author);
        viewHolder.likes.setText(posts.get(i).liked);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public PostsAdapter (DatabaseReference postsRef, Context context){
        this.context = context;
        posts = new ArrayList<>();
        postsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Post p = dataSnapshot.getValue(Post.class);
                posts.add(p);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView author;
        TextView likes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.post_image);
            this.title = itemView.findViewById(R.id.post_title);
            this.author = itemView.findViewById(R.id.post_author);
            this.likes = itemView.findViewById(R.id.post_likes);
        }
    }

}
