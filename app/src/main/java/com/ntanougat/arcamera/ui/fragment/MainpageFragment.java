package com.ntanougat.arcamera.ui.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ntanougat.arcamera.R;
import com.ntanougat.arcamera.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by 倪启航 on 2017/11/25.
 */

public class MainpageFragment extends BaseFragment{

    private String param;

    public MainpageFragment(){}

    public static MainpageFragment newInstance(String param){
        MainpageFragment fragment=new MainpageFragment();
        Bundle args = new Bundle();
        args.putString("param", param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(getArguments()!=null){
            param=getArguments().getString("param");
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mainpage,container,false);
        ButterKnife.bind(this,v);
        initView(v);
        return v;
    }

    private void initView(View v) {

    }
}
