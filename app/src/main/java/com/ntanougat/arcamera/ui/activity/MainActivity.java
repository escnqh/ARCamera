package com.ntanougat.arcamera.ui.activity;

import android.os.Bundle;
import com.ntanougat.arcamera.R;
import com.ntanougat.arcamera.base.BaseActivity;
import com.ntanougat.arcamera.base.BasePresenter;
import com.ntanougat.arcamera.ui.fragment.PhotoInListFragment;

public class MainActivity extends BaseActivity {

    private PhotoInListFragment photoInListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
