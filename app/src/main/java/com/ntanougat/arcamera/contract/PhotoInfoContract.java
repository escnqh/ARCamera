package com.ntanougat.arcamera.contract;

import com.ntanougat.arcamera.base.BaseContract;

import java.util.ArrayList;

/**
 * Created by 倪启航 on 2017/11/25.
 */

public class PhotoInfoContract extends BaseContract {
    public interface Model{

        void getPhotoInfo();
    }

    public interface View<T>{

        void refreshPhotoInfoList(ArrayList<T> photoInfoList);
    }

    public interface Presenter{

        void requestRefresh();

    }
}
