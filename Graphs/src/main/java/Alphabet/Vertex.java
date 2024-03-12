/*
 * This Java file contains the declaration
 * of the Vertex class which is used in the
 * Graph class.
 * file Vertex.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     4  April 2023
 * Modified 16 April 2023
 */

package Alphabet;
import java.util.HashSet;

public class Vertex<ValueType> {
    HashSet<Vertex<ValueType>> toVertex;
    Integer indegree;
    ValueType label;

    Vertex(ValueType label) {
        this.indegree = 0;
        this.toVertex = new HashSet<>();
        this.label = label;
    }

    public void connect(Vertex dst) {
        if (this.toVertex.add(dst))
            dst.indegree += 1;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }
}
