package com.example.hackathonapp.AddingPictures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hackathonapp.R;
import com.example.hackathonapp.typeOfProduct;

public class afterLoadImage extends AppCompatActivity {
private ImageView mImageFromGallery;
private TextView mPriceTextView;
private ImageButton mSaveButton;
private ImageButton mClipButton;
private TextView mModuleTextVIew;
private TextView mManifactureTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_after_load_image);

        mImageFromGallery = (ImageView) findViewById(R.id.image_after_load);
        mPriceTextView = (TextView) findViewById(R.id.price_text_view);
        mManifactureTextView = (TextView) findViewById(R.id.manifacture_text_view);
        mModuleTextVIew = (TextView) findViewById(R.id.moodle_text_view);
        mSaveButton = (ImageButton) findViewById(R.id.save_button);
        mClipButton = (ImageButton) findViewById(R.id.clip_button);
//        Intent intent = getIntent();
//        Bitmap bitgetParcelableExtra("image");
        Bitmap map = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"),0, getIntent().getByteArrayExtra("byteArray").length);
        mImageFromGallery.setImageBitmap(map);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String value = extras.getString("response");
            Log.v("RESPONSE", "SOME" + value);
//            String[] valSplitted = value.split(",");
//            mManifactureTextView.setText(valSplitted[0]);
//            mModuleTextVIew.setText(valSplitted[1]);
//            mPriceTextView.setText(valSplitted[2]);
        }

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), fridge_screen.class);
                startActivity(intent);
            }
        });

        mClipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivity(intent);
            }
        });
    }
}