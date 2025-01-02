package com.shanhai.dataStructe._18graph.shortestPath;

import com.shanhai.dataStructe._18graph.Edge;
import com.shanhai.dataStructe._18graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>迪克斯特拉 单源最短路径算法</h3>
 */
public class Dijkstra {
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

    /*
     * 迪杰斯特拉算法：
     * 1.将所有顶点标记为未访问。创建一个未访问顶点的集合。
     * 2.为每个顶点分配一个临时距离值
     *      2.1 对于我们的初始顶点，将其设置为零
     *      2.2 对于所有其他顶点，将其设置为无穷大。
     * 3.每次选择最小临时距离的未访问顶点，作为新的当前顶点
     * 4.对于当前顶点，遍历其所有未访问的邻居，并更新它们的临时距离为更小
     *      4.1 例如，1->6 的距离是 14，而1->3->6 的距离是11。这时将距离更新为 11
     *      4.2 否则，将保留上次距离值
     * 5.当前顶点的邻居处理完成后，把它从未访问集合中删除
     * */
    private static void dijkstra(List<Vertex> graph, Vertex v) {
        // 1.将所有顶点标记为未访问。创建一个未访问顶点的集合。
        List<Vertex> unVisited = new ArrayList<>(graph);
        // 2.为每个顶点分配一个临时距离值：初始顶点为零；其他顶点为无穷大。
        Map<Vertex, Integer> discMap = new HashMap<>();
        for (Vertex vertex : graph) {
            discMap.put(vertex, Integer.MAX_VALUE);
        }
        discMap.put(v, 0);

        while (!unVisited.isEmpty()) {
            // 3.每次选择最小临时距离的未访问顶点，作为新的当前顶点
            Vertex curr = findMinDiscVertex(unVisited, discMap);
            // 4.对于当前顶点，遍历其所有未访问的邻居，并更新它们的临时距离为更小
            updateNeighbourDist(curr, unVisited, discMap);
            // 5.当前顶点的邻居处理完成后，把它从未访问集合中删除
            unVisited.remove(curr);
        }

        discMap.forEach((vertex, disc) -> {
            printRes(vertex);
            System.out.println("(" + disc + ")");
        });
    }

    private static void updateNeighbourDist(Vertex curr, List<Vertex> unVisited, Map<Vertex, Integer> discMap) {
        for (Edge edge : curr.getEdges()) {
            Vertex linked = edge.getLinked();
            int dist = discMap.get(curr) + edge.getWeight();
            if (unVisited.contains(linked) && dist < discMap.get(linked)) {
                linked.setPrev(curr);
                discMap.put(linked, dist);
            }
        }
    }

    private static Vertex findMinDiscVertex(List<Vertex> list, Map<Vertex, Integer> discMap) {
        Vertex minVertex = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (discMap.get(list.get(i)) < discMap.get(minVertex)) {
                minVertex = list.get(i);
            }
        }
        return minVertex;
    }

    private static void printRes(Vertex vertex) {
        if (vertex.getPrev() != null) {
            printRes(vertex.getPrev());
        }
        System.out.print(vertex.getName() + " --> ");
    }
}
