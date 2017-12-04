package com.ntanougat.arcamera.ui.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import com.ntanougat.arcamera.R;
import com.ntanougat.arcamera.base.BaseActivity;
import com.ntanougat.arcamera.base.BasePresenter;
import com.ntanougat.arcamera.ui.fragment.PhotoInListFragment;

public class MainActivity extends BaseActivity {

    private PhotoInListFragment photoInListFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager=getFragmentManager();
        initView();
        initFragment();

    }

    private void initFragment() {

    }

    private void initView() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
