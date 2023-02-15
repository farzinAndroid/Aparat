package com.farzin.aparat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.farzin.aparat.R;
import com.farzin.aparat.databinding.ActivityRegisterBinding;
import com.farzin.aparat.webService.ApiCaller;
import com.farzin.aparat.webService.MassageListener;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private ApiCaller apiCaller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startBinding();


        binding.skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username= binding.useredt.getText().toString();
                String password = binding.passedt.getText().toString();

                apiCaller = new ApiCaller();
                apiCaller.getUserRegister(username, password, new MassageListener() {
                    @Override
                    public void onSuccess(Object successMassage) {
                        Log.e("", "");

                        try {
                            JSONObject jsonObject = new JSONObject(successMassage.toString());

                            int result = jsonObject.getInt("code");

                            if (result>0){
                                Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(String errorMassage) {

                    }
                });
            }
        });
    }













    private void startBinding() {
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}