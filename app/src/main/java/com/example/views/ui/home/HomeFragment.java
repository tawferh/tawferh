package com.example.views.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.views.data.manager.SearchManager;
import com.example.views.databinding.FragmentHomeBinding;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.views.R;


// Map import
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.map.ClusterListener;
import com.yandex.mapkit.map.ClusterizedPlacemarkCollection;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.runtime.ui_view.ViewProvider;

// Search import
import com.example.views.data.api.*;
import com.example.views.data.repository.*;
import com.example.views.data.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class HomeFragment extends Fragment implements SearchManager.SearchResultListener {
    private FragmentHomeBinding binding;
    private MapView mapView;


    private SearchManager searchManager;
    private EditText searchInput;
    private FloatingActionButton searchButton;


    MapObjectTapListener placemarkTapListener = (mapObject, point) -> {
        Toast.makeText(this.getContext(),"Tapped the placemark: " + mapObject.getUserData(), Toast.LENGTH_LONG).show();
        return true;
    };
    ClusterListener clusterListener = cluster -> {
        List<PlacemarkType> placemarkTypes = new ArrayList<>();
        for (PlacemarkMapObject placemark : cluster.getPlacemarks()) {
            placemarkTypes.add(((PlacemarkUserData) Objects.requireNonNull(placemark.getUserData())).getType());
        }

        // Устанавливает внешний вид каждого кластера с использованием пользовательского представления
        // которое отображает значки кластера
        cluster.getAppearance().setView(
                new ViewProvider(
                        new ClusterView(this.getContext()) {{
                            setData(placemarkTypes);
                        }}
                )
        );
        cluster.getAppearance().setZIndex(100f);
        //cluster.addClusterTapListener(clusterTapListener);
    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        // взаимодействие с поисковой строкой
        searchActivity();

        // стартовое положение карты
        startMapView();

        return root;
    }

    private void startMapView() {
        mapView = binding.mapview;
        final Map map = mapView.getMapWindow().getMap();
        map.move(GeometryProvider.startPosition);

        MapObjectCollection collection = map.getMapObjects().addCollection();

        //регистрация слушателя и лямда-функции
        ClusterizedPlacemarkCollection clusterizedCollection = collection.addClusterizedPlacemarkCollection(clusterListener);

        //структура [[цвет(ключ), картинка(значение)], [], ...]
        HashMap<Object, Object> placemarkTypeToImageProvider = new HashMap<>();
        placemarkTypeToImageProvider.put(PlacemarkType.BROWN, ImageProvider.fromResource(this.requireContext(), R.drawable.pin_brown));
        placemarkTypeToImageProvider.put(PlacemarkType.GREEN, ImageProvider.fromResource(this.requireContext(), R.drawable.pin_green));
        placemarkTypeToImageProvider.put(PlacemarkType.ORANGE, ImageProvider.fromResource(this.requireContext(), R.drawable.pin_orange));
        placemarkTypeToImageProvider.put(PlacemarkType.PINK, ImageProvider.fromResource(this.requireContext(), R.drawable.pin_pink));
        placemarkTypeToImageProvider.put(PlacemarkType.PURPLE, ImageProvider.fromResource(this.requireContext(), R.drawable.pin_purple));

        //рисуем метки
        for (int index = 0; index < GeometryProvider.points.size(); index++) {
            Point point = GeometryProvider.points.get(index);
            //PlacemarkType type = PlacemarkType.values()[new Random().nextInt(PlacemarkType.values().length)];
            PlacemarkType type = PlacemarkType.values()[0];
            ImageProvider imageProvider = (ImageProvider) placemarkTypeToImageProvider.get(type);

            if (imageProvider == null) { continue; }

            PlacemarkMapObject placemark = clusterizedCollection.addPlacemark();
            placemark.setGeometry(point);
            placemark.setIcon(imageProvider);
            placemark.setIconStyle(new IconStyle().setScale(0.1f));
            placemark.setUserData(new PlacemarkUserData("Data_" + index, type));
            placemark.addTapListener(placemarkTapListener);
        }

        clusterizedCollection.clusterPlacemarks(20, 15);
    }

    private void searchActivity() {
        // Находим searchInput и searchButton, используя binding
        searchInput = binding.searchInput;
        searchButton = binding.searchButton;

        // Делаем поле поиска видимым
        searchInput.setVisibility(View.VISIBLE);
        searchButton.setVisibility(View.VISIBLE);

        // Обработчик кнопки поиска
        searchButton.setOnClickListener(v ->
                searchManager.performSearch(searchInput.getText().toString()));
    }


    // Реализация методов SearchResultListener
    @Override
    public void onSearchResults(List<EntertainmentMap> results) {
        if (results == null || results.isEmpty()) {
            Toast.makeText(requireContext(), "Ничего не найдено", Toast.LENGTH_SHORT).show();
            return;
        }

        // Вот тут обработка результата
        EntertainmentMap firstResult = results.get(0);
        Toast.makeText(requireContext(),"Найдено: " + firstResult.getName() + " (" + firstResult.getRating() + ")", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSearchError(String error) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show();
    }

    // под вопросом
    @Override
    public void onLoading(boolean isLoading) {
        // binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
    //


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchManager = new SearchManager(requireContext(), this);
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
