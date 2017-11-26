package com.ntanougat.arcamera.presenter;

import com.ntanougat.arcamera.base.BasePresenter;
import com.ntanougat.arcamera.contract.PhotoInfoContract;
import com.ntanougat.arcamera.entities.PhotoInfo;
import com.ntanougat.arcamera.models.PhotoInfoModel;
import com.ntanougat.arcamera.ui.fragment.PhotoInListFragment;

/**
 * Created by 倪启航 on 2017/11/25.
 */

public class PhotoInfoPresenter extends BasePresenter<PhotoInfoContract.View<PhotoInfo>> implements PhotoInfoContract.Presenter{

    private PhotoInfoContract.View<PhotoInfo> mView;
    private PhotoInfoContract.Model mModel;
    private String param;

    public PhotoInfoPresenter(String param, PhotoInfoContract.View<PhotoInfo> view) {
        this.param=param;
        this.mView=view;
        this.mModel=new PhotoInfoModel();

    }


    @Override
    public void requestRefresh() {

    }
}
