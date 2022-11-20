package backpropagation_mlp;

public class MultiplicaMatrices {
    public int[] ViMd_Vi(int A[], double B[][])
    {
        int R[] = null;
        
        if(A.length == B.length)
        {
            double dA[] = new double[A.length];
            for(int j=0; j<dA.length; j++)
            {
                dA[j] = (double)A[j];
            }
            
            double dR[] = new double[B[0].length];
            double dRT[] = new double[B[0].length];
            for(int j=0; j<B[0].length; j++)
            {
                dR[j] = 0;
                dRT[j] = 0;
                for(int k=0; k<dA.length; k++)
                {
                    dR[j] += (dA[k] * (10*B[k][j]));
                    if(dR[j] < 0) {
                        dRT[j] = 255;
                    } else if(dR[j] > 0) {
                        dRT[j] = 0;
                    }
                }
            }
            
            R = new int[B[0].length];
            for(int j=0; j<R.length; j++)
            {
                R[j] = (int)dRT[j];
            }
        }
        else
        {
            System.out.println("NO se puede Multiplicar A*B");
        }
        
        return R;
    }
}
