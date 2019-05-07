package com.example.android.finalproject_linga_doraj_seank;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<CommentInfo> {
    public CommentAdapter(Context context, int resource, List<CommentInfo> object){
        super (context, resource,object);
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
