import java.util.*;
public class PercolationBFS extends PercolationDFSFast{

	public PercolationBFS(int n) {
		super(n);
	}

	@Override
	protected void dfs(int row, int col) {
		int size= myGrid.length;
		Queue<Integer> qt = new LinkedList<>();
		myGrid[row][col] = FULL;                        

		int value= row*size + col;
		qt.add(value);

		while (qt.size() != 0){
			value= qt.peek();
			row= value/ size;
			col= value % size;

			if (inBounds(row-1, col) && isOpen(row-1, col) && !isFull(row-1, col)) {
				myGrid[row-1][col]= FULL;
				qt.add((row-1)* size + col);
			}
			if (inBounds(row, col-1) && isOpen(row, col-1) && !isFull(row, col-1)) {
				myGrid[row][col-1]= FULL;
				qt.add(row* size + col-1);
			}
			if (inBounds(row+1, col) && isOpen(row+1, col) && !isFull(row+1, col)) {
				myGrid[row+1][col]= FULL;
				qt.add((row+1)* size + col);
			}
			if (inBounds(row, col+1) && isOpen(row, col+1) && !isFull(row, col+1)) {
				myGrid[row][col+1]= FULL;
				qt.add(row* size + col+1);
			}
			qt.remove(value);

		}
	}
}

