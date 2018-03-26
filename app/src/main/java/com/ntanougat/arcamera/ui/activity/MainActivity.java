package com.ntanougat.arcamera.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ntanougat.arcamera.R;
import com.ntanougat.arcamera.base.BaseActivity;
import com.ntanougat.arcamera.ui.fragment.MainpageFragment;
import com.ntanougat.arcamera.ui.fragment.UserpageFragment;
import com.ntanougat.arcamera.ui.fragment.WorkspageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tool_bar_activity_main)
    Toolbar toolBarActivityMain;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.frame_fragment)
    FrameLayout frameFragment;
    @BindView(R.id.navigation_bar_main)
    BottomNavigationBar navigationBarMain;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private String TAG = MainActivity.class.getSimpleName();

    private MainpageFragment mainpageFragment;
    private WorkspageFragment workspageFragment;
    private UserpageFragment userpageFragment;
    private FragmentManager fragmentManager;
    private Menu mainMenu;
    private SearchView searchView;
    private int toolbarchange = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "mainactivity start");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        initView();
        initFragment();

    }

    private void initFragment() {
        mainpageFragment = MainpageFragment.newInstance("");
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_fragment, mainpageFragment);
        transaction.commit();

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mainMenu = menu;
        super.onPrepareOptionsMenu(menu);
        if (toolbarchange == 0) {
            menu.clear();
            getMenuInflater().inflate(R.menu.toolbar_mainpage, menu);
            return true;
        } else if (toolbarchange == 1) {
            menu.clear();
            getMenuInflater().inflate(R.menu.toolbar_workspage, menu);
            MenuItem menuItem = menu.findItem(R.id.toolbar_search);
            View view = MenuItemCompat.getActionView(menuItem);
            if (view != null) {
                searchView = (SearchView) view;
                searchView.setOnQueryTextListener(this);
            }
            return true;
        } else {
            menu.clear();
            getMenuInflater().inflate(R.menu.toolbar_userpage, menu);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_about:

                break;
            case R.id.toolbar_exit:

                break;
            default:
                break;
        }
        return true;
    }

    private void initView() {
        setSupportActionBar(toolBarActivityMain);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
//            actionBar.setTitle(intent_name);
        }

        initNavigationBar();

    }

    private void initNavigationBar() {
        navigationBarMain.setActiveColor(R.color.united_color);
        navigationBarMain
                .addItem(new BottomNavigationItem(R.drawable.ic_mainpage, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_listpage, "全部"))
                .addItem(new BottomNavigationItem(R.drawable.ic_userpage, "我的"))
                .initialise();
        Log.i(TAG,"navigationBar init seccess!");
        navigationBarMain.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                changeFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void changeFragment(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                toolbarchange = 0;
                onPrepareOptionsMenu(mainMenu);
                if (mainpageFragment == null) {
                    mainpageFragment = MainpageFragment.newInstance("");
                    transaction.add(R.id.frame_fragment, mainpageFragment);
                } else {
                    transaction.show(mainpageFragment);
                }
                break;
            case 1:
                toolbarchange = 1;
                onPrepareOptionsMenu(mainMenu);
                if (workspageFragment == null) {
                    workspageFragment = WorkspageFragment.newInstance("");
                    transaction.add(R.id.frame_fragment, workspageFragment);
                } else {
                    transaction.show(workspageFragment);
                }
                break;
            case 2:
                toolbarchange = 2;
                onPrepareOptionsMenu(mainMenu);
                if (userpageFragment == null) {
                    userpageFragment = UserpageFragment.newInstance("");
                    transaction.add(R.id.frame_fragment, userpageFragment);
                } else {
                    transaction.show(userpageFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();


    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mainpageFragment != null) {
            transaction.hide(mainpageFragment);
        }
        if (workspageFragment != null) {
            transaction.hide(workspageFragment);
        }
        if (userpageFragment != null) {
            transaction.hide(userpageFragment);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
