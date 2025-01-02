package com.shanhai.dataStructe._18graph.traverse;

import com.shanhai.dataStructe._18graph.Edge;
import com.shanhai.dataStructe._18graph.Vertex;

import java.util.*;

public class BFS {
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

        bfs(v1);
    }

    private static void bfs(Vertex v) {
        Set<Vertex> set = new HashSet<>();
        Deque<Vertex> queue = new LinkedList<>();
        queue.offer(v);
        set.add(v);
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            System.out.println(poll.getName());
            for (Edge edge : poll.getEdges()) {
                if (!set.contains(edge.getLinked())) {
                    set.add(edge.getLinked());
                    queue.offer(edge.getLinked());
                }
            }
        }
    }
}
