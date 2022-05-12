package com.example.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class OldInsurences extends AppCompatActivity {
private ImageButton mSavionButton;
private ImageButton mPlusButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //next three lines are for setting full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_old_insurences);

        mSavionButton = (ImageButton) findViewById(R.id.savion_button);
        mPlusButton = (ImageButton) findViewById(R.id.add_new_insurence_btn);

        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), newInsurance.class);
                startActivity(intent);
            }
        });

        mSavionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),savion_insurance.class );
                startActivity(intent);
            }
        });
    }
}