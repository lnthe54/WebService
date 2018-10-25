package com.example.lnthe54.webservice.view;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.lnthe54.webservice.R;
import com.example.lnthe54.webservice.adapter.AreaAdapter;

/**
 * @author lnthe54 on 10/24/2018
 * @project WebService
 */
public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_GALLERY = 1;
    private static final int REQUEST_CODE_CAMERA = 2;
    private static final String TITLE_TOOLBAR = "Đăng bài";

    private String[] area = {"Quận Cầu Giấy", "Quận Bắc Từ Liêm", "Quận Nam Từ Liêm", "Quận Đống Đa", "Quận Hà Đông",
            "Quận Hai Bà Trưng", "Quận Hoàng Mai", "Quận Long Biên", "Quận Thanh Xuân", "Quận Hồ Tây",
            "Quận Hoàn Kiếm"};

    private Toolbar toolbar;
    private EditText etFoodName;
    private ImageView ivFood;
    private EditText etFoodPrice;
    private EditText etFoodAddress;
    private EditText etFoodDesc;
    private Spinner spArea;
    private Button btnPost;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initViews();
        addEvents();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(TITLE_TOOLBAR);

        etFoodName = findViewById(R.id.et_name);
        ivFood = findViewById(R.id.img_food);
        etFoodPrice = findViewById(R.id.et_price);
        etFoodAddress = findViewById(R.id.et_address);
        etFoodDesc = findViewById(R.id.et_description);

        spArea = findViewById(R.id.sp_area);

        btnPost = findViewById(R.id.btn_post);
    }

    private void addEvents() {
        ivFood.setOnClickListener(this);
        btnPost.setOnClickListener(this);

        spArea.setAdapter(new AreaAdapter(AddActivity.this, R.layout.layout_custom_spinner, area));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_food: {
                chooseImage();
                break;
            }

            case R.id.btn_post: {
                handlingPost();
                break;
            }
        }
    }

    private void chooseImage() {
        final Dialog dialogImage = new Dialog(AddActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialogImage.setContentView(R.layout.layout_dialog_choose_image);

        LinearLayout layoutGallery;
        LinearLayout layoutCamera;

        layoutGallery = dialogImage.findViewById(R.id.gallery);
        layoutCamera = dialogImage.findViewById(R.id.camera);

        layoutGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageGallery();
                dialogImage.cancel();
            }
        });

        layoutCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageCamera();
                dialogImage.cancel();
            }
        });
        dialogImage.show();

    }

    private void pickImageGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, REQUEST_CODE_GALLERY);
    }

    private void pickImageCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, REQUEST_CODE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1: {

                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    ivFood.setImageURI(selectedImage);
                }
                break;
            }
            case 2: {
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    ivFood.setImageURI(selectedImage);
                }
                break;
            }
        }
    }

    private void handlingPost() {
        if (etFoodName.getText().toString().isEmpty()) {
            etFoodName.setError("Field can't empty");
        }
    }
}
