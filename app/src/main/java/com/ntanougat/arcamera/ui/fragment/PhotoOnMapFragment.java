package com.ntanougat.arcamera.ui.fragment;

import com.ntanougat.arcamera.base.BaseFragment;
import com.ntanougat.arcamera.contract.PhotoInfoContract;
import com.ntanougat.arcamera.entities.PhotoInfo;
import com.ntanougat.arcamera.presenter.PhotoInfoPresenter;

import java.util.ArrayList;

/**
 * Created by 倪启航 on 2017/11/25.
 * Place PhotoInfo on map.
 */

public class PhotoOnMapFragment extends BaseFragment<PhotoInfoContract.View<PhotoInfo>,PhotoInfoPresenter> implements PhotoInfoContract.View<PhotoInfo> {


    @Override
    public void refreshPhotoInfoList(ArrayList<PhotoInfo> photoInfoList) {

    }

    @Override
    protected PhotoInfoPresenter createPresenter() {
        return null;
    }
}
