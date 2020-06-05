package com.example.secondtask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<WeatherPerDay> weatherPerDay = new ArrayList<>();

    private static OnItemClickListener onItemClickListener;
    Adapter(OnItemClickListener onItemClickListener){
        Adapter.onItemClickListener = onItemClickListener;
    }
    void setStr(WeatherPerDay weatherPerDay) {
        this.weatherPerDay.add(weatherPerDay);
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_item_weather, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherPerDay curElem = weatherPerDay.get(position);
        holder.city.setText(curElem.getCity());
        holder.temperature.setText("Temp: " + curElem.getTemperature());
        Picasso.with(holder.icon.getContext()).load(curElem.getIconUrl()).into(holder.icon);
        holder.feelsLike.setText("feels like: " + curElem.getFeelsLike());
    }

    @Override
    public int getItemCount() {
        return weatherPerDay.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView city;
        private final TextView temperature;
        private final ImageView icon;
        private final TextView feelsLike;


        ViewHolder(View itemView) {
            super(itemView);

            city = itemView.findViewById(R.id.city);
            temperature = itemView.findViewById(R.id.temperature);
            icon = itemView.findViewById(R.id.icon);
            feelsLike = itemView.findViewById(R.id.feelsLike);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v);
                }
            });

        }

    }
    public interface OnItemClickListener{
        void onItemClick(View view);
    }
}

