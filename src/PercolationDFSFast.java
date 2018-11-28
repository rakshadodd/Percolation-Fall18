
public class PercolationDFSFast extends PercolationDFS{
	/**
	 * super constructor extended from PercolationDFS
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
	//if (isFull(row,col)) return;
	if (row==0 && !isFull(row, col)
			|| inBounds(row -1 , col)  && isFull(row - 1, col)
			|| inBounds(row, col -1) && isFull(row, col -1)
			|| inBounds(row, col +1) && isFull(row, col + 1)
			|| inBounds(row +1, col) && isFull(row +1, col)){

				dfs(row, col);
		}
	}	
}
