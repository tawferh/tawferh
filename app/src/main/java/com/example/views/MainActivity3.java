package com.example.views;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_dashboard);

        // Получаем ссылки на элементы из layout
        ListView lvMain = findViewById(R.id.lv);
        ListView lvMain2 = findViewById(R.id.lv1);
        TextView txt = findViewById(R.id.txt);
        TextView txt2 = findViewById(R.id.txt2);

        // Создаем адаптеры для обоих ListView
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.mesta1,
                android.R.layout.simple_list_item_1
        );

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.mesta3,
                android.R.layout.simple_list_item_1
        );

        // Устанавливаем адаптеры
        lvMain.setAdapter(adapter1);
        lvMain2.setAdapter(adapter2);


    }
}