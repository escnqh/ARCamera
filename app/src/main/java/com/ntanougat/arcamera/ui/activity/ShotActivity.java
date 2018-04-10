package com.ntanougat.arcamera.ui.activity;

import android.content.ContentValues;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ntanougat.arcamera.R;
import com.ntanougat.arcamera.base.BaseActivity;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;

/**
 * Created by ESCNQH on 2018/3/27.
 */

public class ShotActivity extends BaseActivity implements CameraBridgeViewBase.CvCameraViewListener2{

    private int FLAG = 1;
    private long nowtime;

    private static final String TAG = "MainActivity";

    private Mat mRgba;
    private Mat mIntermediateMat;
    private Mat mGray;

    private CameraBridgeViewBase mOpenCvCameraView;

    String appName;
    String galleryPath;
    String albumPath;

    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("opencv_java3");
        System.loadLibrary("opencv_java");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        appName = getString(R.string.app_name);
        galleryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        albumPath = galleryPath + File.separator + appName+File.separator+System.currentTimeMillis();

        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.tutorial2_activity_surface_view);
        mOpenCvCameraView.setVisibility(CameraBridgeViewBase.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvCameraView.setClickable(true);
        mOpenCvCameraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera camera = ((JavaCameraView) mOpenCvCameraView).getCamera();
                if (camera != null) camera.autoFocus(null);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mOpenCvCameraView.enableView();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mIntermediateMat = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
        mGray.release();
        mIntermediateMat.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        mRgba = inputFrame.rgba();
        mGray = inputFrame.gray();
        Mat mRgbaT = mRgba.t();
        Core.flip(mRgba.t(), mRgbaT, 1);
        Imgproc.resize(mRgbaT, mRgbaT, mRgba.size());
        Mat mGrayT = mGray.t();
        Core.flip(mGray.t(), mGrayT, 1);
        Imgproc.resize(mGrayT, mGrayT, mGray.size());
        nativeProcessFrame(mGrayT.getNativeObjAddr(), mRgbaT.getNativeObjAddr());
        try2Shot(mRgbaT,mGrayT);
        return mRgbaT;
    }

    public void try2Shot(Mat mRgbaT,Mat mGrayT){
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis-nowtime>1000&&FLAG<21) {
            Log.i("Time Logggggggggg",currentTimeMillis+"");
            nowtime=currentTimeMillis;
            FLAG++;
            Mat mBgr = new Mat();

            final String photoPath = albumPath + File.separator + currentTimeMillis + ".jpg";
            final ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DATA, photoPath);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
            values.put(MediaStore.Images.Media.TITLE, appName);
            values.put(MediaStore.Images.Media.DESCRIPTION, appName);
            values.put(MediaStore.Images.Media.DATE_TAKEN, currentTimeMillis);

            // Ensure that the album directory exists.
            File album = new File(albumPath);
            if (!album.isDirectory() && !album.mkdirs()) {
                Log.e(TAG, "Failed to create album directory at " + albumPath);
            }

            // Try to create the photo.
            Imgproc.cvtColor(mRgbaT, mBgr, Imgproc.COLOR_RGBA2BGR, 3);
            if (!Imgcodecs.imwrite(photoPath, mBgr)) {
                Log.e(TAG, "Failed to save photo to " + photoPath);

            }
            Log.d(TAG, "Photo saved successfully to " + photoPath);
            // Try to insert the photo into the MediaStore.
            Uri uri;
            try {
                uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } catch (final Exception e) {
                Log.e(TAG, "Failed to insert photo into MediaStore");
                e.printStackTrace();

                // Since the insertion failed, delete the photo.
                File photo = new File(photoPath);
                if (!photo.delete()) {
                    Log.e(TAG, "Failed to delete non-inserted photo");
                }
            }
            try {
                ExifInterface exif = new ExifInterface(photoPath);
                exif.setAttribute(ExifInterface.TAG_MAKE, "ShotByPeelson");
                exif.setAttribute(ExifInterface.TAG_MODEL, "ShotByPeelson");
                exif.saveAttributes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public native String stringFromJNI();

    public native void nativeProcessFrame(long matAddrGr, long matAddrRgba);
}
