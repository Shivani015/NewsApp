package com.example.shivanikoul.newsapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<News>newsList;
    Context context;
    public Adapter(Context context){
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return  newsList ==null?0:newsList.size();
    }

    public void swap(ArrayList<News> newsArrayList) {
        this.newsList =newsArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV,descriptionTV;
        ImageView newsImage;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTV =itemView.findViewById(R.id.titleTextview);
            descriptionTV=itemView.findViewById(R.id.description);
            newsImage =itemView.findViewById(R.id.newsImage);
        }

        public void bind (int position) {
            titleTV.setText(newsList.get(position).getTitle());
            descriptionTV.setText(newsList.get(position).getDiscription());

            Glide.with(context).load(newsList.get(position).getImgUrl()).into(newsImage);

        }
    }
}
