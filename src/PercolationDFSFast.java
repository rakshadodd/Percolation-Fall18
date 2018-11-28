
public class PercolationDFSFast extends PercolationDFS{
	/**
	 * super constructur extended from PercolationDFS
	 * @param n an integer used a initialize a grid of size n X n
	 */
	public PercolationDFSFast(int n) {
		super(n);
	}
	/**
	 * 
	 */
	@Override
	protected void updateOnOpen(int row, int col) {
	if (row==0 || inBounds(row -1 , col) && isFull(row - 1, col)
			|| inBounds(row, col -1) && isFull(row, col -1)
			|| inBounds(row, col +1) && isFull(row, col + 1)
			|| inBounds(row +1, col) && isFull(row +1, col)){

				dfs(row, col);
		}
	}	
}
