package com.xalanq.coursehelper;

import android.app.Fragment;

/**
 * Author: xalanq
 * Email: xalanq@gmail.com
 * CopyRight © 2018 by xalanq. All Rights Reserved.
 */

/**
 * 基础碎片类
 */
public class BasicFragment extends Fragment {

    private int name;

    public BasicFragment() {
        super();
    }

    void setName(int name) {
        this.name = name;
    }

    int getName() {
        return name;
    }
}
