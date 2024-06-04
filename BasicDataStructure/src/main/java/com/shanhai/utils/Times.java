package com.shanhai.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: xu
 * @date: 2024/5/11 23:09
 */
public class Times {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    public static void test(String title, Task task) {
        if (task == null) return;
        title = (title == null) ? "" : ("【" + title + "】");
        System.out.println(title);
        System.out.println("开始：" + sdf.format(new Date()));
        long begin = System.currentTimeMillis(); // 开始时间
        task.execute(); // 执行代码
        long end = System.currentTimeMillis(); // 结束时间
        System.out.println("结束：" + sdf.format(new Date()));
        double delta = (end - begin) / 1000.0; // 毫秒转换为秒
        System.out.println("耗时：" + delta + "秒");
        System.out.println("-------------------------------------");
    }

    public interface Task {
        void execute();
    }
}
