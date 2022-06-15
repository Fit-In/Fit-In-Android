package com.example.fitin_v2.ui.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitin_v2.R;
import com.example.fitin_v2.databinding.FragmentHomeBinding;
import com.example.fitin_v2.model.NewsListResponseDto;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private HomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        adapter = new HomeAdapter(new HomeDiffUtil());
        binding.rvNewsList.setAdapter(adapter);
        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewsListResponseDto newsListResponseDto) {
                viewModel.displayNews(newsListResponseDto);
            }
        });

        viewModel.getSelectNews().observe(getViewLifecycleOwner(), selectNews -> {
            if (selectNews != null) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(selectNews));
                viewModel.displayNewsFinish();
            }
        });


        return binding.getRoot();
    }
}