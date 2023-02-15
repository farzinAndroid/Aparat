package com.farzin.aparat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.farzin.aparat.R;
import com.farzin.aparat.databinding.ActivityBoutUsBinding;

public class AboutUsActivity extends AppCompatActivity {

    private ActivityBoutUsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startBinding();

        binding.toolbar.linBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.toolbar.txtTitle.setText(getResources().getString(R.string.about_us));
    }

    private void startBinding() {
        binding = ActivityBoutUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}