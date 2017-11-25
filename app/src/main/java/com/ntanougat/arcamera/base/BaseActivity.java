package com.ntanougat.arcamera.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;

/**
 * Created by 倪启航 on 2017/11/21.
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends Activity {
    protected T mPresenter;
    private static final String TAG = "BaseActivity";
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();
}
