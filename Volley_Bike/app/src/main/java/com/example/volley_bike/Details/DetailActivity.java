package com.example.volley_bike.Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;

import com.example.volley_bike.Model.Bike;
import com.example.volley_bike.R;

public class DetailActivity extends AppCompatActivity {
     Bike bike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textView = (TextView)findViewById(R.id.tv11);
        TextView textView1 = (TextView)findViewById(R.id.tv22);
        TextView textView2 = (TextView)findViewById(R.id.tv33);


        bike = (Bike) getIntent().getSerializableExtra("Bike");
        textView.setText(String.valueOf(bike.getBrandID()));
        textView1.setText(String.valueOf(bike.getBikeModelID()));
        textView2.setText(String.valueOf(bike.getModelName()));








    }

    }

