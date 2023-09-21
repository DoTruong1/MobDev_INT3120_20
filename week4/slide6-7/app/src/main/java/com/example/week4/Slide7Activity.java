package com.example.week4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.week4.databinding.ActivitySlide7Binding;

public class Slide7Activity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK ) {
                        Intent intent = result.getData();
                        feedbackText.setText(intent.getStringExtra("feedback"));
                    }
                }
            });


    private AppBarConfiguration appBarConfiguration;
    private ActivitySlide7Binding binding;
    private TextView feedbackText;
    private EditText editText;
    private Button greetingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySlide7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        greetingButton = findViewById(R.id.grettingButton);
        editText = findViewById(R.id.editTextFullName);
        feedbackText = findViewById(R.id.feedbackText);
        greetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    public void sendMessage() {
        String fullName = editText.getText().toString();
        String message = "Hello, Please say hello me!";

        Intent intent = new Intent(this, GreetingActivity.class);

        intent.putExtra("fullName", fullName);
        intent.putExtra("message", message);

        activityResultLauncher.launch(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
//        final int item1 = R.id.item1;
//        textView = findViewById(R.id.textView);
        if (id == R.id.item1) {
            openContext();
            return true;
        } else if (id == R.id.item2) {
            openPopup();
            return true;
        } else if (id == R.id.slide7Act) {
            Intent intent = new Intent(this, Slide7Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.mainAct) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.implicitAct) {
            Intent intent = new Intent(this, ImplicitIntent.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void openContext() {
        Fragment fragment = new FirstFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameLayout, fragment).commit();

    }
    public void openPopup() {
        Fragment fragment = new SecondFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commit();
    }

}