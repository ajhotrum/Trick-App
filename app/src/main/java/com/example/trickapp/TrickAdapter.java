package com.example.trickapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TrickAdapter extends RecyclerView.Adapter <TrickAdapter.TrickHolder> {
    private List<Trick> tricks = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public TrickHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trick_item, parent, false);
        return new TrickHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrickHolder holder, int position) {
        Trick currentTrick = tricks.get(position);
        holder.textViewTitle.setText(currentTrick.getTitle());
    }

    @Override
    public int getItemCount() {
        return tricks.size();
    }

    public void setTricks(List<Trick> tricks) {
        this.tricks = tricks;
        notifyDataSetChanged();
    }

    public Trick getTrickAt(int position){
        return tricks.get(position);
    }

    class TrickHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;

        public TrickHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(tricks.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Trick trick);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
