package com.example.views.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.views.R;

import java.util.List;

import com.example.views.ui.home.HomeFragment;

@SuppressLint("ViewConstructor")
public class ClusterView extends LinearLayout {

    private final TextView brownText;
    private final TextView greenText;
    private final TextView orangeText;
    private final TextView pinkText;
    private final TextView purpleText;

    private final View brownLayout;
    private final View greenLayout;
    private final View orangeLayout;
    private final View pinkLayout;
    private final View purpleLayout;

    public ClusterView(Context context) {
        super(context);
        inflate(context, R.layout.cluster_view, this);
        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        setOrientation(HORIZONTAL);
        setBackgroundResource(R.drawable.cluster_view_background);

        brownText = findViewById(R.id.text_brown_pins);
        greenText = findViewById(R.id.text_green_pins);
        orangeText = findViewById(R.id.text_orange_pins);
        pinkText = findViewById(R.id.text_pink_pins);
        purpleText = findViewById(R.id.text_purple_pins);

        brownLayout = findViewById(R.id.layout_brown_group);
        greenLayout = findViewById(R.id.layout_green_group);
        orangeLayout = findViewById(R.id.layout_orange_group);
        pinkLayout = findViewById(R.id.layout_pink_group);
        purpleLayout = findViewById(R.id.layout_purple_group);
    }

    public void setData(List<PlacemarkType> placemarkTypes) {
        for (PlacemarkType type : PlacemarkType.values()) {
            updateViews(placemarkTypes, type);
        }
    }

    private void updateViews(List<PlacemarkType> placemarkTypes, PlacemarkType type) {

        TextView textView = null;
        View layoutView = null;

        switch (type) {
            case BROWN:
                textView = brownText;
                layoutView = brownLayout;
                break;
            case GREEN:
                textView = greenText;
                layoutView = greenLayout;
                break;
            case ORANGE:
                textView = orangeText;
                layoutView = orangeLayout;
                break;
            case PINK:
                textView = pinkText;
                layoutView = pinkLayout;
                break;
            case PURPLE:
                textView = purpleText;
                layoutView = purpleLayout;
                break;
        }

        int value = countTypes(placemarkTypes, type);

        if (textView != null) {
            textView.setText(String.valueOf(value));
        }
        if (layoutView != null) {
            layoutView.setVisibility(value != 0 ? VISIBLE : GONE);
        }
    }

    private int countTypes(List<PlacemarkType> placemarkTypes, PlacemarkType type) {
        int count = 0;
        for (PlacemarkType placemarkType : placemarkTypes) {
            if (placemarkType == type) {
                count++;
            }
        }
        return count;
    }
}