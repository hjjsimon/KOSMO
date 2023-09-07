package com.kosmo.ratingbar14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;

import com.kosmo.ratingbar14.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonInc.setOnClickListener(view->{
            //기존 rating값+RatingBar의 stepSize값
            binding.ratingbar1.setRating(binding.ratingbar1.getRating()+binding.ratingbar1.getStepSize());
            binding.ratingbar2.setRating(binding.ratingbar2.getRating()+binding.ratingbar2.getStepSize());
            binding.ratingbar3.setRating(binding.ratingbar3.getRating()+binding.ratingbar3.getStepSize());
        });
        binding.buttonDecs.setOnClickListener(view->{
            //기존 rating값-RatingBar의 stepSize값
            binding.ratingbar1.setRating(binding.ratingbar1.getRating()-binding.ratingbar1.getStepSize());
            binding.ratingbar2.setRating(binding.ratingbar2.getRating()-binding.ratingbar2.getStepSize());
            binding.ratingbar3.setRating(binding.ratingbar3.getRating()-binding.ratingbar3.getStepSize());
        });
    }///////////onCreate
}