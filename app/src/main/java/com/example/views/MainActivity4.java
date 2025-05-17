package com.example.views;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_places);

        ListView lvMain = (ListView) findViewById(R.id.lv);
        TextView txt = (TextView) findViewById(R.id.txt);
        TextView txt1 = (TextView) findViewById(R.id.txt1);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mesta2,
                android.R.layout.simple_list_item_1);


        // Set the adapter to the ListView
        lvMain.setAdapter(adapter);

        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.place_park_zagorodny);
        // задаем масштабирование
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.WRAP_CONTENT , ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        imageView.setLayoutParams(layoutParams);
        constraintLayout.addView(imageView);


        setContentView(constraintLayout);


    }
}
