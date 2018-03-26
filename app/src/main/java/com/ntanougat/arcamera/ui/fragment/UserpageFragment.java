package com.ntanougat.arcamera.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntanougat.arcamera.R;
import com.ntanougat.arcamera.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by 倪启航 on 2017/11/25.
 */

public class UserpageFragment extends BaseFragment{

    private static final String TAG = UserpageFragment.class.getSimpleName();

    private String param;

    public UserpageFragment(){

    }

    public static UserpageFragment newInstance(String param){
        UserpageFragment userpageFragment=new UserpageFragment();
        Bundle args = new Bundle();
        args.putString("param", param);
        userpageFragment.setArguments(args);
        return userpageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            param = getArguments().getString("param");
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_userpage,container,false);
        ButterKnife.bind(this,v);


        return v;

    }
}
