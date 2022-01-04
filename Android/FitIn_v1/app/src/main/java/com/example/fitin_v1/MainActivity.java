package com.example.fitin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인화면");


        TextView textLogin = (TextView)findViewById(R.id.TextLogin);
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentLogin);
            }
        });

        ImageButton btnRegister = (ImageButton)findViewById(R.id.BtnPhone);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister =  new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
            }
        });



        ImageButton btnNaver = (ImageButton)findViewById(R.id.BtnNaver);
        ImageButton btnGoogle = (ImageButton)findViewById(R.id.BtnGoogle);
        Intent intentweb = new Intent(getApplicationContext(),WebviewActivity.class);


        btnNaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentweb.putExtra("Uri",0);
                startActivity(intentweb);
            }
        });
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentweb.putExtra("Uri",1);
                startActivity(intentweb);
            }
        });

    }
}