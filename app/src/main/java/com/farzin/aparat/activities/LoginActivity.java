package com.farzin.aparat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.farzin.aparat.Utils.AppConfig;
import com.farzin.aparat.databinding.ActivityLoginBinding;
import com.farzin.aparat.webService.ApiCaller;
import com.farzin.aparat.webService.MassageListener;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private ApiCaller apiCaller;
    private AppConfig appConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startBinding();
        appConfig = new AppConfig(getApplicationContext());


        binding.registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = binding.useredt.getText().toString();
                String password = binding.passedt.getText().toString();

                apiCaller = new ApiCaller();
                apiCaller.getUserLogin(username, password, new MassageListener() {
                    @Override
                    public void onSuccess(Object successMassage) {
                        Log.e("", "");

                        //این سرویس یک کد برمیکرداند اگر کد 0 بود یعنی لاگین انجام نشده
                        //اگر کد 1 بود یعنی لاگین انجام شده پس با JsonObject چک میکنیم

                        try {
                            JSONObject jsonObject = new JSONObject(successMassage.toString());

                            int code = jsonObject.getInt("code");


                            if (code >0){


                                appConfig.setUserid(code);

                                Toast.makeText(LoginActivity.this, "You are Logged", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoginActivity.this, "You are not logged, Please try again", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(String errorMassage) {
                        Log.e("","");
                    }
                });

            }
        });


    }









    private void startBinding() {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}