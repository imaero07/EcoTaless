package ru.amiralab.ecotales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View artView = findViewById(R.id.articlefragment);
        View multView = findViewById(R.id.multfragment);


        multView.setVisibility(View.GONE);
        artView.setVisibility(View.VISIBLE);



        ImageButton artBtn = findViewById(R.id.articlebutton);
        artBtn.setOnClickListener(v -> {multView.setVisibility(View.GONE);
            artView.setVisibility(View.VISIBLE);});

        ImageButton multBtn = findViewById(R.id.multbutton);
        multBtn.setOnClickListener(v -> {artView.setVisibility(View.GONE);
            multView.setVisibility(View.VISIBLE);});


    }
}