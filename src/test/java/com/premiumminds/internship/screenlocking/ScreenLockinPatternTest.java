package com.premiumminds.internship.screenlocking;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2022.
 */
@RunWith(JUnit4.class)
public class ScreenLockinPatternTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public ScreenLockinPatternTest() {
  }

  @Test
  public void ScreenLockinPatternTestFirst1Length3Test() throws InterruptedException, ExecutionException, TimeoutException {
    ScreenLockinPattern slc = new ScreenLockinPattern();
    Future<Integer> count = slc.countPatternsFrom(1,3);
    Integer res = count.get(10, TimeUnit.SECONDS);
    assertEquals(31,res.intValue());
  }

  @Test
  public void ScreenLockinPatternTestFirst3Length2Test()  throws InterruptedException, ExecutionException, TimeoutException {
    Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(3, 2);
    Integer result = count.get(10, TimeUnit.SECONDS);
    assertEquals(5, result.intValue());
  }

  @Test
  public void ScreenLockinPatternTestnextValidPointsTestFirst3(){
    Set<Integer> expected = new TreeSet<>();
    expected.add(2);
    expected.add(4);
    expected.add(5);
    expected.add(6);
    expected.add(8);

    int[][] visited = {{1,2,3},{4,5,6},{7,8,9}};
    Set<Integer> s = ScreenLockinPattern.nextValidPoints(visited,3);
    assertEquals(expected,s);
  }

  @Test
  public void ScreenLockinPatternTestnextValidPointsTestFirst3Visited6(){
    Set<Integer> expected = new TreeSet<>();
    expected.add(2);
    expected.add(4);
    expected.add(5);
    expected.add(8);
    expected.add(9);

    int[][] visited = {{1,2,3},{4,5,0},{7,8,9}};
    Set<Integer> s = ScreenLockinPattern.nextValidPoints(visited,3);
    assertEquals(expected,s);
  }

  @Test
  public void ScreenLockinPatternTestnextValidPointsTestFirst3Visited5(){
    Set<Integer> expected = new TreeSet<>();
    expected.add(2);
    expected.add(4);
    expected.add(6);
    expected.add(7);
    expected.add(8);

    int[][] visited = {{1,2,3},{4,0,6},{7,8,9}};
    Set<Integer> s = ScreenLockinPattern.nextValidPoints(visited,3);
    assertEquals(expected,s);
  }

}