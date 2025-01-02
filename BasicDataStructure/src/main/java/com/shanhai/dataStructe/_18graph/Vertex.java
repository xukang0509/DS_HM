package com.shanhai.dataStructe._18graph;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * 顶点
 */
@Setter
@Getter
public class Vertex {
    public List<Edge> edges;
    private String name;

    private Integer dist = Integer.MAX_VALUE;
    private Vertex prev;

    public Vertex(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex vertex)) return false;
        return Objects.equals(getName(), vertex.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", dist=" + dist +
                ", prev=" + prev +
                '}';
    }
}
