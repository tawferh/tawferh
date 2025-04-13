package com.example.views.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import com.example.views.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.mapview.MapView;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    private MapView mapView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        // Находим EditText и FloatingActionButton, используя binding
        EditText editText = binding.editText;
        FloatingActionButton floatingActionButton = binding.floatingActionButton4;


        // Делаем поле поиска видимым
        editText.setVisibility(View.VISIBLE);
        floatingActionButton.setVisibility(View.VISIBLE);


        mapView = binding.mapview;
        final Map map = mapView.getMapWindow().getMap();
        map.move(
                new CameraPosition(
                        new Point(53.211785, 50.179790),
                        17.0f,
                        150.0f,
                        30.0f
                )
        );


        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }


    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
