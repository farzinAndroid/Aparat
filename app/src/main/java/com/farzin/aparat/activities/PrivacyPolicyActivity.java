package com.farzin.aparat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewClient;

import com.farzin.aparat.R;
import com.farzin.aparat.Utils.Constants;
import com.farzin.aparat.databinding.ActivityPrivacyPolicyBinding;

public class PrivacyPolicyActivity extends AppCompatActivity {

    private ActivityPrivacyPolicyBinding binding;

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

        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.setWebViewClient(new WebViewClient());
        binding.webview.loadUrl(Constants.LOAD_PRIVACY_POLICY);



        binding.toolbar.txtTitle.setText(R.string.privacy_policy);
    }









    private void startBinding() {
        binding = ActivityPrivacyPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}