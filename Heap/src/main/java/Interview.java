/*
 * This Java file contains the declaration
 * of the Interview class which contains the
 * method of the class Interview.
 * file Interview.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     19 March 2023
 * Modified 2  April 2023
 */

import java.util.*;

public final class Interview {
    /**
     * *********************************************************************************************
     * Complexité temporelle: O (nlog(n)) avec n étant le nombre de boîtes
     * Tout d'abord, nous instancions une PriorityQueue avec comme propriété de préserver un
     * ordre décroissant au sein de ses éléments. Initialement, cette PriorityQueue est vide,
     * mais nous allons la charger avec les éléments de la tables boxes. Ainsi, nous devons
     * itérer sur les n éléments (où n est le nombre de box) de la table afin de les insérer
     * dans la PriorityQueue. L'insertion se fait à l'aide de la méthode offer qui permet
     * d'insérer un élément tout en respectant les propriétés du PriorityQueue. Cette
     * opération se fait en O (log n) (où n est le nombre d'éléments dans le priorityQueue),
     * car les opérations d'insertion et de retrait dans un Heap se font en O (log n). Une fois
     * la PriorityQueue remplie, on retire les deux premiers éléments de la Priority grâce à la
     * méthode poll qui s'exécute en O (log n) (opérations d'insertion et de retrait dans un Heap
     * se font en O (log n)). On soustrait les deux éléments et si la différence est plus grande
     * que zéro, on insère le résultat dans la PriorityQueue à l'aide de la méthode offer(). On
     * recommence ses opérations tant que la taille de la PriorityQueue n'est pas inférieur à 2,
     * soit n-1 fois ou n-2 (si la différence des 2 derniers éléments est 0). Ainsi, la boucle
     * while possède une complexité de O (n). Une fois la boucle traitée, on retourne soit 0 si
     * la PriorityQueue est vide soit le seul élément contenu dans la queue.
     * =============================================================================================
     * Calcul de la complexité temporelle:
     * O ( 1 + 1 + 1 + 1 + ( n * log n) + ( n * ( log n + 1 + log n + 1 + log n)) + 1)
     * => O ( 5 + ( n log n) + ( n * ( 3 * log n + 3))
     * => O ( 5 + n log n + ( n * log n)
     * => O ( 5 + n log n + n log n)
     * => O ( 5 + 2 (n log n))
     * => O ( n log n )
     * *********************************************************************************************
     * Complexité spaciale: O (n) avec n étant le nombre de boîte
     * Tout d'abord, l'algorithme alloue un espace pour les n éléments de la table boxes et des
     * espaces constants pour les variables firstBox, secondBox et survivingBox. Ensuite,il alloue
     * un autre espace pour la PriorityQueue qui aura une taille de n élément une fois remplies avec
     * les n éléments de la table. Enfin, un espace constant sera alloué pour la valeur retournée
     * par l'algorithme.
     * =============================================================================================
     * Calcul de la complexité spaciale:
     * O ( n + 1 + 1 + 1 + n + 1)
     * => O (2n + 4)
     * => O (n)
     * *********************************************************************************************
     **/
    static public int lastBox(int[] boxes) {
        int firstBox, secondBox, survivingBox; // Time Complexity = O(1)
        if (boxes == null || boxes.length == 0) // Time Complexity = O(1)
            return 0; // Time Complexity = O(1)
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder()); // Time Complexity = O (1)
        for (int box : boxes) // Time Complexity = O (n)
            queue.offer(box); // Time Complexity = O (log n)
        while (queue.size() >= 2) { //Time Complexity = O (n)
            firstBox = queue.poll(); // Time Complexity = O (log n)
            /*
            *  This condition is added to remove the warning Unboxing of 'queue.poll()'
            *  may produce 'NullPointerException', even if with the initial condition
            *  in the while loop already covers this exception.
            */
            if (queue.size() >= 1) { // Time Complexity = O(1)
                secondBox = queue.poll(); // Time Complexity = O (log n)
                survivingBox = Math.abs(firstBox - secondBox); // Time Complexity = O(1)
                if (survivingBox != 0) // Time Complexity = O(1)
                    queue.offer(survivingBox); // Time Complexity = O (log n)
            }
        }
        return (queue.isEmpty()) ? 0 : queue.peek(); // Time Complexity = O(1)
    }
}
