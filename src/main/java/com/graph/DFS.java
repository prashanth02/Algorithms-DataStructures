package com.graph;

import java.util.HashMap;
import java.util.LinkedList;

/*
    The main difference between graphs and trees is that graphs may contain cycles.
    So to avoid searching in cycles, we will mark each node when we visit it.
 */
public class DFS {

    // Each node maps to a list of all his neighbors
    private HashMap<Node, LinkedList<Node>> adjacencyMap;

    public DFS(boolean directed) {
        adjacencyMap = new HashMap<>();
    }

    public void depthFirstSearch(Node node) {
        //TODO: Fix
        node.visit();
        LinkedList<Node> allNeighbors = adjacencyMap.get(node);
        if (allNeighbors == null)
            return;

        for (Node neighbor : allNeighbors) {
            if (!isVisited(neighbor))
                depthFirstSearch(neighbor);
        }
    }

    public boolean isVisited(Node node) {
        return node.visited;
    }

    /*public void dfs(int start) {
        boolean[] isVisited = new boolean[adjVertices.size()];
        dfsRecursive(start, isVisited);
    }

    private void dfsRecursive(int current, boolean[] isVisited) {
        isVisited[current] = true;
        visit(current);
        for (int dest : adjVertices.get(current)) {
            if (!isVisited[dest])
                dfsRecursive(dest, isVisited);
        }
    }



    public void dfsWithoutRecursion(int start) {
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] isVisited = new boolean[adjVertices.size()];
        stack.push(start);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            isVisited[current] = true;
            visit(current);
            for (int dest : adjVertices.get(current)) {
                if (!isVisited[dest])
                    stack.push(dest);
            }
        }
    }*/

}
