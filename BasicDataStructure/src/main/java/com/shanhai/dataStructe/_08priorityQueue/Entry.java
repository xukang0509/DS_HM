package com.shanhai.dataStructe._08priorityQueue;

import lombok.Data;

import java.util.Objects;

@Data
public class Entry implements Priority {
    String value;
    int priority;

    public Entry(int priority) {
        this.priority = priority;
    }

    public Entry(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "value='" + value + '\'' +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry entry)) return false;
        return getPriority() == entry.getPriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPriority());
    }
}
