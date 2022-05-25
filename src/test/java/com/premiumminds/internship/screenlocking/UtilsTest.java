package com.premiumminds.internship.screenlocking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Set;
import java.util.TreeSet;

@RunWith(JUnit4.class)
public class UtilsTest {

    public UtilsTest(){
    }

    @Test
    public void getPointsBetweenRowTest1() {
        Set<Integer> expected = new TreeSet<>();
        expected.add(4);
        expected.add(5);
        expected.add(6);

        Set<Integer> s = new TreeSet<>();
        int[][] visited = {{1,2,3},{4,5,6},{7,8,9}};

        Utils.getPointsBetweenRow(s::add,1,visited);
        assertEquals(expected,s);

        s.clear();
        Utils.getPointsBetweenRow(s::add,2,visited);
        assertEquals(expected,s);

        s.clear();
        Utils.getPointsBetweenRow(s::add,3,visited);
        assertEquals(expected,s);

        s.clear();
        Utils.getPointsBetweenRow(s::add,7,visited);
        assertEquals(expected,s);

        s.clear();
        Utils.getPointsBetweenRow(s::add,8,visited);
        assertEquals(expected,s);

        s.clear();
        Utils.getPointsBetweenRow(s::add,9,visited);
        assertEquals(expected,s);
    }

    @Test
    public void getPointsBetweenRowTest2() {
        Set<Integer> expected = new TreeSet<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(7);
        expected.add(8);
        expected.add(9);

        Set<Integer> s = new TreeSet<>();
        int[][] visited = {{1,2,3},{4,5,6},{7,8,9}};

        Utils.getPointsBetweenRow(s::add,4,visited);
        assertEquals(expected,s);
        s.clear();
        Utils.getPointsBetweenRow(s::add,5,visited);
        assertEquals(expected,s);
        s.clear();
        Utils.getPointsBetweenRow(s::add,6,visited);
        assertEquals(expected,s);
    }

    @Test
    public void getPointsBetweenColumnTest1() {
        Set<Integer> expected = new TreeSet<>();
        expected.add(2);
        expected.add(5);
        expected.add(8);

        Set<Integer> s = new TreeSet<>();
        int[][] visited = {{1,2,3},{4,5,6},{7,8,9}};

        Utils.getPointsBetweenColumn(s::add,4,visited);
        assertEquals(expected,s);

        s.clear();
        Utils.getPointsBetweenColumn(s::add,4,visited);
        assertEquals(expected,s);

        s.clear();
        Utils.getPointsBetweenColumn(s::add,7,visited);
        assertEquals(expected,s);
    }

    @Test
    public void getPointsBetweenColumnTest2() {
        Set<Integer> expected = new TreeSet<>();
        expected.add(1);
        expected.add(4);
        expected.add(7);
        expected.add(3);
        expected.add(6);
        expected.add(9);

        Set<Integer> s = new TreeSet<>();
        int[][] visited = {{1,2,3},{4,5,6},{7,8,9}};

        Utils.getPointsBetweenColumn(s::add,2,visited);
        assertEquals(expected,s);

        s.clear();
        Utils.getPointsBetweenColumn(s::add,5,visited);
        assertEquals(expected,s);

        s.clear();
        Utils.getPointsBetweenColumn(s::add,8,visited);
        assertEquals(expected,s);
    }

    @Test
    public void getPointsBetweenColumnTest3() {
        Set<Integer> expected = new TreeSet<>();
        expected.add(2);
        expected.add(5);
        expected.add(8);

        Set<Integer> s = new TreeSet<>();
        int[][] visited = {{1,2,3},{4,5,6},{7,8,9}};

        Utils.getPointsBetweenColumn(s::add,3,visited);
        assertEquals(expected,s);

        s.clear();
        Utils.getPointsBetweenColumn(s::add,6,visited);
        assertEquals(expected,s);

        s.clear();
        Utils.getPointsBetweenColumn(s::add,9,visited);
        assertEquals(expected,s);
    }

    @Test
    public void getHorizontalPoints() {
        Set<Integer> expected = new TreeSet<>();

        Set<Integer> s = new TreeSet<>();
        int[][] visited = {{1,2,3},{4,5,6},{7,8,9}};

        /*
            Any point visited yet, from 1 we expect to reach point 2 (horizontally).
         */
        expected.add(2);
        Utils.getHorizontalPoints(s::add,1,visited);
        assertEquals(expected,s);

        /*
            Let's assume that point 2 was already visited
         */
        visited[0][1] = 0; // mark point 2 as visited
        s.clear();
        expected.clear();
        expected.add(3);
        Utils.getHorizontalPoints(s::add,1,visited);
        assertEquals(expected,s);

        /*
            Let's assume all points in the row were already visited
         */
        visited[0][2] = 0;  // mark point 3 as visited
        s.clear();
        expected.clear();
        Utils.getHorizontalPoints(s::add,1,visited);
        assertEquals(expected,s);

        /*
            Let's assume that only point 3 was already visited
         */
        visited[0][1] = 2;  // mark point 2 as not visited
        visited[0][2] = 0;  // mark point 3 as visited
        s.clear();
        expected.clear();
        expected.add(2);
        Utils.getHorizontalPoints(s::add,1,visited);
        assertEquals(expected,s);
    }

    @Test
    public void getVerticalPoints() {
        Set<Integer> expected = new TreeSet<>();
        Set<Integer> s = new TreeSet<>();
        int[][] visited = {{1,2,3},{4,5,6},{7,8,9}};

        /*
            Any point visited yet, from 1 we expect to reach point 4 (vertically).
         */
        expected.add(4);
        Utils.getVerticalPoints(s::add,1,visited);
        assertEquals(expected,s);

        /*
            Assuming only point 4 was visited
         */
        s.clear();
        visited[1][0] = 0; // mark point 4 as visited
        expected.clear();
        expected.add(7);
        Utils.getVerticalPoints(s::add,1,visited);
        assertEquals(expected,s);

        /*
            Assuming all points in the col were already visited
         */
        s.clear();
        visited[2][0] = 0;  // mark point 7 as visited
        expected.clear(); // Set is empty
        Utils.getVerticalPoints(s::add,1,visited);
        assertEquals(expected,s);
    }

    @Test
    public void getDiagonalPoints() {
        Set<Integer> expected = new TreeSet<>();
        Set<Integer> s = new TreeSet<>();
        int[][] visited = {{1,2,3},{4,5,6},{7,8,9}};

        /*
           Start Point: 5
           Expected: 1,3,7,9
         */
        expected.add(1);
        expected.add(3);
        expected.add(7);
        expected.add(9);
        Utils.getDiagonalPoints(s::add,5,visited);
        assertEquals(expected,s);

        /*
           Start Point: 1
           Expected: 5
         */
        expected.clear();
        expected.add(5);
        s.clear();
        Utils.getDiagonalPoints(s::add,1,visited);
        assertEquals(expected,s);
    }


}
