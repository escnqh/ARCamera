package com.ntanougat.arcamera;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ntanougat.arcamera.ui.activity.MainActivity;

/**
 * Created by ESCNQH on 2018/3/25.
 */

public class ARCameraApplication extends AppCompatActivity {

    private String TAG="onStart com.ntanougat.arcamera.ARCameraApplication";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(ARCameraApplication.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
