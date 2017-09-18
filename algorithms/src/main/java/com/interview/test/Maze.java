package com.interview.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * A text file is given representing a rectangular field. The field contains a robot (R), a target (T), and obstacles (O).
 * Empty tiles are indicated with a dot (.). Your program must determine if the robot (R) can reach the target (T),
 * while only able to take steps North, South, East, West. The robot may only step on empty spaces (.),
 * and may not step on a tile with an obstacle or out of the field.
 * Given the text file, compute whether the robot can reach the target tile by stepping only on the empty tiles and print
 * the answer to the standard output. Print "yes" if the target is reachable or "no" otherwise.
 * You should also validate the input file format and print "error: <error description>" message if something is wrong.
 * Although generally not a good practice, your entire application should be submitted in a single file - it is
 * however encouraged to employ a reasonable class breakdown and structure within this file, depending on the language
 * you choose to use. You may use any of the functionality of your language's standard library,
 * but no external libraries beyond that.

 Text File Format

     Each line in the input file represents a row of the rectangular field. All the lines have the same length and can contain the following characters:

     A dot (.) - represents an empty tile. The robot can move to this tile.
     (R) - the initial position of the robot.
     (T) - the tile the robot is trying to reach.
     (O) - an obstacle. The robot cannot move to this tile.

    You may assume the entire file fits in memory.

 Usage

 $ ./yourprogramhere file.txt

 yes

 Examples

     Input text file:

     .R.O.
     OO.O.
     T....

     Output:

     yes



     Input text file:

     .ROOO
     OO...
     T....

     Output:

     no



     Input text file:

     .....
     .ROOO..
     T....

     Output:

     error: the field is not rectangular



     Input text file:

     .....
     ..OOO
     T....

     Output:

     error: no robot on the board
 */
public class Maze {

    //Start time: Sep 8th 20:13 ADT
    //End time: Sep 8th 21:11 ADT
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("error: incorrect number of arguments. Usage: Maze.class <file-name>");
            return;
        }

        String fileName = args[0];
        try {
            MazeBoard maze = new MazeBoard(fileName);
            if (maze.canRobotReachTarget()) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        } catch(Exception e) {
            System.out.println("error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static class MazeBoard {
        private boolean[][] maze;

        private int robotRow = -1;
        private int robotCol = -1;

        private int targetRow = -1;
        private int targetCol = -1;

        public MazeBoard(String fileName) throws IOException {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            if (lines == null || lines.isEmpty()) {
                throw new IllegalArgumentException("the field is empty");
            }

            this.maze = new boolean[lines.size()][];
            int mazeWidth = -1;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                char[] row = line.toCharArray();
                if (mazeWidth != -1 && mazeWidth != row.length) {
                    throw new IllegalArgumentException("the field is not rectangular");
                }
                mazeWidth = row.length;

                this.maze[i] = new boolean[mazeWidth];
                for (int j = 0; j < mazeWidth; j++) {
                    parseCell(row[j], i, j);
                }
            }

            if (robotRow == -1 || robotCol == -1) {
                throw new IllegalStateException("no robot on the board");
            }

            if (targetRow == -1 || targetCol == -1) {
                throw new IllegalStateException("no target on the board");
            }
        }

        private void parseCell(char sign, int row, int col) {
            switch (sign) {
                case 'R':
                    initRobot(row, col);
                    //position of the robot is not an obstacle, so mark it as allowed cell for moving
                case '.':
                    maze[row][col] = true;
                    break;
                case 'T':
                    initTarget(row, col);
                    //position of the target is an obstacle
                case 'O':
                    //by default array is initialized with all values 'false', so do nothing here
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field value: " + sign);
            }
        }

        private void initRobot(int i, int j) {
            if (robotRow != -1 || robotCol != -1) {
                throw new IllegalArgumentException("multiple robots on the board");
            }
            robotRow = i;
            robotCol = j;
        }

        private void initTarget(int i, int j) {
            if (targetRow != -1 || targetCol != -1) {
                throw new IllegalArgumentException("multiple targets on the board");
            }
            targetRow = i;
            targetCol = j;
        }

        public boolean canRobotReachTarget() {
            return isTargetReached(robotRow, robotCol);
        }

        private boolean isTargetReached(int row, int col) {
            //validate borders
            if (row < 0 || row >= maze.length || col < 0 || col >= maze[row].length) return false;

            if (row == targetRow && col == targetCol) return true;

            if (!maze[row][col]) return false;

            //Instead of keeping visited[] array let's re-use the maze[][], just mark visited as obstacle
            maze[row][col] = false;

            if (isTargetReached(row, col + 1)) return true;
            if (isTargetReached(row, col - 1)) return true;
            if (isTargetReached(row + 1, col)) return true;
            if (isTargetReached(row - 1, col)) return true;
            return false;
        }
    }
}
