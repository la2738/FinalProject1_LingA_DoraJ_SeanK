package com.example.android.finalproject_linga_doraj_seank;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ProjectViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public TextView projectName;
    public TextView projectTime;
    public TextView projectLevel;
    public ImageView projectImage;
    public TextView projectRating;

    public ProjectViewHolder(View itemView, final Context context){
        super (itemView);
        cardView = (CardView)itemView.findViewById(R.id.card_view_project);
        projectName = (TextView)itemView.findViewById(R.id.project_name);
        projectLevel = (TextView)itemView.findViewById(R.id.project_level);
        projectTime = (TextView)itemView.findViewById(R.id.project_time);
        projectImage = (ImageView)itemView.findViewById(R.id.project_photo);
        projectRating = (TextView)itemView.findViewById(R.id.project_rating);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, R.string.toast_do_it, Toast.LENGTH_LONG).show();
            }
        });

    }
}
