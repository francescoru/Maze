package maze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Maze
{
    void printSolution(int maze[][], int sol[][], int height, int width)
    {
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
                System.out.print(" " + sol[i][j] +
                        " ");
            System.out.println();
        }
    }


    boolean isSafe(int maze[][], int x, int y, int height, int width)
    {
        
        return (x >= 0 && x < height && y >= 0 &&
                y < width && maze[x][y] == 0);
    }


    boolean solveMaze(int maze[][], int height, int width, int startX, int startY, int endX, int endY)
    {
        int sol[][] = new int[height][width];
        int visited[][] = new int[height][width];


        if (solveMazeUtil(visited, maze, startX, startY, sol, endX, endY, height, width) == false)
        {
            System.out.print("Solution doesn't exist");
            return false;
        }

        printSolution(maze, sol, height, width);
        return true;
    }

    
    boolean solveMazeUtil(int visited[][], int maze[][], int x, int y,
                          int sol[][], int endX, int endY, int height, int width)
    {


        if (x == endX && y == endY)
        {
            sol[x][y] = 1;
            return true;
        }

        
        if (isSafe(maze, x, y,height,width) == true)
        {
            
              if(visited[x][y] == 1) {
            return false;
        }

        visited[x][y] = 1;
         
            sol[x][y] = 1;

            if (solveMazeUtil(visited, maze, x + 1, y, sol, endX, endY,height,width))
                return true;

       
            if (solveMazeUtil(visited, maze, x, y + 1, sol, endX, endY,height,width))
                return true;

            if (solveMazeUtil(visited, maze, x - 1, y, sol, endX, endY,height,width))
                return true;

            if (solveMazeUtil(visited, maze, x, y - 1, sol, endX, endY,height,width))
                return true;

           
            sol[x][y] = 0;
            return false;
        }

        return false;
    }

    public static void main(String args[]) throws FileNotFoundException {
        Maze player = new Maze();

        String filename  = "input.txt";
        Scanner scanner = new Scanner(new FileInputStream(new File(filename)));

        String firstLine = scanner.nextLine();
        String[] sizeArray = firstLine.split(" ");
        int width = new Integer(sizeArray[0]);
        int height = new Integer(sizeArray[1]);

        String secondLine = scanner.nextLine();
        String[] startPosition = secondLine.split(" ");
        int startX = new Integer(startPosition[0]);
        int startY = new Integer(startPosition[1]);

        String thirdLine = scanner.nextLine();
        String[] endPosition = thirdLine.split(" ");
        int endX = new Integer(endPosition[0]);
        int endY = new Integer(endPosition[1]);

        int[][] maze = new int[height][width];
        for(int i = 0; i < height; i++) {
            String currentRow = scanner.nextLine();
            String[] colArray = currentRow.split(" ");
          
            for(int j = 0; j < width; j++) {
                maze[i][j] = Integer.parseInt(colArray[j]);
                System.out.print(maze[i][j] + " ");
            }
            System.out.println('\n');
        }

        scanner.close();

        player.solveMaze(maze, height, width, startX, startY, endX, endY);
    }
}
