package com.shanhai.algorithm.backtrack;

import java.util.Arrays;

/**
 * @author xk
 * @since 2024-08-19 22:43
 */
public class ApplicationSudoku {
    private static Long count = 0L;

    public static void main(String[] args) {
        char[][] board = new char[9][9];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        writeSudoku(board);
        System.out.println("count = " + count);
    }

    public static void writeSudoku(char[][] board) {
        int n = 9;
        boolean[][] rowNum = new boolean[n][n]; // rowNum[i][x] 表示数独中第(i+1)行有数字(x+1)
        boolean[][] colNum = new boolean[n][n]; // colNum[x][j] 表示数独中第(j+1)列有数字(x+1)
        // nineBlocksNum[i/3][j/3][x] 表示数独中的某个九宫格 有数字(x+1)
        boolean[][][] nineBlocksNum = new boolean[3][3][n];
        dfs(board, 0, 0, rowNum, colNum, nineBlocksNum);
    }

    private static void dfs(char[][] board, int row, int col,
                            boolean[][] rowNum, boolean[][] colNum,
                            boolean[][][] nineBlocksNum) {
        while (board[row][col] != '.') {
            if (++col >= 9) {
                col = 0;
                row++;
            }
            if (row >= 9) {
                printArrayTwo(board);
                count++;
                return;
            }
        }
        int n = board.length;
        for (int d = 0; d < n; d++) {
            if (rowNum[row][d] || colNum[d][col] || nineBlocksNum[row / 3][col / 3][d]) {
                continue;
            }
            char ch = (char) (d + '1');
            board[row][col] = ch;
            rowNum[row][d] = colNum[d][col] = nineBlocksNum[row / 3][col / 3][d] = true;
            dfs(board, row, col, rowNum, colNum, nineBlocksNum);
            rowNum[row][d] = colNum[d][col] = nineBlocksNum[row / 3][col / 3][d] = false;
            board[row][col] = '.';
        }
    }

    private static void printArrayTwo(char[][] board) {
        System.out.println("=".repeat(60));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf("%-4c", board[i][j]);
            }
            System.out.println();
        }
    }
}
