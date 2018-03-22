package com.xalanq.coursehelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xalanq.xthulib.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kebiao_fragment, container, false);
        init(view);
        test(view);
        return view;
    }

    private void test(View view) {
        // notification = view.findViewById(R.id.main_content_notification);
        // notification.setText("离一级选课还有1天5小时2分");

        List<CourseBrief> list = new ArrayList<>();
        CourseBrief course = new CourseBrief();
        course.setValue("name", "大学物理（一）");
        course.setValue("place", "六教6A414");
        course.setValue("time", "早上9:50");
        course.setValue("teacher", "李列明");
        course.setValue("pid", "123456");

        for (int i = 0; i < 10; ++i)
            list.add(course);

        CardAdapter adapter = new CardAdapter(list);
        RecyclerView recyclerView = view.findViewById(R.id.kebiao_content_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void init(View view) {
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

        List<CourseBrief> data;

        public CardAdapter() {
            data = new ArrayList<>();
        }

        public CardAdapter(List<CourseBrief> courseBriefList) {
            data = courseBriefList;
        }

        /**
         * 在第position个位置插入course
         * @param course 课程
         * @param position 插入位置
         */
        void add(CourseBrief course, int position) {
            data.add(position, course);
            notifyItemInserted(position);
            notifyItemRangeChanged(position, data.size());
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

            TextView course_name;
            TextView course_place;
            TextView course_time;
            TextView course_teacher;

            public ViewHolder(View view) {
                super(view);
                course_name = view.findViewById(R.id.course_name);
                course_place = view.findViewById(R.id.course_place);
                course_time = view.findViewById(R.id.course_time);
                course_teacher = view.findViewById(R.id.course_teacher);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kebiao_course_card, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            CourseBrief course = data.get(position);
            holder.course_name.setText(course.getValue("name"));
            holder.course_place.setText(course.getValue("place"));
            holder.course_time.setText(course.getValue("time"));
            holder.course_teacher.setText(course.getValue("teacher"));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
