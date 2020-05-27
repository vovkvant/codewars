package com.selfdev.battleship;

import java.util.*;
import java.util.stream.Collectors;

public class BattleField {

    public static boolean fieldValidator(int[][] field) {
        List<Set<Cell>> list = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int k = 0; k < field[i].length; k++) {
                if (field[i][k] == 1) {
                    Cell cell = new Cell(i, k);
                    boolean isAdded = false;
                    for (Set<Cell> ship : list) {
                        if (isRelated(cell, ship)) {
                            ship.add(cell);
                            isAdded = true;
                            break;
                        }
                    }
                    if (!isAdded) {
                        Set<Cell> ship = new HashSet<>();
                        ship.add(cell);
                        list.add(ship);
                    }
                }
            }
        }

        /*
        There must be single battleship (size of 4 cells), 2 cruisers (size 3), 3 destroyers (size 2) and 4 submarines (size 1)
         */
        int battleshipCounter = 0;
        int cruisesCounter = 0;
        int destroyersCounter = 0;
        int submarinesCounter = 0;
        for (Set<Cell> ship : list) {
            System.out.println(ship);
            if (ship.size() > 4) {
                System.out.println("Size of one ship more then 4");
                return false;
            }
            if (!isLineShip(ship)) {
                System.out.println("Not in line ship");
                return false;
            }
            switch (ship.size()) {
                case 4:
                    battleshipCounter++;
                    break;
                case 3:
                    cruisesCounter++;
                    break;
                case 2:
                    destroyersCounter++;
                    break;
                case 1:
                    submarinesCounter++;
                    break;
            }
        }
        if (battleshipCounter != 1) {
            System.out.println("Too much battleships");
            return false;
        }
        if (cruisesCounter != 2) {
            System.out.println("Too much cruises");
            return false;
        }
        if (destroyersCounter != 3) {
            System.out.println("Too much destroyers");
            return false;
        }
        if (submarinesCounter != 4) {
            System.out.println("Too much submarines");
            return false;
        }
        return true;
    }

    static boolean isLineShip(Set<Cell> ship) {
        boolean isHorizontal = ship.stream().map(s -> s.i).collect(Collectors.toSet()).size() == 1;
        boolean isVertical = ship.stream().map(s -> s.k).collect(Collectors.toSet()).size() == 1;
        return isHorizontal || isVertical;
    }

    static boolean isRelated(Cell cell, Set<Cell> ship) {
        return ship.contains(new Cell(cell.i - 1, cell.k)) ||
                ship.contains(new Cell(cell.i + 1, cell.k)) ||
                ship.contains(new Cell(cell.i, cell.k + 1)) ||
                ship.contains(new Cell(cell.i, cell.k - 1)) ||
                ship.contains(new Cell(cell.i + 1, cell.k + 1)) ||
                ship.contains(new Cell(cell.i - 1, cell.k - 1)) ||
                ship.contains(new Cell(cell.i + 1, cell.k - 1)) ||
                ship.contains(new Cell(cell.i - 1, cell.k + 1));
    }

    static class Cell {
        int i;
        int k;

        public Cell(int i, int k) {
            this.i = i;
            this.k = k;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cells = (Cell) o;
            return i == cells.i &&
                    k == cells.k;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, k);
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "i=" + i +
                    ", k=" + k +
                    '}';
        }
    }
}
