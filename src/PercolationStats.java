public class PercolationStats {
	private int runs;
	private double [] results;
		
		   public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
		   {
			   if (N <= 0 || T <= 0) {
				   throw new IllegalArgumentException("Either n <= 0 or T <= 0");
			   }			    
			   results = new double[T];
			   runs = T;
			   for (int x = 0; x < T; x++) {
				   Percolation p = new Percolation(N);
				   int opens = 0;
				   while (!p.percolates()) {
					   int i = StdRandom.uniform(1, N+1);
					   int j = StdRandom.uniform(1, N+1);					   					 
					   if (!p.isOpen(i, j))
						   {						   
							   p.open(i, j);							  
							   opens++;
						   }					     
				   }
				   results[x] = (double) opens/(N*N);
			   }
		   }
		   
		   public double mean()                      // sample mean of percolation threshold
		   {
			   return StdStats.mean(results);
			   
		   }
		   public double stddev()                    // sample standard deviation of percolation threshold
		   {
			   return StdStats.stddev(results);
			   
		   }
		   public double confidenceLo()              // low  endpoint of 95% confidence interval
		   {
			   double result;
			   result = mean() - ((1.96*stddev())/Math.sqrt(runs));
			   return result;
			   
		   }
		   public double confidenceHi()              // high endpoint of 95% confidence interval
		   {
			   double result;
			   result = mean() + ((1.96*stddev())/Math.sqrt(runs));
			   return result;
			   
		   }

		   public static void main(String[] args)    // test client (described below)
		   {
			   PercolationStats PS;
			   int size, runs;
			   if (args.length > 1)
			   {
				   try {
					   size = Integer.parseInt(args[0]);
					   runs = Integer.parseInt(args[1]);
				   } catch (NumberFormatException e)
				   {
					   StdOut.printf("Both arguments must be numeric. Arg[0] = %s, Arg[1] = %s\n", args[0], args[1]); 
					  return;
				   }
				   
				   if (size < 0 || runs < 0) 
				   {
					   if (size <= 0)
						   StdOut.printf("Size parameter is < 0. Percolation Map Size = %s\n", args[0]);
					   if (runs <= 0)
						   StdOut.printf("You must specify a positive number of iterations to perform. Runs = %s\n", args[1]); 
					   return;
				   }
			   }
			   else
			   {				   			  
				   size = 200;
				   runs = 100;

				   
			   }
			   PS = new PercolationStats(size, runs);
			 

			   StdOut.printf("mean                     = %f \n", PS.mean());
			   StdOut.printf("stddev                   = %f \n", PS.stddev());
			   StdOut.printf("95%% confidence interval = %f, %f ", PS.confidenceLo(), PS.confidenceHi());
		}
}
