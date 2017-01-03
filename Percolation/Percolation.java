import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private int nMax;
   private boolean[][] grid;
   private WeightedQuickUnionUF quickUnion;
   private WeightedQuickUnionUF percCheck;

   public Percolation(int n) {
   		if (n > 0) {
   			nMax = n;
   			quickUnion = new WeightedQuickUnionUF(n*n + 1);
            percCheck = new WeightedQuickUnionUF(n*n + 2);
   			grid = new boolean[n][n];
   		}
   		else
   			throw new java.lang.IllegalArgumentException("Index cannot be less than or equal to 0");
   } // create n-by-n grid, with all sites blocked

   private boolean isValid(int row, int col) {
   		return row <= nMax && col <= nMax && row > 0 && col > 0;
   } // checks if the row and column are valid

   public void open(int row, int col) {
   		if (isValid(row, col)) {
   			grid[row - 1][col - 1] = true;
   			//System.out.println("in for "+row+" and "+col);
   			if (isValid(row - 1, col) && isOpen(row - 1, col)) {
   				quickUnion.union(nMax*(row - 2) + col - 1, nMax*(row - 1) + col - 1);
               percCheck.union(nMax*(row - 2) + col - 1, nMax*(row - 1) + col - 1);
            }
   			if (isValid(row, col - 1) && isOpen(row, col -1)) {
   				quickUnion.union(nMax*(row - 1) + col - 2, nMax*(row - 1) + col - 1);
               percCheck.union(nMax*(row - 1) + col - 2, nMax*(row - 1) + col - 1);
            }

   			if (isValid(row, col + 1) && isOpen(row, col + 1)) {
   				quickUnion.union(nMax*(row - 1) + col, nMax*(row - 1) + col - 1);
               percCheck.union(nMax*(row - 1) + col, nMax*(row - 1) + col - 1);
            }
   			if (isValid(row + 1, col) && isOpen(row + 1, col)) {
   				quickUnion.union(nMax*(row) + col - 1, nMax*(row - 1) + col - 1);
               percCheck.union(nMax*(row) + col - 1, nMax*(row - 1) + col - 1);
            }

            if (row == 1) {
               quickUnion.union(nMax*nMax, col - 1); 
               percCheck.union(nMax*nMax, col - 1);
            }
            if (row == nMax) 
               percCheck.union(nMax*nMax + 1, nMax*(row - 1) + col - 1);
   		}
   		else
   			throw new java.lang.IndexOutOfBoundsException("nMax is "+nMax+" called for "+row+" and "+col);
   } // open site (row, col) if it is not open already

   public boolean isOpen(int row, int col) {
   		if (isValid(row, col)) {
   			return grid[row - 1][col - 1];
   		}
   		else
   			throw new java.lang.IndexOutOfBoundsException("nMax is "+nMax+" called for "+row+" and "+col);
   } // is site (row, col) open?

   public boolean isFull(int row, int col) {
         if (isValid(row, col))
             return quickUnion.connected(nMax*nMax,nMax*(row - 1) + col - 1);
         else
            throw new java.lang.IndexOutOfBoundsException("nMax is "+nMax+" called for "+row+" and "+col);
   } // is site (row, col) full?

   public boolean percolates() {
   		return percCheck.connected(nMax*nMax,nMax*nMax + 1);
   } // does the system percolate?

   private void printGrid() {
   		for (int i = 0; i < nMax; i++) {
   			for (int j = 0; j < nMax; j++)
   				if (grid[i][j])
   					System.out.print(" x ");
   				else
   					System.out.print(" - ");
   			System.out.print("\n");
   		}
   }

   public static void main(String[] args) {
   		Percolation test = new Percolation(5);
   		for (int i = 1; i <= 5; i++){
   			test.open(1,i);
   			if (i == 4)
   				test.open(2,i);
   			if (!(i == 3 || i == 5))
   				test.open(3,i);
   			if (i == 4)
   				test.open(4,i);
   			if (i != 2)
   				test.open(5,i);
   		}
        // test.open(2,1);
         //test.open(1,1);
   		System.out.println(test.isFull(3,1));
   		System.out.println(test.percolates());
         System.out.println(test.isFull(3,1));
   		test.printGrid();
   } // test client (optional)
}