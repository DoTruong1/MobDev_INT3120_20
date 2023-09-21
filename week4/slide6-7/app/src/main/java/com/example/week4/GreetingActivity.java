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

import com.example.week4.databinding.ActivityGreetingBinding;

public class GreetingActivity extends AppCompatActivity {

    private ActivityGreetingBinding binding;
    private TextView feedbackTextGreeting;
    private Button goBack;
    private String fullName;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGreetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        goBack = findViewById(R.id.goBackButton);
        this.feedbackTextGreeting = findViewById(R.id.feedbackTextGreeting);

        Intent intent = this.getIntent();
        this.fullName = intent.getStringExtra("fullName");
        this.message = intent.getStringExtra("message");
        feedbackTextGreeting.setText(message);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }

    public void goBack() {
        this.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


// Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

    @Override
    public void finish() {
        Intent data = new Intent();
        String feedback = "OK, Hello " + this.fullName + ". How are you?";
        data.putExtra("feedback", feedback);

        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}