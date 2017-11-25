package com.ntanougat.arcamera.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by 倪启航 on 2017/11/21.
 * 所有Presenter的基类
 */

public abstract class BasePresenter<T> {
    protected Reference<T> mViewRef;

    //与View建立关系
    public void attachView(T view){
        mViewRef=new WeakReference<T>(view);
    }

    //获取View
    protected T getView(){
        return mViewRef.get();
    }

    //判断是否与View建立了关系
    public boolean isViewAttached(){
        return mViewRef!=null&&mViewRef.get()!=null;
    }

    //与View解除关系
    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef=null;
        }
    }

}
