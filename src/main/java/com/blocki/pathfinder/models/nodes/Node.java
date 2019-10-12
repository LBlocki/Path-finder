package com.blocki.pathfinder.models.nodes;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * This class is used by program mainly for viewing. It holds information useful during
 * drawing and updating board
 */
@Getter
@Setter
public class Node
{

    public enum NODE_TYPE {BLOCK, START, END, CLEAN}

    private Integer width;

    private Integer height;

    private NODE_TYPE _node_type;

    public Node(Integer width, Integer height, NODE_TYPE _node_type)
    {
        this.width = width;
        this.height = height;
        this._node_type = _node_type;
    }

    @Override
    public boolean equals(Object object)
    {

        if (this == object)
        {
            return true;
        }

        else if (object == null || getClass() != object.getClass())
        {
            return false;
        }

        Node node = (Node) object;
        return getWidth().equals(node.getWidth()) && getHeight().equals(node.getHeight());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getWidth(), getHeight());
    }
}
