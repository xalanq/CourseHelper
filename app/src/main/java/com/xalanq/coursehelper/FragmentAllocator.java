package com.xalanq.coursehelper;

/**
 * Author: xalanq
 * Email: xalanq@gmail.com
 * CopyRight © 2018 by xalanq. All Rights Reserved.
 */

public class FragmentAllocator {

    private MainFragment mainFragment;
    private AboutFragment aboutFragment;

    public MainFragment getMain() {
        if (mainFragment == null) {
            mainFragment = new MainFragment();
            mainFragment.setName(R.string.main_navigation_menu_kebiao);
        }
        return mainFragment;
    }

    public AboutFragment getAbout() {
        if (aboutFragment == null) {
            aboutFragment = new AboutFragment();
            aboutFragment.setName(R.string.main_navigation_menu_about);
        }
        return aboutFragment;
    }

}
