package com.classwork.onlinenotebook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.classwork.onlinenotebook.ApiClient;
import com.classwork.onlinenotebook.ApiInterface;
import com.classwork.onlinenotebook.R;
import com.classwork.onlinenotebook.models.AllNotes;
import com.classwork.onlinenotebook.views.UpdateActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class RecyclerAdapters extends RecyclerView.Adapter<RecyclerAdapters.ViewHolder> {

    Context context;
    List<AllNotes> dataModels;
    ApiInterface apiInterface;

    public RecyclerAdapters(Context context, List<AllNotes> dataModels) {
        this.context = context;
        this.dataModels = dataModels;

        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);
    }


    @NonNull
    @Override
    public RecyclerAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridlayout, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapters.ViewHolder holder, int position) {
        holder.notes.setText(dataModels.get(position).getNotes().toString());
        holder.date.setText(dataModels.get(position).getUpdateat().toString());
        holder.edit.setOnClickListener(v -> {
            String note = dataModels.get(position).getNotes().toString().trim();

            Intent intent = new Intent(context, UpdateActivity.class);

            intent.putExtra("text", note);

        });
        //click
        holder.linearLayout.setOnClickListener(view -> {
            String note = dataModels.get(position).getNotes().toString().trim();
            int id = dataModels.get(position).getId();

            Intent intent = new Intent(context, UpdateActivity.class);

            intent.putExtra("text", note);
            intent.putExtra("id", id);

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView notes, date;
        LinearLayout linearLayout;
        ImageView delete, share, edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notes = itemView.findViewById(R.id.notetext);
            date = itemView.findViewById(R.id.datetext);
            linearLayout = itemView.findViewById(R.id.linearlayoutforcl);

            delete = itemView.findViewById(R.id.deletenote);
            share = itemView.findViewById(R.id.sharenote);
            edit = itemView.findViewById(R.id.editnote);

        }
    }
}
