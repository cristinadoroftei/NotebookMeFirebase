package com.example.notemefirebase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.CustomViewHolder> {



    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
        String title = MemoryStorage.notes.get(position).getTitle();
        String date = MemoryStorage.notes.get(position).getDate();
        String time = MemoryStorage.notes.get(position).getTime();
        holder.nTitle.setText(title);
        holder.nDate.setText(date);
        holder.nTime.setText(time);

    }

    @Override
    public int getItemCount() {
        return MemoryStorage.notes.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView nTitle, nDate, nTime;
        ConstraintLayout itemLayout;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.nTitle);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);
            itemLayout = itemView.findViewById(R.id.parent_layout);

            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AddNote.class);
                    intent.putExtra("note", MemoryStorage.notes.get(getAdapterPosition()));

                    Log.d("BeforeIntent:", String.valueOf(MemoryStorage.notes.get(getAdapterPosition()).getID()));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
