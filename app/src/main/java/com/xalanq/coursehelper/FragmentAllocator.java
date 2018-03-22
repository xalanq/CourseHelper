package com.xalanq.coursehelper;

/**
 * Author: xalanq
 * Email: xalanq@gmail.com
 * CopyRight © 2018 by xalanq. All Rights Reserved.
 */

public class FragmentAllocator {

    private KebiaoFragment kebiaoFragment;
    private AboutFragment aboutFragment;

    public KebiaoFragment getKebiao() {
        if (kebiaoFragment == null) {
            kebiaoFragment = new KebiaoFragment();
            kebiaoFragment.setName(R.string.main_navigation_menu_kebiao);
        }
        return kebiaoFragment;
    }

    public AboutFragment getAbout() {
        if (aboutFragment == null) {
            aboutFragment = new AboutFragment();
            aboutFragment.setName(R.string.main_navigation_menu_about);
        }
        return aboutFragment;
    }

}
