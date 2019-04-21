package com.example.android.finalproject_linga_doraj_seank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CommunityFragment extends Fragment {

    private static final String TAG = "CommunityFragment";
    private static final int NUM_COLUMNS = 2;

    private ArrayList<Post> posts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);

        initialBitmaps();

        Log.d(TAG, "initializing staggered recyclerview.");

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.staggered_recycler_view);
        PostsAdapter adapter = new PostsAdapter(posts, getContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void initialBitmaps(){
        Log.d(TAG, "InitialBitmaps preparing");

        posts.add(new Post(R.string.painting_url, R.string.painting_hack_title, R.string.painting_author, R.string.painting_likes));
        posts.add(new Post(R.string.refrig_url,R.string.refrig_title, R.string.refrig_author, R.string.refrig_likes));
        posts.add(new Post(R.string.cattree_url, R.string.cattree_title, R.string.cattree_author, R.string.cattree_likes));
        posts.add(new Post(R.string.porridge_url, R.string.porridge_title, R.string.porridge_author, R.string.porridge_likes));

    }

}
