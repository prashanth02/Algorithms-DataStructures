package com.datastructure;

/*
    A quadtree is a tree data structure in which each internal node has exactly four children.
    Quadtrees are the two-dimensional analog of octrees and are most often used to partition a two-dimensional space by recursively subdividing it into four quadrants or regions.
    The data associated with a leaf cell varies by application, but the leaf cell represents a "unit of interesting spatial information".
 */

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    /*
     *  			N
     *  		W		E
     *  			S
     */

    class Node {
        int x, y, value;

        Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value; /* some data*/
        }
    }

    final int MAX_CAPACITY =4;
    int level = 0;
    List<Node> nodes;
    QuadTree northWest = null;
    QuadTree northEast = null;
    QuadTree southWest = null;
    QuadTree southEast = null;
    Boundry boundry;

    QuadTree(int level, Boundry boundry) {
        this.level = level;
        nodes = new ArrayList<Node>();
        this.boundry = boundry;
    }

    /* Traveling the Graph using Depth First Search*/
    static void dfs(QuadTree tree) {
        if (tree == null)
            return;

        System.out.printf("\nLevel = %d [X1=%d Y1=%d] \t[X2=%d Y2=%d] ",
                tree.level, tree.boundry.getxMin(), tree.boundry.getyMin(),
                tree.boundry.getxMax(), tree.boundry.getyMax());

        for (Node node : tree.nodes) {
            System.out.printf(" \n\t  x=%d y=%d", node.x, node.y);
        }
        if (tree.nodes.size() == 0) {
            System.out.printf(" \n\t  Leaf Node.");
        }
        dfs(tree.northWest);
        dfs(tree.northEast);
        dfs(tree.southWest);
        dfs(tree.southEast);

    }

    void split() {
        int xOffset = this.boundry.getxMin()
                + (this.boundry.getxMax() - this.boundry.getxMin()) / 2;
        int yOffset = this.boundry.getyMin()
                + (this.boundry.getyMax() - this.boundry.getyMin()) / 2;

        northWest = new QuadTree(this.level + 1, new Boundry(
                this.boundry.getxMin(), this.boundry.getyMin(), xOffset,
                yOffset));
        northEast = new QuadTree(this.level + 1, new Boundry(xOffset,
                this.boundry.getyMin(), xOffset, yOffset));
        southWest = new QuadTree(this.level + 1, new Boundry(
                this.boundry.getxMin(), xOffset, xOffset,
                this.boundry.getyMax()));
        southEast = new QuadTree(this.level + 1, new Boundry(xOffset, yOffset,
                this.boundry.getxMax(), this.boundry.getyMax()));

    }

    void insert(int x, int y, int value) {
        if (!this.boundry.inRange(x, y)) {
            return;
        }

        Node node = new Node(x, y, value);
        if (nodes.size() < MAX_CAPACITY) {
            nodes.add(node);
            return;
        }
        // Exceeded the capacity so split it in FOUR
        if (northWest == null) {
            split();
        }

        // Check coordinates belongs to which partition
        if (this.northWest.boundry.inRange(x, y))
            this.northWest.insert(x, y, value);
        else if (this.northEast.boundry.inRange(x, y))
            this.northEast.insert(x, y, value);
        else if (this.southWest.boundry.inRange(x, y))
            this.southWest.insert(x, y, value);
        else if (this.southEast.boundry.inRange(x, y))
            this.southEast.insert(x, y, value);
        else
            System.out.printf("ERROR : Unhandled partition %d %d", x, y);
    }

    public static void main(String args[]) {
        Boundry boundry = new Boundry(0, 0, 1000, 1000);
        QuadTree anySpace = new QuadTree(1, boundry);
        anySpace.insert(100, 100, 1);
        anySpace.insert(500, 500, 1);
        anySpace.insert(600, 600, 1);
        anySpace.insert(700, 600, 1);
        anySpace.insert(800, 600, 1);
        anySpace.insert(900, 600, 1);
        anySpace.insert(510, 610, 1);
        anySpace.insert(520, 620, 1);
        anySpace.insert(530, 630, 1);
        anySpace.insert(540, 640, 1);
        anySpace.insert(550, 650, 1);
        anySpace.insert(555, 655, 1);
        anySpace.insert(560, 660, 1);
        //Traveling the graph
        QuadTree.dfs(anySpace);
    }
}

/*public class QuadTree<Key extends Comparable<Key>, Value>  {
    private Node root;

    // helper node data type
    private class Node {
        Key x, y;              // x- and y- coordinates
        Node NW, NE, SE, SW;   // four subtrees
        Value value;           // associated data

        Node(Key x, Key y, Value value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }


    *//***********************************************************************
     *  Insert (x, y) into appropriate quadrant
     ***************************************************************************//*
    public void insert(Key x, Key y, Value value) {
        root = insert(root, x, y, value);
    }

    private Node insert(Node h, Key x, Key y, Value value) {
        if (h == null) return new Node(x, y, value);
            //// if (eq(x, h.x) && eq(y, h.y)) h.value = value;  // duplicate
        else if ( less(x, h.x) &&  less(y, h.y)) h.SW = insert(h.SW, x, y, value);
        else if ( less(x, h.x) && !less(y, h.y)) h.NW = insert(h.NW, x, y, value);
        else if (!less(x, h.x) &&  less(y, h.y)) h.SE = insert(h.SE, x, y, value);
        else if (!less(x, h.x) && !less(y, h.y)) h.NE = insert(h.NE, x, y, value);
        return h;
    }


    *//***********************************************************************
     *  Range search.
     ***************************************************************************//*

    public void query2D(Interval2D<Key> rect) {
        query2D(root, rect);
    }

    private void query2D(Node h, Interval2D<Key> rect) {
        if (h == null) return;
        Key xmin = rect.intervalX.min();
        Key ymin = rect.intervalY.min();
        Key xmax = rect.intervalX.max();
        Key ymax = rect.intervalY.max();
        if (rect.contains(h.x, h.y))
            StdOut.println("    (" + h.x + ", " + h.y + ") " + h.value);
        if ( less(xmin, h.x) &&  less(ymin, h.y)) query2D(h.SW, rect);
        if ( less(xmin, h.x) && !less(ymax, h.y)) query2D(h.NW, rect);
        if (!less(xmax, h.x) &&  less(ymin, h.y)) query2D(h.SE, rect);
        if (!less(xmax, h.x) && !less(ymax, h.y)) query2D(h.NE, rect);
    }


    *//***************************************************************************
     *  helper comparison functions
     ***************************************************************************//*

    private boolean less(Key k1, Key k2) { return k1.compareTo(k2) <  0; }
    private boolean eq  (Key k1, Key k2) { return k1.compareTo(k2) == 0; }


    *//***************************************************************************
     *  test client
     ***************************************************************************//*
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);   // queries
        int N = Integer.parseInt(args[1]);   // points

        QuadTree<Integer, String> st = new QuadTree<Integer, String>();

        // insert N random points in the unit square
        for (int i = 0; i < N; i++) {
            Integer x = (int) (100 * Math.random());
            Integer y = (int) (100 * Math.random());
            // StdOut.println("(" + x + ", " + y + ")");
            st.insert(x, y, "P" + i);
        }
        StdOut.println("Done preprocessing " + N + " points");

        // do some range searches
        for (int i = 0; i < M; i++) {
            Integer xmin = (int) (100 * Math.random());
            Integer ymin = (int) (100 * Math.random());
            Integer xmax = xmin + (int) (10 * Math.random());
            Integer ymax = ymin + (int) (20 * Math.random());
            Interval<Integer> intX = new Interval<Integer>(xmin, xmax);
            Interval<Integer> intY = new Interval<Integer>(ymin, ymax);
            Interval2D<Integer> rect = new Interval2D<Integer>(intX, intY);
            StdOut.println(rect + " : ");
            st.query2D(rect);
        }
    }

}*/
