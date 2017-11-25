package com.ntanougat.arcamera.ui.fragment;

import com.ntanougat.arcamera.base.BaseFragment;
import com.ntanougat.arcamera.contract.PhotoInfoContract;
import com.ntanougat.arcamera.entities.PhotoInfo;
import com.ntanougat.arcamera.presenter.PhotoInListPresenter;
import com.ntanougat.arcamera.ui.listener.RefreshPhotoInfoListener;

import java.util.ArrayList;

/**
 * Created by 倪启航 on 2017/11/25.
 */

public class PhotoInListFragment extends BaseFragment<PhotoInfoContract.View<PhotoInfo>,PhotoInListPresenter> implements PhotoInfoContract.View<PhotoInfo>,RefreshPhotoInfoListener{


    @Override
    public void onPhotoInfoRefresh() {

    }

    @Override
    public void refreshPhotoInfoList(ArrayList<PhotoInfo> photoInfoList) {

    }

    @Override
    protected PhotoInListPresenter createPresenter() {
        return null;
    }
}
