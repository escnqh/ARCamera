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
 * Created by ESCNQH on 2018/3/26.
 */

public class WorkspageFragment extends BaseFragment {

    private static final String TAG = WorkspageFragment.class.getSimpleName();
    private String param;
    public WorkspageFragment(){

    }

    public static WorkspageFragment newInstance(String param){
        WorkspageFragment workspageFragment=new WorkspageFragment();
        Bundle args = new Bundle();
        args.putString("param", param);
        workspageFragment.setArguments(args);
        return workspageFragment;
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
        View v=inflater.inflate(R.layout.fragment_workspage,container,false);
        return v;
    }
}
