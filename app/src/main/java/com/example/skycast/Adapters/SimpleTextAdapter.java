package com.example.skycast.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skycast.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.viewHolder> {
    private List<String> items;

    public SimpleTextAdapter(String item) {
        this.items = new ArrayList<>();
        this.items.add(item);
    }

    @NonNull
    @Override
    public SimpleTextAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly, parent, false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleTextAdapter.viewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.simple_text_textView);
        }
    }
}
