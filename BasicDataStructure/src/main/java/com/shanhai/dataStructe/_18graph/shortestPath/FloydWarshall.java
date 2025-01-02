package com.shanhai.dataStructe._18graph.shortestPath;

import com.shanhai.dataStructe._18graph.Edge;
import com.shanhai.dataStructe._18graph.Vertex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <h3>Floyd - Warshall多源最短路径算法</h3>
 */
public class FloydWarshall {
    public static void main(String[] args) {
        // 正常情况
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v3, -2));
        v2.edges = List.of(new Edge(v1, 4), new Edge(v3, 3));
        v3.edges = List.of(new Edge(v4, 2));
        v4.edges = List.of(new Edge(v2, -1));
        List<Vertex> graph = List.of(v1, v2, v3, v4);

        floydWarshall(graph);
    }

    private static void floydWarshall(List<Vertex> graph) {
        int size = graph.size();
        int[][] dist = new int[size][size];
        Vertex[][] prev = new Vertex[size][size];
        // 初始化
        for (int i = 0; i < size; i++) {
            Vertex v = graph.get(i);
            Map<Vertex, Integer> vertexToWeightMap = v.getEdges().stream()
                    .collect(Collectors.toMap(Edge::getLinked, Edge::getWeight));
            for (int j = 0; j < size; j++) {
                Vertex u = graph.get(j);
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = vertexToWeightMap.getOrDefault(u, Integer.MAX_VALUE);
                    prev[i][j] = vertexToWeightMap.get(u) != null ? v : null;
                }
            }
        }
        //print(prev);
        //print(dist);

        // 看能否借路到达其它顶点
        // v2->v1           v1->v?
        //      i  k             k    j
        // dist[1][0]   +   dist[0][0,1,2,3]
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE &&
                            dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        prev[i][j] = prev[k][j];
                    }
                }
            }
        }
        print(prev);
        //print(dist);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                path(prev, graph, i, j);
            }
        }
    }

    private static void path(Vertex[][] prev, List<Vertex> graph, int i, int j) {
        LinkedList<String> stack = new LinkedList<>();
        System.out.print("[" + graph.get(i).getName() + "," + graph.get(j).getName() + "] ");
        stack.push(graph.get(j).getName());
        while (i != j) {
            Vertex p = prev[i][j];
            stack.push(p.getName());
            j = graph.indexOf(p);
        }
        System.out.println(stack);
    }

    private static void print(Vertex[][] prev) {
        System.out.println("-------------------------");
        for (Vertex[] row : prev) {
            System.out.println(Arrays.stream(row).map(v -> v == null ? "null" : v.getName())
                    .map(s -> String.format("%5s", s))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }

    private static void print(int[][] dist) {
        System.out.println("-------------");
        for (int[] row : dist) {
            System.out.println(Arrays.stream(row).boxed()
                    .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))
                    .map(s -> String.format("%2s", s))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }
}
/*
              直接连通(初始化)
              v1  v2  v3  v4
          v1  0   ∞   -2  ∞
          v2  4   0   3   ∞
          v3  ∞   ∞   0   2
          v4  ∞   -1  ∞   0

              k=0 借助v1到达其它顶点
              v1  v2  v3  v4
          v1  0   ∞   -2  ∞
          v2  4   0   2   ∞
          v3  ∞   ∞   0   2
          v4  ∞   -1  ∞   0

              k=1 借助v2到达其它顶点
              v1  v2  v3  v4
          v1  0   ∞   -2  ∞
          v2  4   0   2   ∞
          v3  ∞   ∞   0   2
          v4  3   -1  1   0

              k=2 借助v3到达其它顶点
              v1  v2  v3  v4
          v1  0   ∞   -2  0
          v2  4   0   2   4
          v3  ∞   ∞   0   2
          v4  3   -1  1   0

              k=3 借助v4到达其它顶点
              v1  v2  v3  v4
          v1  0   -1  -2  0
          v2  4   0   2   4
          v3  5   1   0   2
          v4  3   -1  1   0
*/
