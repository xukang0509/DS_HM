package com.shanhai.dataStructe._18graph.topologicalSort;

import com.shanhai.dataStructe._18graph.Edge;
import com.shanhai.dataStructe._18graph.Vertex;

import java.util.*;

public class TopologicalSortDFS {
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

        // 0-未被访问，1-正在访问，2-已经访问过
        Map<Vertex, Integer> visitMap = new HashMap<>();
        for (Vertex vertex : graph) {
            visitMap.put(vertex, 0);
        }
        Deque<String> stack = new LinkedList<>();
        for (Vertex vertex : graph) {
            dfs(vertex, stack, visitMap);
        }
        stack.forEach(System.out::println);
    }

    private static void dfs(Vertex vertex, Deque<String> stack, Map<Vertex, Integer> visitMap) {
        Integer status = visitMap.get(vertex);
        if (status == 2) return;
        if (status == 1) System.out.println("发现了环");
        visitMap.put(vertex, 1);
        for (Edge edge : vertex.getEdges()) {
            dfs(edge.getLinked(), stack, visitMap);
        }
        visitMap.put(vertex, 2);
        stack.push(vertex.getName());
    }
}
