package com.premiumminds.internship.screenlocking;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.*;

/**
 * Created by aamado on 05-05-2022.
 */
class ScreenLockinPattern implements IScreenLockinPattern {

    ExecutorService executorService;

    /**
     * Method to count patterns from firstPoint with the length
     * @param firstPoint initial matrix position
     * @param length the number of points used in pattern
     * @return number of patterns
     */
    public Future<Integer> countPatternsFrom(int firstPoint, int length) {
        int[][] matrix = {{1,2,3},
                          {4,5,6},
                          {7,8,9}};

        executorService = Executors.newFixedThreadPool(1);
        Future<Integer> asyncRes = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return countPatternsFromRecursively(matrix,firstPoint,length);
            }
        });
        executorService.shutdown();

        return asyncRes;
    };

    /**
     * Method to count patterns from currPosition with the length, taking
     * into account previously visited positions.
     * @param visited matrix of positions
     * @param currPoint actual matrix position
     * @param length the number of points used in pattern
     * @return number of patterns
     */
    private Integer countPatternsFromRecursively(int[][] visited, int currPoint, int length) {
        int pos = currPoint - 1;
        visited[pos/3][pos%3] = 0; // mark currPoint as visited

        if(length == 0){
            return 0;
        }else if (length == 1){
            return 1;
        }

        int sum = 0;
        Set<Integer> validPoints = nextValidPoints(visited,currPoint);
        for(int p: validPoints){
            // Clone visited array, it should be the same for the set of validPoints
            int[][] temp = new int[visited.length][visited[0].length];
            for(int i = 0; i < temp.length; i++){
                temp[i] = Arrays.copyOf(visited[i],temp.length);
            }

            // Recursion
            sum += countPatternsFromRecursively(temp, p,length - 1);
        }
        return sum;
    };

    public static Set<Integer> nextValidPoints(int[][] visited, int currPoint){
        Set<Integer> validPoints = new TreeSet<>();
        Utils.getPointsBetweenRow(validPoints::add,currPoint,visited);
        Utils.getPointsBetweenColumn(validPoints::add,currPoint,visited);
        Utils.getHorizontalPoints(validPoints::add,currPoint,visited);
        Utils.getVerticalPoints(validPoints::add,currPoint,visited);
        Utils.getDiagonalPoints(validPoints::add,currPoint,visited);
        return validPoints;
    }

}