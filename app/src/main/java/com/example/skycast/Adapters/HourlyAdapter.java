package com.example.skycast.Adapters;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skycast.R;
import com.example.skycast.Packages.Weather.HourlyData;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.viewHolder> {

    Map<String, HourlyData> items;
    Context ctx;

    public HourlyAdapter(Map<String, HourlyData> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public HourlyAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly, parent, false);
        this.ctx = parent.getContext();
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyAdapter.viewHolder holder, int position) {
        String key = (String)items.keySet().toArray()[position];
        HourlyData item = items.get(key);
        if (item != null) {
            LocalTime now = LocalTime.now();
            // we don't really care of the Locale.COUNTRY used to format the String
            // I just didn't want to use US over default English.
            int hour = now.getHour();
            int minute = now.getMinute();
            if (minute >= 45) {
                hour++; // Round up if the minutes are 30 or more
            }
            String hourNow = String.format(Locale.ENGLISH, "%02dH00", hour);
            if (Objects.equals(key, hourNow)) {
                holder.itemView.requestFocus();
            }
            holder.hour.setText(key);
            holder.tmp.setText(ctx.getString(
                    R.string.temperature_format,
                    (int)item.tmp2m));
            switch (item.getGeneralizedCondition()) {
                case 0:
                    holder.img.setImageResource(R.drawable.sunny);
                    break;
                case 1:
                    holder.img.setImageResource(R.drawable.night);
                    break;
                case 2:
                    holder.img.setImageResource(R.drawable.rainy);
                    break;
                case 3:
                    holder.img.setImageResource(R.drawable.snowy);
                    break;
                case 4:
                    holder.img.setImageResource(R.drawable.storm);
                    break;
                case 5:
                    holder.img.setImageResource(R.drawable.cloudy);
                    break;
                case 6:
                    holder.img.setImageResource(R.drawable.cloudy_sunny);
                    break;
                case 7:
                    holder.img.setImageResource(R.drawable.cloudy_night);
                    break;
            }

            holder.itemView.setOnClickListener(l -> {
                Toast.makeText(this.ctx,
                        ((TextView)l.findViewById(R.id.hourly_hour)).getText() + " " + ((TextView)l.findViewById(R.id.hourly_temperature)).getText(),
                        Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView hour, tmp;
        ImageView img;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            this.hour = itemView.findViewById(R.id.hourly_hour);
            this.tmp = itemView.findViewById(R.id.hourly_temperature);
            this.img = itemView.findViewById(R.id.hourly_condition_img);
        }
    }
}
