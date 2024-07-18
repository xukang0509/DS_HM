package com.shanhai.dataStructe._17sort;

public class SortedResult implements Comparable<SortedResult> {
    private String methodName;
    private Long cmpCount;
    private Long swapCount;
    private Long expendTime;

    public SortedResult(String methodName, long cmpCount, long swapCount, long expendTime) {
        this.methodName = methodName;
        this.cmpCount = cmpCount;
        this.swapCount = swapCount;
        this.expendTime = expendTime;
    }

    @Override
    public int compareTo(SortedResult o) {
        return Long.compare(this.expendTime, o.expendTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-26s", methodName)).append(" | \t");
        sb.append(String.format("%12d", cmpCount)).append(" | \t\t");
        sb.append(String.format("%12d", swapCount)).append(" | \t");
        sb.append(String.format("%9d", expendTime));
        return sb.toString();
    }
}
