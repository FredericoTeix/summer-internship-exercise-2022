package com.premiumminds.internship.screenlocking;

import java.util.function.Consumer;

public class Utils {

    /**
     * Method to add points from the rows above and below currPoint, as possible
     * next valid points.
     * @param c consumer of Points
     * @param currPoint current Point
     * @param visited matrix of positions
     */
    public static void getPointsBetweenRow(Consumer<Integer> c, Integer currPoint, int[][] visited){
        int row, point;
        row = (currPoint - 1)/3;

        // Row Above
        if(row - 1 >= 0){
            for(int col = 0; col < visited[0].length; col++){
                point = visited[row-1][col];
                if (point != 0) {
                    c.accept(point);
                }
            }
        }

        // Row Below
        if(row + 1 < 3){
            for(int col = 0; col < visited[0].length; col++){
                point = visited[row+1][col];
                if (point != 0) {
                    c.accept(point);
                }
            }
        }
    }

    /**
     * Method to add points from the columns on the left and on the right of the currPoint, as possible
     * next valid points.
     * @param c consumer of Points
     * @param currPoint current Point
     * @param visited matrix of positions
     */
    public static void getPointsBetweenColumn(Consumer<Integer> c, Integer currPoint, int[][] visited){
        int col,point;
        col = (currPoint - 1)%3;
        // Col Left
        if(col - 1 >= 0){
            for(int r = 0; r < visited.length; r++){
                point = visited[r][col-1];
                if (point != 0) {
                    c.accept(point);
                }
            }
        }

        // Col Right
        if(col + 1 < visited[0].length){
            for(int r = 0; r < visited.length; r++){
                point = visited[r][col+1];
                if (point != 0) {
                    c.accept(point);
                }
            }
        }
    }

    /**
     *
     * @param c consumer of Points
     * @param currPoint current Point
     * @param visited matrix of positions
     */
    public static void getHorizontalPoints(Consumer<Integer> c, Integer currPoint, int[][] visited){
        int row,col,point;
        row = (currPoint - 1)/3;
        col = (currPoint - 1)%3;

        // Check Horizontal Left
        while(--col >= 0){
            point = visited[row][col];
            if(point != 0) {
                c.accept(point);
                //System.out.println(String.format("%d <- %d",point, currPoint));
                break;
            }
        }

        col = (currPoint - 1)%3;

        // Check Horizontal Right
        while(++col < 3){
            point = visited[row][col];
            if(point != 0) {
                c.accept(point);
                //System.out.println(String.format("%d -> %d", currPoint, point));
                break;
            }
        }

    }

    /**
     * Method to search for Vertical Points that were not visited.
     * @param c consumer of Points
     * @param currPoint current Point
     * @param visited matrix of positions
     */
    public static void getVerticalPoints(Consumer<Integer> c, Integer currPoint, int[][] visited){
        int row,col,point;
        row = (currPoint - 1)/3;
        col = (currPoint - 1)%3;

        // Check Vertical Up
        while(--row >= 0){
            point = visited[row][col];
            if(point != 0) {
                c.accept(point);
                //System.out.println(String.format("%d is above %d",point, currPoint));
                break;
            }
        }

        // Check Vertical Down
        row = (currPoint - 1)/3;
        while(++row < 3){
            point = visited[row][col];
            if(point != 0) {
                c.accept(point);
                //System.out.println(String.format("%d is below %d",point, currPoint));
                break;
            }
        }
    }

    /**
     * Method to search for diagonal Points that were not visited. Similiar to BFS.
     * @param c consumer of Points
     * @param currPoint current Point
     * @param visited matrix of positions
     */
    public static void getDiagonalPoints(Consumer<Integer> c, Integer currPoint, int[][] visited){
        int row,col,point;
        row = (currPoint - 1)/3;
        col = (currPoint - 1)%3;

        // Check diagonal /
        while(--row >= 0 && ++col < 3){
            point = visited[row][col];
            if(point != 0){
                c.accept(point);
                break;
            }
        }

        row = (currPoint - 1)/3;
        col = (currPoint - 1)%3;
        while(++row < 3 && --col >= 0){
            point = visited[row][col];
            if(point != 0){
                c.accept(point);
                break;
            }
        }

        // Check diagonal \
        row = (currPoint - 1)/3;
        col = (currPoint - 1)%3;
        while(--row >= 0 && --col >= 0){
            point = visited[row][col];
            if(point != 0){
                c.accept(point);
                break;
            }
        }

        row = (currPoint - 1)/3;
        col = (currPoint - 1)%3;
        while(++row < 3 && ++col < 3){
            point = visited[row][col];
            if(point != 0){
                c.accept(point);
                break;
            }
        }
    }

}
