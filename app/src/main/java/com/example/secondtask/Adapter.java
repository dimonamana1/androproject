package com.example.secondtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Адаптер
 */
class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

    // В этом методе мы создаем новую ячейку
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_item_weather, parent, false);
        return new ViewHolder(view);
    }

    // В этом методе мы привязываем данные к ячейке
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.nameTv.setText(appInfo.getName());
        holder.versionTv.setText(appInfo.getVersionName());
        holder.iconIv.setImageDrawable(appInfo.getIcon());
    }

    // В этом методе мы возвращаем количество элементов списка
    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * View holder
     * Хранит информацию о ячейке
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iconIv;
        private final TextView nameTv;
        private final TextView versionTv;

        public ViewHolder(View itemView) {
            super(itemView);

            iconIv = itemView.findViewById(R.id.icon_iv);
            nameTv = itemView.findViewById(R.id.name_tv);
            versionTv = itemView.findViewById(R.id.version_tv);
        }
    }
}

}
