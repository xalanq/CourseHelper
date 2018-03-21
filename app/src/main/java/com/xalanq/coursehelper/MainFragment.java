package com.xalanq.coursehelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Author: xalanq
 * Email: xalanq@gmail.com
 * CopyRight © 2018 by xalanq. All Rights Reserved.
 */

/**
 * 主界面碎片
 * 用于显示主页（课表）
 */
public class MainFragment extends BasicFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        TextView textView = view.findViewById(R.id.main_textView);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5000; ++i)
            builder.append("test");
        textView.setText(builder.toString());
        return view;
    }
}
