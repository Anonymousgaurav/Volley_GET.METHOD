package com.example.volley_bike.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volley_bike.Model.Bike;
import com.example.volley_bike.R;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.vholder>
{

    private Context context;
    private List<Bike> listt;

    public DetailsAdapter(Context context, List<Bike> listt)
    {
        this.context = context;
        this.listt = listt;
    }

    @NonNull
    @Override
    public vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vieww = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item, parent,false);
        vholder vh = new vholder(vieww);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull vholder holder, int position)
    {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class vholder extends RecyclerView.ViewHolder
    {
        TextView tv11,tv22,tv33;

        public vholder(@NonNull View itemView) {
            super(itemView);

            tv11 = itemView.findViewById(R.id.tv11);
            tv22 = itemView.findViewById(R.id.tv22);
            tv33= itemView.findViewById(R.id.tv33);
        }
    }
}
