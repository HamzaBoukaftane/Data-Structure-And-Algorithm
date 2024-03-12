/*
 * This Java file contains the declaration
 * of the Interview class which contains all
 * the methods of the class Interview
 * file Interview.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     19 march 2023
 * Modified 17 march 2023
 */

public class Interview {
    /**
     * Complexité temporelle: O(n). Tous les accès (quantité m) au array
     * avec index "numbers[i]" se font en O(1), la boucle for se fait en
     * O(N), donc la complexité temporelle est de O(N) + m*O(1) = O(n).
     * Dans les cas pire ou moyen, la boucle se termine toujours à la fin,
     * donc on aura toujours O(n)

     * Complexite spatiale : O(1), aucun espace mémoire additionelle n'est
     * utilisé, la mémoire utilisée reste constante indépendemment de la
     * taille de l'entrée, donc la complexité spatiale est O(1), peut-importe
     * si on est dans le pire ou moyen cas.
     **/
    public static Integer findNonDuplicateIterativeLinear(Integer[] numbers) {
        int arraySize = numbers.length;
        if (arraySize == 0)
            return null;
        if (arraySize == 1)
            return numbers[0];
        int endIndex = arraySize - 1;
        if (endIndex % 2 != 0)
            endIndex--;
        // For loop sur moitie des elements :
        // temps : O(N/2) => O(N), space: O(1)
        for (int i = 0; i < endIndex; i += 2)
            if (!(numbers[i].equals(numbers[i + 1])))
                return numbers[i];
        if (!(numbers[arraySize - 2].equals(numbers[arraySize - 1])))
            return numbers[arraySize - 1];
        return null;
    }

    /**
     *  Complexité temporelle: O(log(n)). Justification : Dans cette solution,
     *  nous utilisons la méthode de recherche binaire. Nous divisons l'entrée
     *  en deux chaque itération, ce qui implique que dans le pire cas ou le
     *  cas moyen, on aura O(log(n)).

     *  Complexite spatiale : O(1), car l'espace mémoire utilisé ne dépend pas
     *  de l'entrée du array, l'algorithme ne crée pas de nouvelles structures
     *  de données. Ainsi, la quantité de mémoire supplémentaire utilisée par
     *  l'algorithme est constante , peut-importe si on est dans le pire ou moyen cas.
     **/
    public static Integer findNonDuplicateIterative(Integer[] numbers) {
        int high = numbers.length - 1, low = 0, middle;
        if (high == -1)
            return null;
        else if (high == 0)
            return numbers[0];
        else if (!(numbers[0].equals(numbers[1])))
            return numbers[0];
        else if (!(numbers[high].equals(numbers[high - 1])))
            return numbers[high];
        // Boucle while
        // Temps : O(log(n)), Espace : O(1) - Justification complete en haut
        while (low <= high) {
            middle = (low + high) / 2;
            if (!(numbers[middle].equals(numbers[middle - 1])) &&
                    !(numbers[middle].equals(numbers[middle + 1])))
                return numbers[middle];
            if ((middle % 2 == 0 && numbers[middle].equals(numbers[middle + 1])) ||
                    (middle % 2 == 1 && numbers[middle].equals(numbers[middle - 1])))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return null;
    }

    /**
     * Complexité temporelle: O(log(n)). Justification : Dans cette solution,
     * nous utilisons la méthode de recherche binaire. Nous divisons l'entrée
     * en deux après chaque itération de la récursion, similairement à la
     * fonction précédente, donc c'est O(log(n)) dans le pire des cas et les cas moyen.

     * Complexite spatiale : O(1), La complexité spatiale est également de O(1),
     * similairement à la fonction précédente. la fonction utilise une quantité
     * constante d'espace mémoire supplémentaire, quelle que soit la taille du tableau
     * d'entrée, peut-importe si on est dans le pire ou moyen cas.
     **/
    public static Integer findNonDuplicateRecursive(Integer[] numbers) {
        return findNonDuplicateRecursive(numbers, 0, numbers.length - 1);
    }

    public static Integer findNonDuplicateRecursive(Integer[] numbers, int low, int high) {
        if (numbers.length == 0)
            return null;
        if (low == high) {
            return numbers[low];
        }
        int middle = (low + high) / 2;
        if (middle % 2 == 0) {
            if (numbers[middle].equals(numbers[middle + 1]))
                return findNonDuplicateRecursive(numbers, middle + 2, high);
            else
                return findNonDuplicateRecursive(numbers, low, middle);
        } else {
            if (numbers[middle].equals(numbers[middle - 1]))
                return findNonDuplicateRecursive(numbers, middle + 1, high);
            else
                return findNonDuplicateRecursive(numbers, low, middle - 1);
        }
    }
}
