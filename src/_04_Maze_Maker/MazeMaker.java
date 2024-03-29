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

		
		System.out.println("c1x "+c1.getX());
		System.out.println("c1y "+c1.getY());
		System.out.println("c2x "+c2.getX());
		System.out.println("c2y "+c2.getY());
		
		if (c1.getY() == c2.getY()) {

			if (c1.getX() == c2.getX() + 1) {
				c2.setEastWall(false);
				c1.setWestWall(false);
				
			}

			if (c1.getX() == c2.getX() - 1) {
				c2.setWestWall(false);
				c1.setEastWall(false);
				

			}
		}

		if (c1.getX() == c2.getX()) {

			if (c1.getY() == c2.getY() + 1) {
				c2.setSouthWall(false);
				c1.setNorthWall(false);
				
			}

			if (c1.getY() == c2.getY() - 1) {
				c2.setNorthWall(false);
				c1.setSouthWall(false);
				

			}

		}

	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> cellular = new ArrayList<Cell>();
int xc = 0;
int yc = 0;
	//	if (c.hasEastWall()) {



for(int i = c.getX()-1; i< c.getX()+2;i++) {
	
	xc++;
	
	if(i<5&&i>-1&& !maze.c[i][c.getY()].hasBeenVisited()) {
		cellular.add(maze.c[i][c.getY()]);
	
	}
}

	for(int j = c.getY()-1; j< c.getY()+2;j++) {
	
		yc++;
	
	if(j>-1&&j<5&& !maze.c[c.getX()][j].hasBeenVisited()) {
		cellular.add(maze.c[c.getX()][j]);
	

	}
	
	}
	
//
		//	if (c.getX() + 1 < 5 && !maze.c[c.getY()][c.getX() + 1].hasBeenVisited()) {

		//		cellular.add(maze.c[c.getY()][c.getX() + 1]);
				

			
	//	}

	//	if (c.hasNorthWall()) {
	//		if (c.getY() - 1 > -1 && !maze.c[c.getY() - 1][c.getX()].hasBeenVisited()) {
//
		//		cellular.add(maze.c[c.getY() - 1][c.getX()]);
//nn++;
			

	//	}

		//if (c.hasSouthWall()) {

		//	if (c.getY() + 1 < 5 && !maze.c[c.getY() + 1][c.getX()].hasBeenVisited()) {

		//		cellular.add(maze.c[c.getY() + 1][c.getX()]);
//nn++;
			

	//	}
	//	if (c.hasWestWall()) {

		//	if (c.getX() - 1 > -1 && !maze.c[c.getY()][c.getX() - 1].hasBeenVisited()) {

			//	cellular.add(maze.c[c.getY()][c.getX() - 1]);
//nn++;
		//	}

	//	}
	

		return cellular;
	}
}

