package com.shanhai.dataStructe._18graph.traverse;

import com.shanhai.dataStructe._18graph.Edge;
import com.shanhai.dataStructe._18graph.Vertex;

import java.util.*;

public class DFS {
    private static Set<Vertex> set = new HashSet<>();

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(new Edge(v3), new Edge(v2), new Edge(v6));
        v2.edges = List.of(new Edge(v4));
        v3.edges = List.of(new Edge(v4), new Edge(v6));
        v4.edges = List.of(new Edge(v5));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5));

        dfs2(v1);
    }

    private static void dfs2(Vertex v) {
        Deque<Vertex> stack = new LinkedList<>();
        stack.push(v);
        while (!stack.isEmpty()) {
            Vertex pop = stack.pop();
            System.out.println(pop.getName());
            set.add(pop);
            for (Edge edge : pop.getEdges()) {
                if (!set.contains(edge.getLinked())) {
                    stack.push(edge.getLinked());
                }
            }
        }
    }

    private static void dfs1(Vertex v) {
        set.add(v);
        System.out.println(v.getName());
        for (Edge edge : v.edges) {
            if (!set.contains(edge.getLinked())) {
                dfs1(edge.getLinked());
            }
        }
    }
}
