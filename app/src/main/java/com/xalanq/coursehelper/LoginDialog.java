package com.xalanq.coursehelper;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: xalanq
 * Email: xalanq@gmail.com
 * CopyRight © 2018 by xalanq. All Rights Reserved.
 */

public class LoginDialog extends Dialog {

    @BindView(R.id.title) TextView title;

    boolean active;
    String username;

    public LoginDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.login);

        ButterKnife.bind(this);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        title.setText(titleId);
    }

    public boolean isActive() {
        return active;
    }

    public String getUsername() {
        return username;
    }
}
