package com.shanhai.algorithm.greedy;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ActivitySelectionProblem {
    public static void main(String[] args) {
        //Activity[] activities = new Activity[]{
        //        new Activity(0, 1, 3),
        //        new Activity(1, 2, 4),
        //        new Activity(2, 3, 5),
        //};

        Activity[] activities = new Activity[]{
                new Activity(0, 1, 2),
                new Activity(1, 3, 4),
                new Activity(2, 0, 6),
                new Activity(3, 5, 7),
                new Activity(4, 8, 9),
                new Activity(5, 5, 9)
        };
        select(activities, activities.length);
    }

    private static void select(Activity[] activities, int length) {
        List<Activity> result = new ArrayList<>();
        int i = 0, j;
        result.add(activities[i]);
        for (j = 1; j < length; j++) {
            if (activities[i].getFinish() <= activities[j].getStart()) {
                result.add(activities[j]);
                i = j;
            }
        }

        result.forEach(System.out::println);
    }

    @Data
    private static class Activity {
        int index;
        int start;
        int finish;

        public Activity(int index, int start, int finish) {
            this.index = index;
            this.start = start;
            this.finish = finish;
        }

        @Override
        public String toString() {
            return "Activity(" + index + ")";
        }
    }
}
