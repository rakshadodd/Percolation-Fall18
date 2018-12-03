
public class PercolationDFSFast extends PercolationDFS{
	/**
	 * super constructor extended from PercolationDFS
	 * @param n an integer used a initialize a grid of size n X n
	 */
	public PercolationDFSFast(int n) {
		super(n);
	}
	/**
	 * calls dfs if the given cell needs to be marked full, which happens if the cell is in the the top row
	 * or adjacent to a full cell
	 * @param row is the row coordinate of the cell being checked/marked
	 * @param col is the col coordinate of the cell being checked/marked
	 */
	@Override
	protected void updateOnOpen(int row, int col) {
	if (row==0 && !isFull(row, col)
			|| inBounds(row -1 , col)  && isFull(row - 1, col)
			|| inBounds(row, col -1) && isFull(row, col -1)
			|| inBounds(row, col +1) && isFull(row, col + 1)
			|| inBounds(row +1, col) && isFull(row +1, col)) {

				dfs(row, col);
		}
	}	
}
