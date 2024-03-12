/*
 * This Java file contains the declaration
 * of the Graph class which contains the
 * methods in order to initialize the graph,
 * set connections and top sort the graph.
 * file Graph.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     4  April 2023
 * Modified 16 April 2023
 */

package Alphabet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph<ValueType> {
    private final HashMap<ValueType, Integer> vertexMap;
    ArrayList<Vertex<ValueType>> vertices;
    private Integer nbVertex;

    Graph() {
        nbVertex = 0;
        vertices = new ArrayList<>();
        vertexMap = new HashMap<>();
    }

    private Vertex<ValueType> createVertex(ValueType value) {
        Integer pos = vertexMap.get(value);
        if (pos == null) {
            pos = nbVertex;
            vertexMap.put(value, pos);
            vertices.add(new Vertex<>(value));
            nbVertex++;
        }
        return vertices.get(pos);
    }

    public void connect(ValueType src, ValueType dst) {
        Vertex<ValueType> srcVertex = createVertex(src);
        Vertex<ValueType> dstVertex = createVertex(dst);
        srcVertex.connect(dstVertex);
    }

    public ArrayList<ValueType> topSort() {
        return generateTopSortArray(fillUpQueue());
    }

    private Queue<Vertex<ValueType>> fillUpQueue(){
        Queue<Vertex<ValueType>> queue = new LinkedList<>();
        for (Vertex<ValueType> vertice : vertices)
            if (vertice.indegree == 0)
                queue.add(vertice);
        return queue;
    }

    private ArrayList<ValueType> generateTopSortArray(Queue<Vertex<ValueType>> queue) {
        ArrayList<ValueType> topSortedArray = new ArrayList<>();
        while (!queue.isEmpty()) {
            Vertex<ValueType> vertice = queue.poll();
            topSortedArray.add(vertice.label);
            for (Vertex<ValueType> neighbour : vertice.toVertex)
                if (--neighbour.indegree == 0)
                    queue.add(neighbour);
        }
        return topSortedArray;
    }
}
