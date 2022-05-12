package com.example.hackathonapp.AddingPictures;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hackathonapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AddImages extends AppCompatActivity {
    private ImageButton mOpenCameraBttn;
    private ImageButton mMediaGalleryBttn;

    private String fileName;
    private ImageView myimage;
    private String encodedString;
    private ImageView mImageFromGallery;
    Uri selectedImage;
    //    ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
////                    if(result.getResultCode() == Activity.RESULT_OK) {
//                        Log.v("TAG", "ENTERED IF");
//                        Intent data = result.getData();
//                        Uri selectedImage = data.getData();
//                        String[] filePathColumn = { MediaStore.Images.Media.DATA };
//                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                        cursor.moveToFirst();
//                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                        String picturePath = cursor.getString(columnIndex);
//                        cursor.close();
//
//                        String fileNameSegments[] = picturePath.split("/");
//                        fileName = fileNameSegments[fileNameSegments.length - 1];
//
//                        Bitmap myImg = BitmapFactory.decodeFile(picturePath);
//                        myimage.setImageBitmap(myImg);
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                        // Must compress the Image to reduce image size to make upload easy
//                        myImg.compress(Bitmap.CompressFormat.PNG, 50, stream);
//                        byte[] byte_arr = stream.toByteArray();
//                        // Encode Image to String
//                        encodedString = Base64.encodeToString(byte_arr, 0);
//                        Log.v("TAG", "BANANA");
//                        uploadImage();
//
////                    }
//                }
//            });
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.v("TAG", "BANANA");
                        Intent data = result.getData();
                        selectedImage = data.getData();

                        mImageFromGallery.setImageURI(selectedImage);
//                        String utf = selectedImage.toString();
//                        byte[] bytes = utf.getBytes(StandardCharsets.UTF_8);
//                        String utf8Encoding = new String(bytes, StandardCharsets.UTF_8);
//                        encodedString = utf8Encoding;


                        BitmapDrawable drawable = (BitmapDrawable) mImageFromGallery.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();
////                final byte[] arr = bitmap.getNinePatchChunk();
                        Intent intent = new Intent(AddImages.this, afterLoadImage.class);
                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                        intent.putExtra("byteArray", bs.toByteArray());
//                intent.putExtra("response",response);
//                intent.putExtra("image", bitmap);
//                        SystemClock.sleep(3000);
                        startActivity(intent);
//                        uploadImage();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_images);

        mOpenCameraBttn = (ImageButton) findViewById(R.id.open_camera_button);
        mMediaGalleryBttn = (ImageButton) findViewById(R.id.choose_from_gallery_bttn);
        mImageFromGallery = (ImageView) findViewById(R.id.test_image_view);
        mOpenCameraBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

        mMediaGalleryBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivity(intent);
//                mGetContent.launch(intent);
                mStartForResult.launch(new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));

            }
        });

    }

    /**
     * API call for upload selected image from gallery to the server
     */
//    public void uploadImage() {
//        Log.v("TAG", "BANANA2");
//        RequestQueue rq = Volley.newRequestQueue(this);
//        String url = "https://hackathon-bituach-server.herokuapp.com";
//        Log.d("URL", url);
//        StringRequest stringRequest = new StringRequest(Request.Method.PUT,
//                url, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
////                try {
//                Log.e("RESPONSE", response);
////                    JSONObject json = new JSONObject(response);
//
//                Toast.makeText(getBaseContext(),
//                        "The image uploaded", Toast.LENGTH_SHORT)
//                        .show();
//               BitmapDrawable drawable = (BitmapDrawable) mImageFromGallery.getDrawable();
//               Bitmap bitmap = drawable.getBitmap();
//////                final byte[] arr = bitmap.getNinePatchChunk();
//                Intent intent = new Intent(AddImages.this, afterLoadImage.class);
//                ByteArrayOutputStream bs = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
//                intent.putExtra("byteArray", bs.toByteArray());
////                intent.putExtra("response",response);
////                intent.putExtra("image", bitmap);
//                startActivity(intent);
////                } catch (JSONException e) {
////                    Log.d("JSON Exception", e.toString());
////                    Toast.makeText(getBaseContext(),
////                            "Error while loadin data!",
////                            Toast.LENGTH_LONG).show();
////                }
//
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("ERROR", "Error [" + error + "]");
//                Toast.makeText(getBaseContext(),
//                        "Cannot connect to server", Toast.LENGTH_LONG)
//                        .show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//
//                params.put("image", encodedString);
////                params.put("filename", fileName);
//
//                return params;
//
//            }
//
//        };
//        rq.add(stringRequest);
//    }

}