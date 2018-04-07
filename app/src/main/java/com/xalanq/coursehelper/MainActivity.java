package com.xalanq.coursehelper;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主要活动
 */
public class MainActivity extends BasicActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.main_layout_drawer) DrawerLayout drawerLayout;
    @BindView(R.id.main_layout_content) LinearLayout contentLayout;
    @BindView(R.id.main_toolbar) Toolbar toolbar;
    @BindView(R.id.main_toolbar_title) TextView toolbarTitle;
    @BindView(R.id.main_navigationView) NavigationView navigationView;

    private String TAG = "MainActivity";
    private FragmentAllocator fragmentAllocator;
    private BasicFragment currentFragment;
    private boolean doubleClickToExitPressOnce = false;
    private LoginDialog loginDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initValues();
        setToolbar();
        setNavigation();

        switchFragment(fragmentAllocator.getKebiao());
    }

    private void initValues() {
        ButterKnife.bind(this);
        fragmentAllocator = new FragmentAllocator();
    }

    private void setNavigation() {
        navigationView.getMenu().findItem(R.id.main_navigation_kebiao).setChecked(true);
        final TextView username = navigationView.getHeaderView(0).findViewById(R.id.main_navigation_username);
        if (loginDialog != null && loginDialog.isActive())
            username.setText(getString(R.string.main_navigation_username1) + loginDialog.getUsername() + getString(R.string.main_navigation_username2));
        else
            username.setText(R.string.main_navigation_login);
        username.setClickable(true);
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginDialog == null || !loginDialog.isActive()) {
                    if (loginDialog == null) {
                        loginDialog = new LoginDialog(MainActivity.this);
                        loginDialog.setTitle(R.string.login_title);
                    }
                    loginDialog.show();
                }
                else {
                    new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.main_navigation_logout)
                        .setMessage(R.string.logout_message)
                        .setPositiveButton(R.string.logout_positive, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                loginDialog = null;
                                username.setText(R.string.main_navigation_login);
                            }
                        })
                        .setNegativeButton(R.string.logout_negative, null)
                        .show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.main_navigation_drawer_open, R.string.main_navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void switchFragment(BasicFragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded())
            fragmentTransaction.add(R.id.main_layout_content, fragment);
        if (currentFragment != null)
            fragmentTransaction.hide(currentFragment);
        currentFragment = fragment;
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
        toolbarTitle.setText(fragment.getName());
    }

    private void updateData() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle(R.string.main_dialog_update_title);
        dialog.setMessage(getString(R.string.main_updating));
        dialog.setCancelable(false);
        dialog.show();
        Toast.makeText(BasicApplication.getContext(), R.string.main_dialog_update_title, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
            }
        }, 4000);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            if (doubleClickToExitPressOnce) {
                super.onBackPressed();
                return;
            }
            doubleClickToExitPressOnce = true;
            Toast.makeText(this, R.string.main_double_click_to_exit, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.this.doubleClickToExitPressOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_navigation_kebiao) {
            switchFragment(fragmentAllocator.getKebiao());
        }
        else if (id == R.id.main_navigation_about) {
            switchFragment(fragmentAllocator.getAbout());
        }
        else if (id == R.id.main_navigation_update) {
            new AlertDialog.Builder(this)
                .setTitle(R.string.main_dialog_update_title)
                .setMessage(R.string.main_dialog_update_message)
                .setPositiveButton(R.string.main_dialog_update_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateData();
                    }
                })
                .setNegativeButton(R.string.main_dialog_update_negative, null)
                .show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
