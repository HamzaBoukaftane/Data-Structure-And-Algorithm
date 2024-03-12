/*
 * This Java file contains the declaration
 * of the KthSmallestElement class which contains
 * the methods in order to find the kth smallest
 * element in a specific type of matrix.
 * file Alphabet.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     4  April 2023
 * Modified 16 April 2023
 */

package KthSmallestElement;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class KthSmallestElement {
    /**
     * Définition de variable
     * m : nombre de ligne
     * n : nombre de colonne
     * k : le kième plus petit élément
     * *********************************************************************************************
     * Complexité temporelle: Pire cas => O ( k log max(m,n))
     * Tout d'abord, nous instancions une PriorityQueue que nous remplissons avec tout les éléments
     * de la première ligne ou la première colonne en fonction de si le nombre de lignes est plus grand
     * que le nombre de colonnes et vice versa. Ainsi, pour remplir la queue, il faut itérer sur les n
     * ou m éléments et les insérer successivement pour une complexité totale de O (n * log(n)) pour
     * initializeQueueRow ou de O (m * log(m)) pour initializeQueueCol, car la méthode add des PriorityQueue
     * est O (log n) ou O (log m). Comme dans le pire des cas, k est égal à n ou m, on obtient une
     * complexité pour le remplissage de la queue de O (k * log(max(n,m)). À partir de la queue remplie,
     * il reste juste à trouver le k ième plus petit élément. Ainsi on itère sur k éléments de la queue
     * en retirant son minimum puis ajoutant le prochain élément de la ligne ou de la colonne en fonction
     * de laquelle est en plus grand nombre. Pour ce faire, on utilise la méthode findKthMin qui itère k fois
     * sur la queue en exécutant la méthode addToQueue. La méthode addToQueue possède une complexité de
     * O (log n) ou O (log m). Ainsi, la méthode findKth possède une complexité de O(k * log(max(n,m)).
     * =============================================================================================
     * Calcul de la complexité temporelle dans le pire cas:
     * initializeQueueRow ou initializeQueueCol => a = O (1 + k * log (max(n,m)) + 1)
     *                                          => a = O (2 + k * log (max(n,m)))
     *                                          => a = O (k * log (max(n,m)))
     * addToQueue => b = O (1 + 1 + 1 + 1 + 1 + 1 + log(max(n,m)))
     *            => b = O (6 + log(max(n,m)))
     *            => b = O (log(max(n,m)))
     * findKthMin => c = O ([k * (log(max(n,m)) + 1 + 1 + 1 + 1 + 1 + 1 + 1 + b)] + log(max(n,m)))
     *            => c = O ([k * (log(max(n,m)) + 7 + log(max(n,m)))] + log(max(n,m)))
     *            => c = O ([k * (2 * log(max(n,m)))] + log(max(n,m)))
     *            => c = O ([k * (log(max(n,m)))] + log(max(n,m)))
     *            => c = O (k * (log(max(n,m))))
     * findKthSmallestElement (Total) => O (1 + 1 + 1 + 1 + 1 + 1 + 1 + a + c)
     *                                => O (7 + k * log (max(n,m)) + k * log (max(n,m)))
     *                                => 0 (2 * k * log (max(n,m)))
     *                                => O (k * log (max(n,m)))
     * *********************************************************************************************
     * Complexité spaciale: Pire cas => O (max(n, m)))
     * Tout d'abord, un espace est alloué pour la PriorityQueue qui possède au plus n ou m éléments en
     * fonction de si le nombres de ligne est plus grand que le nombre de colonnes ou vice-versa. Pour
     * le reste des allocations de mémoire, on alloue de l'espace dans notre code à un tableau de int
     * de 3 éléments, un autre tableau de 5 éléments et des espaces pour des variables contenant des int
     * et d'autres contenant des Nodes. Ces dernières allocations sont constants donc O(1).
     * =============================================================================================
     * Calcul de la complexité spaciale dans le pire cas en ne considérant pas les allocations O(1):
     * O (max(n,m))
     * ** La chargé de cour Sara Beddouche nous a indiqué qu'il ne fallait pas considéré l'espace
     *    alloué à la matrice m * n passé en paramètre. Ainsi, nous l'excluons O (m * n) du calcul
     *    de la complexité spaciale. Voir commentaire sur dernier commit pour un transcript de la
     *    communication avec la chargée.
     * *********************************************************************************************
     **/
    static public <T extends Comparable<T>> T findKthSmallestElement(final T[][] matrix, final int k) {
        if (matrix == null) return null;
        int rs = matrix.length, cs = matrix[0].length, max = Math.max(rs, cs);
        int[] rows_cols_max = {rs, cs, max};
        if (k < 0 || k >= rs * cs) return null;
        PriorityQueue<Node<T>> queue = (max == rs) ? initializeQueueRow(matrix, cs) : initializeQueueCol(matrix, rs);
        return findKthMin(matrix, queue, k, rows_cols_max);
    }

    private static <T extends Comparable<T>> PriorityQueue<Node<T>> initializeQueueRow(T[][] matrix, int cols) {
        PriorityQueue<Node<T>> queue = new PriorityQueue<>();
        IntStream.range(0, cols).forEach(index -> queue.add(new Node<>(matrix[0][index], 0, index)));
        return queue;
    }

    private static <T extends Comparable<T>> PriorityQueue<Node<T>> initializeQueueCol(T[][] matrix, int rows) {
        PriorityQueue<Node<T>> queue = new PriorityQueue<>();
        IntStream.range(0, rows).forEach(index -> queue.add(new Node<>(matrix[index][0], index, 0)));
        return queue;
    }

    private static <T extends Comparable<T>> T findKthMin (T[][] matrix, PriorityQueue<Node<T>> q, int k, int[] rCM) {
        IntStream.range(0, k).forEach(index -> {
            Node<T> curr = q.poll();
            assert curr != null;
            int row = curr.row, column = curr.col;
            int[] rows_cols_Max_row_col = {rCM[0], rCM[1], rCM[2], row, column};
            addToQueue(matrix, q, rows_cols_Max_row_col);
        });
        return Objects.requireNonNull(q.poll()).value;
    }

    private static <T extends Comparable<T>> void addToQueue(T[][] matrix, PriorityQueue<Node<T>> q, int[] rsCsMaxRC) {
        int rows = rsCsMaxRC[0], cols = rsCsMaxRC[1], max = rsCsMaxRC[2], row = rsCsMaxRC[3], col = rsCsMaxRC[4];
        if (rows == max && row < rows - 1)
            q.add(new Node<>(matrix[row + 1][col], row + 1, col));
        else if (col < cols - 1)
            q.add(new Node<>(matrix[row][col + 1], row, col + 1));
    }

    private record Node<T extends Comparable<T>>(T value, int row, int col) implements Comparable<Node<T>> {
        @Override
            public int compareTo(Node<T> other) {
                return Integer.compare(value.compareTo(other.value), 0);
            }
        }
}

