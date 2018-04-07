package com.xalanq.coursehelper;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xalanq.xthulib.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: xalanq
 * Email: xalanq@gmail.com
 * CopyRight © 2018 by xalanq. All Rights Reserved.
 */

/**
 * 主界面碎片
 * 用于显示主页（课表）
 */
public class KebiaoFragment extends BasicFragment {

    private CardAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kebiao_fragment, container, false);
        init(view);
        test(view);
        return view;
    }

    private void test(View view) {
        adapter.addNotification("距离一级选课还有1天20小时5分");

        adapter.addDivider("周一");

        CourseBrief course = new CourseBrief();
        course.setValue("name", "大学物理B(1)");
        course.setValue("place", "六教6A414");
        course.setValue("time", "早上9:50");
        course.setValue("teacher", "李列明");
        course.setValue("pid", "123456");
        adapter.addCourse(course);

        course = new CourseBrief();
        course.setValue("name", "面向对象程序设计基础");
        course.setValue("place", "四教4202");
        course.setValue("time", "下午13:30");
        course.setValue("teacher", "黄民烈");
        course.setValue("pid", "123456");
        adapter.addCourse(course);

        adapter.addDivider("周二");

        course = new CourseBrief();
        course.setValue("name", "离散数学(2)");
        course.setValue("place", "六教6A214");
        course.setValue("time", "早上9:50");
        course.setValue("teacher", "张小平");
        course.setValue("pid", "123456");
        adapter.addCourse(course);

        course = new CourseBrief();
        course.setValue("name", "线性代数(2)");
        course.setValue("place", "五教5104");
        course.setValue("time", "下午13:30");
        course.setValue("teacher", "鲁自群");
        course.setValue("pid", "123456");
        adapter.addCourse(course);

        adapter.addDivider("周三");

        course = new CourseBrief();
        course.setValue("name", "大学物理B(1)");
        course.setValue("place", "六教6A414");
        course.setValue("time", "早上8:00");
        course.setValue("teacher", "李列明");
        course.setValue("pid", "123456");
        adapter.addCourse(course);

        course = new CourseBrief();
        course.setValue("name", "微积分A(2)");
        course.setValue("place", "六教6C202");
        course.setValue("time", "早上9:50");
        course.setValue("teacher", "姚家燕");
        course.setValue("pid", "123456");
        adapter.addCourse(course);

        course = new CourseBrief();
        course.setValue("name", "中国近代史纲要");
        course.setValue("place", "五教5202");
        course.setValue("time", "下午13:30");
        course.setValue("teacher", "李蕉");
        course.setValue("pid", "123456");
        adapter.addCourse(course);

        adapter.addDivider("周四");

        course = new CourseBrief();
        course.setValue("name", "基础英语读写(2)");
        course.setValue("place", "紫荆C楼212");
        course.setValue("time", "早上8:00");
        course.setValue("teacher", "刘延");
        course.setValue("pid", "123456");
        adapter.addCourse(course);

        adapter.addDivider("周五");

        course = new CourseBrief();
        course.setValue("name", "微积分A(2)");
        course.setValue("place", "六教6C202");
        course.setValue("time", "早上8:00");
        course.setValue("teacher", "姚家燕");
        course.setValue("pid", "123456");
        adapter.addCourse(course);

        course = new CourseBrief();
        course.setValue("name", "美式英语语音");
        course.setValue("place", "六教6A103");
        course.setValue("time", "早上9:50");
        course.setValue("teacher", "Yariv Pomeranz");
        course.setValue("pid", "123456");
        adapter.addCourse(course);
    }

    private void updateData() {
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setTitle(R.string.kebiao_dialog_update_title);
        dialog.setMessage(getString(R.string.main_updating));
        dialog.setCancelable(false);
        dialog.show();
        Toast.makeText(BasicApplication.getContext(), R.string.kebiao_dialog_update_title, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
            }
        }, 4000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.button_refresh) {
            new AlertDialog.Builder(getActivity())
                .setTitle(R.string.kebiao_dialog_update_title)
                .setMessage(R.string.kebiao_dialog_update_message)
                .setPositiveButton(R.string.kebiao_dialog_update_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateData();
                    }
                })
                .setNegativeButton(R.string.kebiao_dialog_update_negative, null)
                .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.kebiao_toolbar, menu);
        for (int i = 0; i < menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            Drawable drawable = item.getIcon();
            if (drawable != null) {
                drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            }
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void init(View view) {
        adapter = new CardAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.kebiao_content_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    public static class CourseBrief extends Course {

        /**
         * 值对
         * name: 课程名
         * place: 上课地点
         * time: 时间
         * teacher: 授课教师
         * pid: 课程号id
         */
        HashMap<String, String> values;

        public static CourseBrief fromCourse(Course course) {
            CourseBrief courseBrief = new CourseBrief();
            return courseBrief;
        }

    }

    public static class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

        public static final int EMPTY = 0;
        public static final int COURSE = 1;
        public static final int DIVIDER = 2;
        public static final int NOTIFICATION = 3;

        public static class Data {
            CourseBrief course;
            String divider;
            String notification;
            public Data() {
                course = null;
                divider = null;
                notification = null;
            }
            public Data(CourseBrief course) {
                this.course = course;
                divider = null;
                notification = null;
            }
            public static Data fromDivider(String divider) {
                Data data = new Data();
                data.divider = divider;
                data.course = null;
                data.notification = null;
                return data;
            }
            public static Data fromNotification(String notification) {
                Data data = new Data();
                data.notification = notification;
                data.course = null;
                data.divider = null;
                return data;
            }
        }
        
        List<Data> data;

        public CardAdapter() {
            data = new ArrayList<>();
        }

        public CardAdapter(List<Data> courseBriefList) {
            data = courseBriefList;
        }

        /**
         * 在第position个位置插入course
         * @param course 课程
         * @param position 插入位置
         */
        void addCourse(CourseBrief course, int position) {
            data.add(position, new Data(course));
            notifyItemInserted(position);
            notifyItemRangeChanged(position, data.size());
        }

        void addCourse(CourseBrief course) {
            addCourse(course, data.size());
        }

        void addDivider(String divider, int position) {
            data.add(position, Data.fromDivider(divider));
            notifyItemInserted(position);
            notifyItemRangeChanged(position, data.size());
        }

        void addDivider(String divider) {
            addDivider(divider, data.size());
        }

        void addNotification(String notification, int position) {
            data.add(position, Data.fromNotification(notification));
            notifyItemInserted(position);
            notifyItemRangeChanged(position, data.size());
        }

        void addNotification(String notification) {
            addNotification(notification, data.size());
        }

        /**
         * 在第position个位置删除一门course
         * @param position 删除位置
         */
        void remove(int position) {
            data.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, data.size());
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        public static class CourseHolder extends ViewHolder {

            @BindView(R.id.course_name) TextView course_name;
            @BindView(R.id.course_place) TextView course_place;
            @BindView(R.id.course_time) TextView course_time;
            @BindView(R.id.course_teacher) TextView course_teacher;

            public CourseHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                tint(course_place);
                tint(course_time);
                tint(course_teacher);
            }

            private void tint(TextView view) {
                Drawable[] drawables = view.getCompoundDrawables();
                for (Drawable drawable : drawables) {
                    if (drawable != null) {
                        drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
                    }
                }
            }
        }

        public static class DividerHolder extends ViewHolder {

            @BindView(R.id.divider_text) TextView textView;

            public DividerHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        public static class NotificationHolder extends ViewHolder {

            @BindView(R.id.notification_text) TextView textView;

            public NotificationHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        @Override
        public int getItemViewType(int position) {
            Data d = data.get(position);
            if (d.course != null)
                return COURSE;
            if (d.divider != null)
                return DIVIDER;
            if (d.notification != null)
                return NOTIFICATION;
            return EMPTY;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            if (viewType == COURSE) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kebiao_course_card, parent, false);
                final CourseHolder holder = new CourseHolder(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(BasicApplication.getContext(), "Click Course", Toast.LENGTH_SHORT).show();
                    }
                });
                return holder;

            }
            else if (viewType == DIVIDER) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.divider_with_text, parent, false);
                final DividerHolder holder = new DividerHolder(view);
                return holder;
            }
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card, parent, false);
            final NotificationHolder holder = new NotificationHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder _holder, int position) {
            Data d = data.get(position);
            if (_holder instanceof CourseHolder) {
                CourseHolder holder = (CourseHolder) _holder;
                holder.course_name.setText(d.course.getValue("name"));
                holder.course_place.setText(d.course.getValue("place"));
                holder.course_time.setText(d.course.getValue("time"));
                holder.course_teacher.setText(d.course.getValue("teacher"));
            }
            else if (_holder instanceof DividerHolder) {
                DividerHolder holder = (DividerHolder) _holder;
                holder.textView.setText(d.divider);
            }
            else if (_holder instanceof NotificationHolder) {
                NotificationHolder holder = (NotificationHolder) _holder;
                holder.textView.setText(d.notification);
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
