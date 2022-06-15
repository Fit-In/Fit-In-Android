package com.example.fitin_v2.ui.detail;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitin_v2.R;
import com.example.fitin_v2.databinding.FragmentDetailBinding;
import com.example.fitin_v2.model.NewsListResponseDto;

public class DetailFragment extends Fragment {

    private DetailViewModel viewModel;
    private FragmentDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        final NewsListResponseDto newsList = DetailFragmentArgs.fromBundle(getArguments()).getSelectedNews();
        final DetailViewModelFactory viewModelFactory = new DetailViewModelFactory(newsList, requireActivity().getApplication());

        viewModel = new ViewModelProvider(this, viewModelFactory).get(DetailViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}