package com.example.hackathonapp.AddingPictures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.hackathonapp.OldInsurences;
import com.example.hackathonapp.R;

public class fridge_screen extends AppCompatActivity {
private ImageButton mReturnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fridge_screen);

        mReturnButton = (ImageButton) findViewById(R.id.fridge_back_button);
        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OldInsurences.class);
                startActivity(intent);
            }
        });
    }
}