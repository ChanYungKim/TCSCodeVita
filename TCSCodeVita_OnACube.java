//import static java.lang.Math.*;
import java.util.*;

public class TCSCodeVita_OnACube {
  
  public static int square (int n) {
    return n*n;
  }
  
  public static float shortestDistance (int xCurrent, int yCurrent, int zCurrent, int xTarget, int yTarget, int zTarget) {
    float distance = 0;

    //System.out.println(xCurrent + "   " + yCurrent + "   " + zCurrent);
    //System.out.println(xTarget + "   " + yTarget + "   " + zTarget);

    if (zCurrent == 10 || zTarget == 10) {
      if (xTarget == 0 || xTarget == 10) {
        int height = Math.abs(xTarget - xCurrent) + (10 - zTarget);

        distance = (float) Math.sqrt (square(height) + square(yTarget-yCurrent));
      }

      else if (yTarget == 0 || yTarget == 10) {
        int height = Math.abs(yTarget - yCurrent) + (10 - zTarget);

        distance = (float) Math.sqrt (square(height) + square(xTarget-xCurrent));
      }
      else {
        System.out.println("NO WAY");
        distance = -1;
      }
    }

    else {
      if ((xCurrent == 0 && xTarget == 10) || (xCurrent == 10 && xTarget == 0)) {
        int height = (10 - zCurrent) + (10 - zTarget) + 10;
        distance = (float) (Math.sqrt(square (height) + square (yTarget - yCurrent)));
      }
      else if ((yCurrent == 0 && yTarget == 10) || (yCurrent == 10 && yTarget == 0)) {
        int height = (10 - zCurrent) + (10 - zTarget) + 10;

        distance = (float) (Math.sqrt(square (height) + square (xTarget - xCurrent)));
      }
      else {
        System.out.println("NO WAY");
        distance = -1;
      }
    }
    //System.out.println(distance);
    return distance;
  }

  public static double calculateArcLength (double chordLength) {
    double radius = 0;
    radius = (chordLength/2) * (1/Math.sin(Math.PI/6));
    
    //System.out.println(radius);

    return radius * (Math.PI/3);
  }

  public static void main(String[] args) {
    // TEST CASE #1
    //int N = 3; // total number of points
    //int [] coordinates = {1,1,10,2,1,10,0,1,9};

    // TEST CASE #2
    //int N = 3; // total number of points
    //int [] coordinates = {1,1,10,2,1,10,0,5,9};

    // TEST CASE #3
    //int N = 2; // total number of points
    //int [] coordinates = {1,1,10,3,3,9};

    // TEST CASE #4
    //int N = 2; // total number of points
    //int [] coordinates = {0,1,3,10,3,1};

    // TEST CASE #5
    int N = 2; // total number of points
    int [] coordinates = {9,0,1,1,10,1};

    int [][] points = new int [3][N];

    int rowIndex = 0;
    int columnIndex = 0;
    int arrayIndex = 0;
    while (rowIndex < N) {
      points [columnIndex][rowIndex] = coordinates [arrayIndex];

      if (columnIndex < 2) {
        columnIndex ++;
      }
      else {
        columnIndex = 0;
        rowIndex ++;
      }

      arrayIndex ++;
    }
    float sumOfDifference = 0;
    float sumOfDifferenceSquare = 0;
    float distance = 0;
    //int [] difference = new int [3];
    boolean isOnTheSamePlane = false;
    for (int row = 0; row < N - 1; row ++) {
      sumOfDifference = 0;
      sumOfDifferenceSquare = 0;
      
      //int xCurrent, yCurrent, zCurrent, xTarget, yTarget, zTarget = 0;

      for (int coordinate = 0; coordinate < 3; coordinate ++) {
        sumOfDifference += Math.abs(points [coordinate][row] - points [coordinate][row+1]);
        /*if (coordinate == 2) {
          sumOfDifference += Math.abs (9 - points [coordinate][row]);
        }
        else {
          sumOfDifference += Math.abs(points [coordinate][row] - points [coordinate][row+1]);
        }*/
        
        sumOfDifferenceSquare += square (points [coordinate][row] - points [coordinate][row+1]);
        if (points [coordinate][row] - points [coordinate][row+1] == 0) {
          if (points [coordinate][row] == 0 || points [coordinate][row] == 10) {
            isOnTheSamePlane = true;
          }
        }
      }
      if (isOnTheSamePlane) {
        distance += calculateArcLength(Math.sqrt(sumOfDifferenceSquare));
        //distance += calculateArcLength(sumOfDifferenceSquare);
      }
      else {
        int xCurrent = points [0][row];
        int yCurrent = points [1][row];
        int zCurrent = points [2][row];

        int xTarget = points [0][row + 1];
        int yTarget = points [1][row + 1];
        int zTarget = points [2][row + 1];
        
        distance += shortestDistance (xCurrent, yCurrent, zCurrent, xTarget, yTarget, zTarget);
      }
      isOnTheSamePlane = false;
      //System.out.println(distance);
    }

  System.out.println(distance);
  }
}
