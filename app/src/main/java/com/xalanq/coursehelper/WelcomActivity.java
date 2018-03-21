package com.xalanq.coursehelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Author: xalanq
 * Email: xalanq@gmail.com
 * CopyRight © 2018 by xalanq. All Rights Reserved.
 */

/**
 * 启动动画
 */
public class WelcomActivity extends BasicActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
