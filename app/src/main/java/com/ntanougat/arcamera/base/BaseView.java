package com.ntanougat.arcamera.base;

/**
 * Created by 倪启航 on 2017/11/25.
 * View的基类
 */

public interface BaseView<T> {

    //在Activity中包含Fragment的时候使用，这时候继承BaseActivity
    //Activity中初始化Presenter的实例，然后通过view调用该方法将Presenter注入给Fragment
    void setPresenter(T presenter);
}
