package com.example.secondtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

    private List<String> list = new ArrayList<>();
    public void setStr(String str) {
        this.list.add(str);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_item_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String curString = list.get(position);
        holder.nameTv.setText(curString);
        holder.versionTv.setText(curString);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTv;
        private final TextView versionTv;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.name_tv);
            versionTv = itemView.findViewById(R.id.version_tv);
        }
    }
}

