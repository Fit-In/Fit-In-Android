package com.example.fitin_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button btnRegister = (Button)findViewById(R.id.BtnPhone);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister =  new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
            }
        });



        Button btnNaver = (Button)findViewById(R.id.BtnNaver);
        Button btnFacebook = (Button)findViewById(R.id.BtnFacebook);
        Intent intentweb = new Intent(getApplicationContext(),WebviewActivity.class);


        btnNaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentweb.putExtra("Uri",0);
                startActivity(intentweb);
            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentweb.putExtra("Uri",1);
                startActivity(intentweb);
            }
        });

    }
}