package com.shingetsu.ex1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ViewHolder> {
    Context context;
    ArrayList<String> listNumber;

    public NumberAdapter(Context context, ArrayList<String> listNumber) {
        this.context = context;
        this.listNumber = listNumber;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_number,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.number.setText(listNumber.get(position));
    }

    @Override
    public int getItemCount() {
        return listNumber.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.tv_number_item);
        }
    }
}
