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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.views.FindPlaceActivity;
import com.example.views.MainActivity3;
import com.example.views.data.api.RetrofitClient;
import com.example.views.data.repository.EntertainmentRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import com.example.views.databinding.FragmentHomeBinding;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.mapview.MapView;

import com.example.views.data.api.*;
import com.example.views.data.repository.*;
import com.example.views.data.model.*;

import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private MapView mapView;

    private EditText searchInput;
    private FloatingActionButton searchButton;
    private EntertainmentRepository repository;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        // Находим searchInput и searchButton, используя binding
        searchInput = binding.searchInput;
        searchButton = binding.searchButton;


        // Делаем поле поиска видимым
        searchInput.setVisibility(View.VISIBLE);
        searchButton.setVisibility(View.VISIBLE);

        // Инициализация репозитория
        repository = new EntertainmentRepository(RetrofitClient.getApiService());

        // Обработчик кнопки поиска
        searchButton.setOnClickListener(v -> performSearch());


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


    private void performSearch() {
        String query = searchInput.getText().toString().trim();

        if (query.isEmpty()) {
            Toast.makeText(requireContext(), "Введите поисковый запрос", Toast.LENGTH_SHORT).show();
            return;
        }

        // Показываем индикатор загрузки (если есть)
        showLoading(true);

        repository.search(
                query,          // name
                null,          // category
                0f,           // minRating
                0,            // page
                20,           // size
                "rating",     // sortBy
                SortDirection.DESC, // direction
                new EntertainmentRepository.ApiCallback<ApiPageResponse<EntertainmentMap>>() {
                    @Override
                    public void onSuccess(ApiPageResponse<EntertainmentMap> page) {
                        requireActivity().runOnUiThread(() -> {
                            showLoading(false);
                            processSearchResults(page.getContent());
                        });
                    }

                    @Override
                    public void onError(String error) {
                        requireActivity().runOnUiThread(() -> {
                            showLoading(false);
                            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show();
                        });
                    }
                }
        );
    }

    private void showLoading(boolean isLoading) {
        // Реализуйте отображение/скрытие ProgressBar
        // Например:
        // binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void processSearchResults(List<EntertainmentMap> results) {
        if (results == null || results.isEmpty()) {
            Toast.makeText(requireContext(), "Ничего не найдено", Toast.LENGTH_SHORT).show();
            return;
        }

        EntertainmentMap firstResult = results.get(0);
        Toast.makeText(requireContext(),"Найдено: " + firstResult.getName() + " (" + firstResult.getRating() + ")", Toast.LENGTH_SHORT).show();


        // Пример: Переход на FindPlaceActivity с передачей данных
        //Intent intent = new Intent(requireActivity(), MainActivity3.class);

//        // Если нужно передать данные (например, первый результат или весь список)
//        intent.putExtra("first_place_name", results.get(0).getName());
//        intent.putExtra("first_place_lat", results.get(0).getLatitude());
//        intent.putExtra("first_place_lon", results.get(0).getLongitude());
//
//        // Или передать весь список (если EntertainmentMap implements Serializable)
//        intent.putExtra("all_places", new ArrayList<>(results));

        //startActivity(intent);
        // Обработка результатов поиска:
        // 1. Добавление маркеров на карту.
        // 2. Обновление списка мест (если есть RecyclerView).
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
