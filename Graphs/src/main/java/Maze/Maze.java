/*
 * This Java file contains the declaration
 * of the Maze class which contains the
 * methods and classes in order to find the
 * shortest path from start to finish.
 * file Maze.java
 * auteurs Hamza Boukaftane and Arman Lidder
 * date     4  April 2023
 * Modified 16 April 2023
 */

package Maze;
import java.util.*;
import java.util.stream.Collectors;

public class Maze {

    public static Integer findShortestPath(ArrayList<ArrayList<Tile>> maze) {
        if (maze.isEmpty()) return null;
        printMaze(maze);
        int rows = maze.size(), columns = maze.get(0).size();
        Coordinate start = findStart(maze, rows, columns);
        return (start == null) ? null : findShortestPath(maze, start, rows, columns);
    }

    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        System.out.println("Maze Test\n");
        for (ArrayList<Tile> row : maze)
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        System.out.println("\n");
    }

    private static Coordinate findStart(ArrayList<ArrayList<Tile>> maze, int rows, int columns) {
        ArrayList<Coordinate> openings = findMazeEntryAndExit(maze, rows, columns);
        return (openings.size() != 2) ? null : openings.get(0);
    }

    private static ArrayList<Coordinate> findMazeEntryAndExit(ArrayList<ArrayList<Tile>> maze, int rows, int columns) {
        ArrayList<Coordinate> openings = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            if (row == 0 || row == rows - 1) {
                for (int column = 0; column < columns; column++)
                    if(maze.get(row).get(column) == Tile.Exit)
                        openings.add(new Coordinate(row, column));
            } else {
                if (maze.get(row).get(0) == Tile.Exit)
                    openings.add(new Coordinate(row, 0));
                else if (maze.get(row).get(columns - 1) == Tile.Exit)
                    openings.add(new Coordinate(row, columns - 1));
            }
        }
        return openings;
    }

    private static Integer findShortestPath(ArrayList<ArrayList<Tile>> maze, Coordinate start, int rows, int columns) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, start));
        boolean[][] visitedCoords = new boolean[rows][columns];
        visitedCoords[start.row][start.column] = true;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String[] directions = {"up","down","left","right"};
            for (String direction: directions) {
                Coordinate neighbour = current.coord.getNeighbour(direction);
                if (isValidCoord(maze,neighbour,rows,columns) && !isVisited(neighbour,visitedCoords)) {
                    if (isExit(maze,neighbour))
                        return current.distanceFromBeginning + 1;
                    visitedCoords[neighbour.row][neighbour.column] = true;
                    queue.add(new Node(current.distanceFromBeginning + 1, neighbour));
                }
            }
        }
        return null;
    }

    private static boolean isValidCoord(ArrayList<ArrayList<Tile>> maze, Coordinate neighbour, int rows, int columns) {
        boolean isValidRow = (0 <= neighbour.row && neighbour.row < rows),
                isValidColumn = (0 <= neighbour.column && neighbour.column < columns);
        return (isValidRow && isValidColumn && !(maze.get(neighbour.row).get(neighbour.column) == Tile.Wall));
    }

    private static boolean isVisited(Coordinate neighbour, boolean[][] visitedCoords) {
        return visitedCoords[neighbour.row][neighbour.column];
    }

    private static boolean isExit(ArrayList<ArrayList<Tile>> maze, Coordinate neighbour) {
        return maze.get(neighbour.row).get(neighbour.column) == Tile.Exit;
    }

    private record Coordinate(int row, int column) {
        Coordinate getNeighbour(String direction) {
                return switch (direction) {
                    case "up" -> new Coordinate(row - 1, column);
                    case "down" -> new Coordinate(row + 1, column);
                    case "right" -> new Coordinate(row, column + 1);
                    case "left" -> new Coordinate(row, column - 1);
                    default -> this;
                };
            }
        }

    private static class Node {
        final int distanceFromBeginning;
        final Coordinate coord;

        Node(int distance, Coordinate coord) {
            this.distanceFromBeginning = distance;
            this.coord = coord;
        }
    }
}

