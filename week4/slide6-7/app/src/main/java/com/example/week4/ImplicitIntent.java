package com.example.week4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ImplicitIntent extends AppCompatActivity {

    private Button btn;
    private Button goBack;
    private EditText link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);
        btn = findViewById(R.id.btn);
        link = findViewById(R.id.link);
        goBack = findViewById(R.id.backBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String directLink = link.getText().toString().isEmpty() ? "https://google.com/" : link.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(directLink));
                startActivity(intent);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

    }

    public void goBack() {
        this.onBackPressed();
    }

}