package com.shanhai.dataStructe._18graph;

import lombok.Data;

/**
 * 边
 */
@Data
public class Edge {
    private Vertex linked;
    private int weight;

    public Edge(Vertex linked) {
        this(linked, 1);
    }

    public Edge(Vertex linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }
}
