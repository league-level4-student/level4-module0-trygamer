package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		int y = randGen.nextInt(w);
		int x = randGen.nextInt(h);
		Cell c = maze.getCell(x, y);

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(c);

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> c2 = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (c2.size() > 0) {

			// C1. select one at random.
			int y = randGen.nextInt(c2.size());

			// C2. push it to the stack
			uncheckedCells.push(c2.get(y));
			// C3. remove the wall between the two cells
			removeWalls(currentCell, c2.get(y));
			// C4. make the new cell the current cell and mark it as visited
			currentCell = c2.get(y);
			currentCell.setBeenVisited(true);

			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}

		// D. if all neighbors are visited
		else {
			// D1. if the stack is not empty
			if (!uncheckedCells.empty()) {

				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
				// D1b. make that the current cell

				// D1c. call the selectNextPath method with the current cell

				selectNextPath(currentCell);
			}
		}

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {

		if (c1.getY() == c2.getY()) {

			if (c1.getX() == c2.getX() + 1) {
				c1.setEastWall(false);
				c2.setWestWall(false);
				System.out.println("e");
			}

			if (c1.getX() == c2.getX() - 1) {
				c1.setWestWall(false);
				c2.setEastWall(false);
				System.out.println("w");

			}
		}

		if (c1.getX() == c2.getX()) {

			if (c1.getY() == c2.getY() + 1) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
				System.out.println("s");
			}

			if (c1.getY() == c2.getY() - 1) {
				c1.setNorthWall(false);
				c1.setSouthWall(false);
				System.out.println("n");

			}

		}

	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cellular = new ArrayList<Cell>();

		if (c.hasEastWall()) {

			if (c.getX() + 1 < 5 && !maze.c[c.getY()][c.getX() + 1].hasBeenVisited()) {

				cellular.add(maze.c[c.getY()][c.getX() + 1]);

			}
		}

		if (c.hasNorthWall()) {
			if (c.getY() - 1 > -1 && !maze.c[c.getY() - 1][c.getX()].hasBeenVisited()) {

				cellular.add(maze.c[c.getY() - 1][c.getX()]);

			}

		}

		if (c.hasSouthWall()) {

			if (c.getY() + 1 < 5 && !maze.c[c.getY() + 1][c.getX()].hasBeenVisited()) {

				cellular.add(maze.c[c.getY() + 1][c.getX()]);

			}

		}
		if (c.hasWestWall()) {

			if (c.getX() - 1 > -1 && !maze.c[c.getY()][c.getX() - 1].hasBeenVisited()) {

				cellular.add(maze.c[c.getY()][c.getX() - 1]);

			}

		}

		return cellular;
	}
}
