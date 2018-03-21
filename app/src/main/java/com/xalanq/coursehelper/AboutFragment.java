package com.xalanq.coursehelper;

/**
 * Author: xalanq
 * Email: xalanq@gmail.com
 * CopyRight © 2018 by xalanq. All Rights Reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 显示作者信息的碎片
 */
public class AboutFragment extends BasicFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, container, false);
        TextView author = view.findViewById(R.id.about_author);
        author.setText(R.string.about_author);
        TextView github = view.findViewById(R.id.about_github);
        github.setText(R.string.about_github);
        github.setMovementMethod(LinkMovementMethod.getInstance());
        return view;
    }
}
