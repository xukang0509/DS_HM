package com.shanhai.dataStructe._18graph.topologicalSort;

import com.shanhai.dataStructe._18graph.Edge;
import com.shanhai.dataStructe._18graph.Vertex;

import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("网页基础");
        Vertex v2 = new Vertex("Java基础");
        Vertex v3 = new Vertex("JavaWeb");
        Vertex v4 = new Vertex("Spring框架");
        Vertex v5 = new Vertex("微服务框架");
        Vertex v6 = new Vertex("数据库");
        Vertex v7 = new Vertex("实战项目");

        v1.edges = List.of(new Edge(v3)); // +1
        v2.edges = List.of(new Edge(v3)); // +1
        v3.edges = List.of(new Edge(v4));
        v6.edges = List.of(new Edge(v4));
        v4.edges = List.of(new Edge(v5));
        v5.edges = List.of(new Edge(v7));
        v7.edges = List.of();
        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6, v7);

        // 统计每个顶点的入度
        Map<Vertex, Integer> inDegreeMap = new HashMap<>();
        for (Vertex vertex : graph) {
            inDegreeMap.put(vertex, 0);
        }
        for (Vertex vertex : graph) {
            for (Edge edge : vertex.getEdges()) {
                inDegreeMap.put(edge.getLinked(), inDegreeMap.get(edge.getLinked()) + 1);
            }
        }
        // 将入度为0的节点加入队列
        Deque<Vertex> vertexDeque = new LinkedList<>();
        inDegreeMap.forEach((vertex, inDegree) -> {
            if (inDegree == 0) vertexDeque.offer(vertex);
        });
        // 队列中不断移除顶点，每移除一个顶点，把它相邻顶点入度减一，若减到0则入队
        List<String> result = new ArrayList<>();
        while (!vertexDeque.isEmpty()) {
            Vertex poll = vertexDeque.poll();
            result.add(poll.getName());
            for (Edge edge : poll.getEdges()) {
                inDegreeMap.put(edge.getLinked(), inDegreeMap.get(edge.getLinked()) - 1);
                if (inDegreeMap.get(edge.getLinked()) == 0) {
                    vertexDeque.offer(edge.getLinked());
                }
            }
        }
        if (result.size() != graph.size()) {
            System.out.println("出现了环");
        } else {
            result.forEach(System.out::println);
        }
    }
}
