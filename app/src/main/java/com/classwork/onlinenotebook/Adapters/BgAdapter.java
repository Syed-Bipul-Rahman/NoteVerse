package com.classwork.onlinenotebook.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.classwork.onlinenotebook.R;
import com.classwork.onlinenotebook.models.BGmodel;

import java.util.ArrayList;

public class BgAdapter extends RecyclerView.Adapter<BgAdapter.myviewholder> {
    private OnItemClickListener onItemClickListener;
    Context context;
    ArrayList<BGmodel> arrayList;

    public BgAdapter(Context context, ArrayList<BGmodel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public BgAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bgrecycler_layout, parent, false);
        myviewholder myHolder = new myviewholder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BgAdapter.myviewholder holder, int position) {
        holder.imageView.setImageResource(arrayList.get(position).getImage());
        holder.linearLayout.setOnClickListener(view -> {
            Toast.makeText(context, "clicked position " + position, Toast.LENGTH_SHORT).show();

            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position); // Notify the listener of item click
            }


        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout linearLayout;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recyclerimageladdyo);
            linearLayout = itemView.findViewById(R.id.conteainerbg);


        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
