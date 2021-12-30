package com.example.fitin_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("로그인");

        Intent inIntent = getIntent();

        ImageButton ivBack = (ImageButton)findViewById(R.id.ivback);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnLogin = (Button)findViewById(R.id.BtnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(),CompleteActivity.class);
                startActivity(outIntent);
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

        TextView tvRegister = (TextView)findViewById(R.id.TvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister =  new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }
}