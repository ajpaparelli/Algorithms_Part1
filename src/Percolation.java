  
public class Percolation {

    private static final boolean BLOCKED = false;
    private static final boolean OPEN = true;
    
    private boolean[] grid;
    private WeightedQuickUnionUF UF, BW;
    private int max;
    
    
    
    public Percolation(int N)
    {
    	if (N <= 0) {
    		throw new IllegalArgumentException();
    	}
    		
        max = N;
        grid = new boolean[N*N+2];
        
       UF = new WeightedQuickUnionUF((N*N)+2);
       BW = new WeightedQuickUnionUF((N*N)+1);
       
        for (int i = 1; i < N*N; i++) 
        {
                grid[i] = BLOCKED;                                           
        }        
    }
    
    public void open(int i, int j)
    {
    	if (i <= 0 || i > max || j <= 0 || j > max)
    	{
    		throw new IndexOutOfBoundsException();
    	}

    	int r = i-1;
    	int c = j-1;
    	int id = (r*max)+c+1;
        
    	grid[id] = OPEN;
    	if (r != 0 && isOpen(i-1, j)) {
    		UF.union(id, id-max);
    		BW.union(id, id-max);
    	}
    	if (r != max-1 && isOpen(i+1, j)) {

  			UF.union(id, id+max);
  			BW.union(id, id+max);
    	}
    	if (c != 0 && isOpen(i, j -1)) {
    		UF.union(id, id-1);
    		BW.union(id, id-1);
    	}
    	if (c != max -1 && isOpen(i, j+1)) {
    		UF.union(id, id+1);
    		BW.union(id, id+1);
    	}
    	
    	if (r == max-1)
    	{
            UF.union((max*max)+1, id); 
    	}
    	if (r == 0) {
    		UF.union(0, id);
    		BW.union(0, id);
    	}
    	             
    }
    
    public boolean isOpen(int i, int j)
    {
    	if (i <= 0 || i > max || j <= 0 || j > max)
    	{
    		throw new IndexOutOfBoundsException();
    	}
    	int r = i-1;
    	int c = j-1;
    	int id = (r*max) + c +1;
        return grid[id] == OPEN;
    }
    
    public boolean isFull(int i, int j)
    {
    	if (i <= 0 || i > max || j <= 0 || j > max)
    	{
    		throw new IndexOutOfBoundsException();
    	}
    	int r = i-1;
    	int c = j-1;
    	int id = (r*max)+c+1;
    	return BW.connected(0, id) && UF.connected(0, id);
    }
        
    public boolean percolates()
    {
        return UF.connected(0, (max*max)+1);
    }
    
    public static void main(String[] args)
    {
       int x = 5;
       int i, j;
       if (args.length > 0)
       {
    	   In s = new In(args[0]);
    	   x = s.readInt();
    	   Percolation P = new Percolation(x);
    	   while (s.hasNextChar())
    	   {
    		   i = s.readInt();
    		   j = s.readInt();
    			   P.open(i, j);
    		   if (P.percolates())
    		   	break;
    	   }
       }
       else
       {
       Percolation P = new Percolation(x);
       while (!P.percolates())
       {
    	   i = StdRandom.uniform(1, x+1);
    	   j = StdRandom.uniform(1, x+1);
    	   if (!P.isOpen(i, j))
    	   {
    		   P.open(i, j);
    	   }
       }
       }
       
    }
}
   