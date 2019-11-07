package com.example.volley_bike.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volley_bike.Details.DetailActivity;
import com.example.volley_bike.Model.Bike;
import com.example.volley_bike.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.vholder>
{

    private Context context;
    private  List<Bike> list;



    public CustomAdapter(Context context, List<Bike> list)
    {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);
        vholder vh = new vholder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final vholder holder, final int position)
    {
        Bike b = list.get(position);

        holder.tv1.setText(String.valueOf(b.getBikeModelID()));
        holder.tv2.setText(String.valueOf(b.getBrandID()));
        holder.tv3.setText(String.valueOf(b.getModelName()));


        holder.relative_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("Bike",list.get(position));
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class vholder extends RecyclerView.ViewHolder
    {
        TextView tv1,tv2,tv3;
        RelativeLayout relative_item;

        public vholder(@NonNull final View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            relative_item = itemView.findViewById(R.id.relative_item);

        }
    }
}
