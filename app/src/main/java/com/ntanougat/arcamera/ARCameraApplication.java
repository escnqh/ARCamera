package com.ntanougat.arcamera;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ntanougat.arcamera.ui.activity.MainActivity;

/**
 * Created by ESCNQH on 2018/3/25.
 */

public class ARCameraApplication extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=new Intent(ARCameraApplication.this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
