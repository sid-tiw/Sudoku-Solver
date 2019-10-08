package com.swst;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

class Solve {

    static boolean solve(int[][] matrix, int n) throws Exception {
        PairSelf minPos = minPoss(matrix, n);
        while (minPos.first != -1 && minPos.second != -1) {
            ArrayList<Integer> possibles = possibilities(matrix, n, minPos);
            if (possibles.size() == 1) {
                matrix[minPos.first][minPos.second] = possibles.get(0);
                minPos = minPoss(matrix, n);
                continue;
            }
            for (int i = 0; i < possibles.size(); i++) {
                int[][] tempMatrix = matrix;
                copy(tempMatrix, matrix, n);
                tempMatrix[minPos.first][minPos.second] = possibles.get(i);
                boolean solveFlag = solve(tempMatrix, n);
                if(solveFlag == true) {
                    copy(matrix, tempMatrix, n);
                    return true;
                }
                throw new Exception("Not Solvable!!");
            }
        }
        return checkValidity(matrix, n);
    }

    static void print(int[][] sudoku, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(sudoku[i][j] + " ");
            System.out.println();
        }
    }                                                   //Prints the current sudoku

    static boolean checkValidity(int[][] sudoku, int n) throws Exception {
        for (int i = 0; i < n; i++) {
            boolean[] flag1 = new boolean[n], flag2 = new boolean[n];
            Arrays.fill(flag1, false);
            Arrays.fill(flag2, false);
            for (int j = 0; j < n; j++) {
                flag1[sudoku[i][j] - 1] = true;
                flag2[sudoku[j][i] - 1] = true;
            }
            for (int j = 0; j < n; j++) {
                if (!flag1[j] || !flag2[j])
                    return false;
            }
        }
        return true;
    }                       //After Solving Check Whether the sudoku is valid or not

    static ArrayList<PairSelf> checkForZeroes(int[][] sudoku, int n) {
        ArrayList<PairSelf> x = new ArrayList<PairSelf>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (sudoku[i][j] == 0)
                    x.add(new PairSelf(i, j));
        return x;
    }                           //Check for empty Positions

    static ArrayList<Integer> possibilities(int[][] sudoku, int n, PairSelf coordinates) {          //return all possibilities at "coordinates"
        ArrayList<Integer> possibles = new ArrayList<Integer>();
        boolean[] arr = new boolean[n];
        Arrays.fill(arr, false);
        for (int i = 0; i < n; i++) {
            int s1 = sudoku[coordinates.first][i], s2 = sudoku[i][coordinates.second];
            if (s1 != 0)
                arr[s1 - 1] = true;
            if (s2 != 0)
                arr[s2 - 1] = true;
        }
        int s = (int) Math.sqrt(n);
        int x = (int) coordinates.first / s, y = (int) coordinates.second / s;
        x *= s;
        y *= s;
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                int s1 = sudoku[x + i][y + j];
                if (s1 != 0)
                    arr[s1 - 1] = true;
            }
        }
        for (int i = 0; i < n; i++)
            if (!arr[i])
                possibles.add(i + 1);
        return possibles;
    }

    static PairSelf minPoss(int[][] sudoku, int n) {
        PairSelf minPos = new PairSelf(-1, -1);
        int min = n + 1;
        ArrayList<PairSelf> zeroes = checkForZeroes(sudoku, n);
        for (int i = 0; i < zeroes.size(); i++) {
            ArrayList<Integer> possibles = possibilities(sudoku, n, zeroes.get(i));
            if (possibles.size() < min) {
                min = possibles.size();
                minPos = zeroes.get(i);
            }
        }
        return minPos;
    }

    static void copy(int[][] dest, int[][] source, int n) {
        for (int i = 0; i < n; i++)
            System.arraycopy(source[i], 0, dest[i], 0, n);
    }
}
