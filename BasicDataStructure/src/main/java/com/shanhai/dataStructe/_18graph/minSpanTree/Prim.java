package com.shanhai.dataStructe._18graph.minSpanTree;

import com.shanhai.dataStructe._18graph.Edge;
import com.shanhai.dataStructe._18graph.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>最小生成树 - Prim 算法</h3>
 */
public class Prim {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");

        v1.edges = List.of(new Edge(v2, 2), new Edge(v3, 4), new Edge(v4, 1));
        v2.edges = List.of(new Edge(v1, 2), new Edge(v4, 3), new Edge(v5, 10));
        v3.edges = List.of(new Edge(v1, 4), new Edge(v4, 2), new Edge(v6, 5));
        v4.edges = List.of(new Edge(v1, 1), new Edge(v2, 3), new Edge(v3, 2),
                new Edge(v5, 7), new Edge(v6, 8), new Edge(v7, 4));
        v5.edges = List.of(new Edge(v2, 10), new Edge(v4, 7), new Edge(v7, 6));
        v6.edges = List.of(new Edge(v3, 5), new Edge(v4, 8), new Edge(v7, 1));
        v7.edges = List.of(new Edge(v4, 4), new Edge(v5, 6), new Edge(v6, 1));

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6, v7);

        prim(graph, v1);
    }

    private static void prim(List<Vertex> graph, Vertex source) {
        List<Vertex> unVisited = new ArrayList<>(graph);
        source.setDist(0);

        while (!unVisited.isEmpty()) {
            // 3. 选取当前顶点
            Vertex curr = chooseMinDistVertex(unVisited);
            // 4. 更新当前顶点邻居距离
            updateNeighboursDist(curr, unVisited);
            // 5. 移除当前顶点
            unVisited.remove(curr);
        }

        for (Vertex v : graph) {
            System.out.println(v);
        }
    }

    private static void updateNeighboursDist(Vertex curr, List<Vertex> unVisited) {
        for (Edge edge : curr.getEdges()) {
            Vertex linked = edge.getLinked();
            if (unVisited.contains(linked)) {
                int weight = edge.getWeight();
                if (weight < linked.getDist()) {
                    linked.setDist(weight);
                    linked.setPrev(curr);
                }
            }
        }
    }

    private static Vertex chooseMinDistVertex(List<Vertex> unVisited) {
        Vertex minVertex = unVisited.get(0);
        for (int i = 1; i < unVisited.size(); i++) {
            if (unVisited.get(i).getDist() < minVertex.getDist()) {
                minVertex = unVisited.get(i);
            }
        }
        return minVertex;
    }
}
