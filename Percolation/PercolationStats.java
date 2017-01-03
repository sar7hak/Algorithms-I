import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
   private int trials;
   private double[] results;
   private int nMax;

   public PercolationStats(int n, int trials) {
   		if (n > 0 && trials > 0) {
   			nMax = n;
   			this.trials = trials;
   			results = new double[trials];
            monteCarlo();
   		}
   		else
   			throw new java.lang.IllegalArgumentException("Argument cannot be zero");
   }   // perform trials independent experiments on an n-by-n grid

   public double mean() {
   		return StdStats.mean(results);
   }                       // sample mean of percolation threshold

   public double stddev() {
   		return StdStats.stddev(results);
   }                       // sample standard deviation of percolation threshold

   public double confidenceLo() {
   		return mean() - 1.96 * stddev() / Math.sqrt(trials);
   }                 // low  endpoint of 95% confidence interval

   public double confidenceHi() {
   		return mean() + 1.96 * stddev() / Math.sqrt(trials);
   }                 // high endpoint of 95% confidence interval

   private void monteCarlo() {
   		for (int i = 0; i < trials; i++) {
   			int num = 0;
   			Percolation trial = new Percolation(nMax);
   			while (!trial.percolates()) {
   				int x = StdRandom.uniform(1,nMax + 1);
   				int y = StdRandom.uniform(1,nMax + 1);
   				if (!trial.isOpen(x,y)) {
   					trial.open(x,y);
   					num++;
   				}
   			}
   			double count = num;
   			results[i] = count / (nMax*nMax);

   		}
   }
   public static void main(String[] args) {
   		int n = Integer.parseInt(args[0]);
   		int trials = Integer.parseInt(args[1]);
   		PercolationStats test = new PercolationStats(n,trials);
   		System.out.println("Mean = "+test.mean());
   		System.out.println("Stddev = "+test.stddev());
   		System.out.println("confidenceLo = "+test.confidenceLo());
   		System.out.println("confidenceHi = "+test.confidenceHi());
   }   // test client (described below)
}