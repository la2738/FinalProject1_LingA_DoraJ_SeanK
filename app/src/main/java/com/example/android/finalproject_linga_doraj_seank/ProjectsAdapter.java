package com.example.android.finalproject_linga_doraj_seank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectViewHolder> {

    private List<Project> projects;
    private Context context;

    public ProjectsAdapter(List<Project>projects, Context context){
        this.projects = projects;
        this.context = context;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card_view, parent, false);
        return new ProjectViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position){
        Project project = projects.get(position);
        holder.projectName.setText(project.name);
        holder.projectLevel.setText(project.level);
        holder.projectTime.setText(project.time);
        holder.projectImage.setImageResource(project.photoID);
        holder.projectRating.setText(project.rating);
    }

    @Override
    public int getItemCount(){
        return projects.size();
    }
}
