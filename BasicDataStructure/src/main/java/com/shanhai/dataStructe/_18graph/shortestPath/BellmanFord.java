package com.shanhai.dataStructe._18graph.shortestPath;

import com.shanhai.dataStructe._18graph.Edge;
import com.shanhai.dataStructe._18graph.Vertex;

import java.util.List;

/**
 * <h3>Bellman-Ford 算法，可以处理负边</h3>
 */
public class BellmanFord {
    public static void main(String[] args) {
        method1();
    }

    private static void method2() {
        // 负边情况
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v3, 1), new Edge(v2, 2));
        v2.edges = List.of(new Edge(v3, -2));
        v3.edges = List.of(new Edge(v4, 1));
        v4.edges = List.of();

        List<Vertex> graph = List.of(v1, v2, v3, v4);

        bellmanFord(graph, v1);
    }

    private static void method1() {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(new Edge(v3, 9), new Edge(v2, 7), new Edge(v6, 14));
        v2.edges = List.of(new Edge(v4, 15));
        v3.edges = List.of(new Edge(v4, 11), new Edge(v6, 2));
        v4.edges = List.of(new Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5, 9));

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6);

        bellmanFord(graph, v1);
    }

    private static void bellmanFord(List<Vertex> graph, Vertex source) {
        int size = graph.size();
        source.setDist(0);
        // 进行 顶点个数-1 轮处理
        for (int i = 0; i < size - 1; i++) {
            // 遍历所有的边
            for (Vertex start : graph) {
                for (Edge edge : start.getEdges()) {
                    // start ---> edge ---> end
                    // 处理每一条边
                    // 如果 start.dist + edge.weight < end.dist；更新 end.dist
                    Vertex end = edge.getLinked();
                    if (start.getDist() != Integer.MAX_VALUE && start.getDist() + edge.getWeight() < end.getDist()) {
                        end.setPrev(start);
                        end.setDist(start.getDist() + edge.getWeight());
                    }
                }
            }
        }

        for (Vertex vertex : graph) {
            printRes(vertex);
            System.out.println(vertex.getDist());
        }
    }

    private static void printRes(Vertex vertex) {
        if (vertex.getPrev() != null) {
            printRes(vertex.getPrev());
        }
        System.out.print(vertex.getName() + " --> ");
    }
}
