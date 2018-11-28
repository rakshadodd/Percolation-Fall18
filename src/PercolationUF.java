
public class PercolationUF implements IPercolate{
	boolean[][] myGrid;
	int myOpenCount;
	IUnionFind myFinder; //= new QuickUWPC();
	private final int VTOP;
	private final int VBOTTOM;
	
	public PercolationUF(int size, IUnionFind finder) {
		VTOP= size*size;
		VBOTTOM= size*size +1;
		finder.initialize(size*size + 2);
		myFinder= finder;
		myOpenCount=0;
		myGrid= new boolean[size][size];
	}

	/**
	 * Open site (row, col) if it is not already open. By convention, (0, 0)
	 * is the upper-left site
	 * The method modifies internal state so that determining if percolation
	 * occurs could change after taking a step in the simulation.
	 * 
	 * @param row
	 *            row index in range [0,N-1]
	 * @param col
	 *            column index in range [0,N-1]
	 */
	@Override
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (myGrid[row][col]!= true) {
			myGrid[row][col] = true;
			myOpenCount+= 1;
			int cell= getIndex(row, col);
			if (row==0) {
				myFinder.union(cell, VTOP);
			}
			if (row== myGrid.length-1) {
				myFinder.union(cell, VBOTTOM);
			}
			if (inBounds(row-1, col) && isOpen(row-1, col)) {
				myFinder.union(cell, getIndex(row-1,col));
			}
			if (inBounds(row, col-1) && isOpen(row, col-1)) {
				myFinder.union(cell, getIndex(row, col-1));
			}
			if (inBounds(row+1, col) && isOpen(row+1, col)) {
				myFinder.union(cell, getIndex(row+1,col));
			}
			if (inBounds(row, col+1) && isOpen(row, col+1)) {
				myFinder.union(cell, getIndex(row, col+1));
			}
		}
	}

	/**
	 * Returns true if and only if site (row, col) is OPEN
	 * 
	 * @param row
	 *            row index in range [0,N-1]
	 * @param col
	 *            column index in range [0,N-1]
	 */
	@Override
	public boolean isOpen(int row, int col) {
		
		if (!inBounds(row, col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		
		return myGrid[row][col];
	}


	/**
	 * Returns true if and only if site (row, col) is FULL
	 * 
	 * @param row
	 *            row index in range [0,N-1]
	 * @param col
	 *            column index in range [0,N-1]
	 */
	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		
		return myFinder.connected(getIndex(row, col), VTOP);
	}

	/**
	 * Returns true if the simulated percolation actually percolates. What it
	 * means to percolate could depend on the system being simulated, but
	 * returning true typically means there's a connected path from
	 * top-to-bottom.
	 * 
	 * @return true if the simulated system percolates
	 */
	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
		
	/**
	 * Returns the number of distinct sites that have been opened in this
	 * simulation
	 * @return number of open sites
	 */
	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	/**
	 * Helper method to check if a given row and col index value are within myGrid
	 * @param row index in range [0,N-1]
	 * @param col index in range [0,N-1]
	 * @return true if (row,col) on grid, false otherwise
	 */
	public boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	
	/**
	 * Helper method to create a corresponding one-digit integer value for a given row and column
	 * @param row index in range [0,N-1]
	 * @param col index in range [0,N-1]
	 * @return the integer value for myFinder
	 */
	public int getIndex(int row, int col) {
		if (row==0) return col;
		else return row*myGrid.length + col;
	}
}
