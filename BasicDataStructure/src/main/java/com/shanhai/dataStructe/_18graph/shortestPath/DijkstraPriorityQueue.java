package com.shanhai.dataStructe._18graph.shortestPath;

import com.shanhai.dataStructe._18graph.Edge;
import com.shanhai.dataStructe._18graph.Vertex;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <h3>迪克斯特拉 单源最短路径算法</h3>
 */
public class DijkstraPriorityQueue {
    public static void main(String[] args) {
        method1();
    }

    private static void method2() {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v3, 1), new Edge(v2, 2));
        v2.edges = List.of(new Edge(v3, -2));
        v3.edges = List.of(new Edge(v4, 1));
        v4.edges = List.of();

        List<Vertex> graph = List.of(v1, v2, v3, v4);

        dijkstra(graph, v1);
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

        dijkstra(graph, v1);
    }

    private static void dijkstra(List<Vertex> graph, Vertex v) {
        // 1.创建一个优先级队列，放入所有顶点（队列大小会达到边的数量）
        PriorityQueue<Vertex> unVisited = new PriorityQueue<>(Comparator.comparingInt(Vertex::getDist));
        // 2.为每个顶点分配一个临时距离值：初始顶点为零；其他顶点为无穷大
        v.setDist(0);
        for (Vertex vertex : graph) {
            unVisited.offer(vertex);
        }

        while (!unVisited.isEmpty()) {
            // 3.每次选择最小临时距离的未访问顶点，作为新的当前顶点
            Vertex curr = unVisited.peek();
            // 4.对于当前顶点，遍历其所有未访问的邻居，并更新它们的临时距离为更小
            updateNeighbourDist(curr, unVisited);
            // 5.当前顶点的邻居处理完成后，把它从未访问集合中删除
            unVisited.poll();
        }

        for (Vertex vertex : graph) {
            printRes(vertex);
            System.out.println(vertex.getDist());
        }
    }

    private static void updateNeighbourDist(Vertex curr, PriorityQueue<Vertex> unVisited) {
        for (Edge edge : curr.getEdges()) {
            Vertex linked = edge.getLinked();
            int dist = curr.getDist() + edge.getWeight();
            if (unVisited.contains(linked) && dist < linked.getDist()) {
                linked.setPrev(curr);
                linked.setDist(dist);
            }
        }
    }

    private static void printRes(Vertex vertex) {
        if (vertex.getPrev() != null) {
            printRes(vertex.getPrev());
        }
        System.out.print(vertex.getName() + " --> ");
    }
}
