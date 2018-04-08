package com.xalanq.coursehelper;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xalanq.xthulib.AuthAcademic;
import com.xalanq.xthulib.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: xalanq
 * Email: xalanq@gmail.com
 * CopyRight © 2018 by xalanq. All Rights Reserved.
 */

public class LoginDialog extends Dialog {

    @BindView(R.id.title) TextView title;
    @BindView(R.id.username) TextView usernameView;
    @BindView(R.id.password) TextView passwordView;
    @BindView(R.id.message) TextView messageView;
    @BindView(R.id.sign_in) Button button;
    private TextView hasToChange;

    public LoginDialog(@NonNull Context context, TextView username) {
        super(context);
        setContentView(R.layout.login);

        hasToChange = username;

        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(false);
            }
        });
    }

    private void login(final boolean hidden) {
        final String username = usernameView.getText().toString();
        final String password = passwordView.getText().toString();
        View focusView = null;
        messageView.setVisibility(View.GONE);
        messageView.setText(null);
        if (TextUtils.isEmpty(password)) {
            passwordView.setError(getContext().getString(R.string.login_password_empty));
            focusView = passwordView;
        }

        if (TextUtils.isEmpty(username)) {
            usernameView.setError(getContext().getString(R.string.login_username_empty));
            focusView = usernameView;
        }

        if (focusView != null) {
            focusView.requestFocus();
        } else {
            final ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setTitle(R.string.login_title);
            dialog.setMessage(getContext().getString(R.string.login_ing));
            dialog.setCancelable(false);
            new AsyncTask<Void, Integer, Boolean>() {

                private int successful;
                private String realname;

                @Override
                protected void onPreExecute() {
                    if (!hidden)
                        dialog.show();
                }

                @Override
                protected Boolean doInBackground(Void... voids) {
                    try {
                        if (AuthAcademic.auth(username, password)) {
                            successful = 1;
                            realname = UserInfo.getInfo().get("realname");
                        }
                        else
                            successful = 0;
                    } catch (Exception e) {
                        successful = -1;
                    }
                    return true;
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    if (successful == 0) {
                        messageView.setVisibility(View.VISIBLE);
                        messageView.setText(R.string.login_fail);
                        passwordView.requestFocus();
                        if (hidden) {
                            Toast.makeText(getContext(), R.string.login_fail, Toast.LENGTH_SHORT).show();
                        }
                    } else if (successful == -1) {
                        messageView.setVisibility(View.VISIBLE);
                        messageView.setText(R.string.login_network);
                        if (hidden) {
                            Toast.makeText(getContext(), R.string.login_network, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        LoginDialog.this.cancel();
                        hasToChange.setText(
                            getContext().getString(R.string.main_navigation_username1) +
                            realname +
                            getContext().getString(R.string.main_navigation_username2)
                        );
                        Toast.makeText(getContext(), R.string.login_success, Toast.LENGTH_SHORT).show();
                    }
                    if (!hidden)
                        dialog.cancel();
                }

            }.execute();
        }
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        title.setText(titleId);
    }

}
