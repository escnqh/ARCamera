package com.ntanougat.arcamera.presenter;

import com.ntanougat.arcamera.base.BaseContract;
import com.ntanougat.arcamera.base.BasePresenter;
import com.ntanougat.arcamera.contract.PhotoInfoContract;
import com.ntanougat.arcamera.entities.PhotoInfo;

/**
 * Created by 倪启航 on 2017/11/25.
 */

public class PhotoInListPresenter extends BasePresenter<PhotoInfoContract.View<PhotoInfo>> implements PhotoInfoContract.Presenter{

    private PhotoInfoContract.View<PhotoInfo> mView;
    private PhotoInfoContract.Model mModel;


    @Override
    public void requestRefresh() {

    }
}
