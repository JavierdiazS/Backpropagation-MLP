package backpropagation_mlp;

public class Perceptron extends Ejemplos {
    private double PesosJK[][] = new double[4][3]; 
    private double UmbralesJ[]= new double[4];
    private double PesosIJ[] = new double[4];
    private double UmbralesI = 0;
    private double Deltaj[] = new double[240];
    private double hj[] = new double[240];
    private double yj[] = new double[240]; 
    private double Deltai[] = new double[60];
    private double hi[] = new double[60];
    private double yi[] = new double[60];
    private double ECM[] = new double[60];
    private double DeltaPesosJK[][] = new double[240][3];
    private double DeltaUmbralesJ[] = new double[240];
    private double DeltaPesosIJ[][] = new double[60][4];
    private double DeltaUmbralesI[] = new double[60];
    private double DeltaPesosJKRojos[][] = new double[4][3];
    private double DeltaUmbralesJRojos[] = new double[4];
    private double DeltaPesosIJRojos[] = new double[4];
    private double DeltaUmbralesIRojos = 0;
    private double NuevosPesosJK[][] = new double[4][3]; 
    private double NuevosUmbralesJ[]= new double[4];
    private double NuevosPesosIJ[] = new double[4];
    private double NuevosUmbralesI = 0;
    private double lower = -0.1;
    private double upper = 0.1;
    private double ECMT = 0;
    private double lambda = 0.03;
    private double epsilon = 0.5;
        
    public void pesosJK() {
        for (int x=0; x < PesosJK.length; x++) {
            for (int y=0; y < PesosJK[x].length; y++) {
                PesosJK[x][y] = Math.random() * (upper - lower) + lower;
                this.PesosJK = PesosJK;
            }
        }
    }
    
    public void umbralesJK() {
        for (int x=0; x < UmbralesJ.length; x++) {
                UmbralesJ[x] = Math.random() * (upper - lower) + lower;
                this.UmbralesJ = UmbralesJ;
        }
    }
    
    public void pesosIJ() {
        for (int x=0; x < PesosIJ.length; x++) {
                PesosIJ[x] = Math.random() * (upper - lower) + lower;
                this.PesosIJ = PesosIJ;
        }
    }
    
    public void umbralesI() {
        UmbralesI = Math.random() * (upper - lower) + lower;
        this.UmbralesI = UmbralesI;
    }
    
   public void neuronasOcultas() {
        int n = 0;
        for(int y=0; y < 60;y++){
            for(int z = 0; z < 4; z++){
                hj[n] = (Posibles[y][0]*PesosJK[z][0])+(Posibles[y][1]*PesosJK[z][1])+(Posibles[y][2]*PesosJK[z][2])-UmbralesJ[z];
                n++;
            }
                
        }
       
       for(int m=0; m < yj.length;m++){
           yj[m] = (2/(1+Math.pow(Math.E, (-lambda*hj[m]))))-1;
       }
       
       for(int p=0; p < Deltaj.length; p++){
           Deltaj[p] = (2*lambda*Math.pow(Math.E, (-lambda*hj[p])))/(Math.pow((1+Math.pow(Math.E, (-lambda*hj[p]))),2));
       }
   } 
   
   public void neuronasEfectoras() {
       int h = 0;
       for(int p=0; p < hi.length; p++) {
           hi[p] = (yj[h]*PesosIJ[0])+(yj[h+1]*PesosIJ[1])+(yj[h+2]*PesosIJ[2])+(yj[h+3]*PesosIJ[3])-UmbralesI;
           h = h + 4;
       }
       
       for(int p=0; p < yi.length; p++) {
           yi[p] = (2/(1+Math.pow(Math.E, (-lambda*hi[p]))))-1;
       }
       
       for(int p=0; p < Deltai.length; p++) {
           Deltai[p] = (2*lambda*Math.pow(Math.E, (-lambda*hi[p])))/(Math.pow((1+Math.pow(Math.E, (-lambda*hi[p]))),2)); 
       }   
   }
   
   public void ECM(){
        for (int x=0; x < ECM.length; x++) {
            ECM[x] = Math.pow(Deseados[x][0]-yi[x],2);
            ECMT+=ECM[x];
       }
       ECMT = ECMT/60;
       ECMT=0;
   }
   
   public void VariacionesPesosJK(){
        int y = 0;
        int z = 0;
        int m = 0;
        int o = 0;
        int a = 0;
        int b = 0;

        for (int n = 0; n < 720; n++) {
            DeltaPesosJK[y][z] = epsilon * ((Deseados[m][0] - yi[m]) * Deltai[m] * PesosIJ[a]) * Deltaj[y] * Posibles[m][z];
            z = (z + 1) % 3;
            if (z == 0) {
                y++;
            }

            o = (o + 1) % 12;
            if (o == 0) {
                m++;
            }

            b = (b + 1) % 4;
            if (b == 0) {    
                a = (a + 1) % 4;
            }
        }
   }
   
   
   public void VariacionesumbralesJK() {
        int y = 0;
        int z = 0;
        int m = 0;
        int o = 0;
        int a = 0;
        int b = 0;

        for (int n = 0; n < 240; n++) {
            DeltaUmbralesJ[n] = epsilon * ((Deseados[m][0] - yi[m]) * Deltai[m] * PesosIJ[a]) * Deltaj[y] * (-1);
            z = (z + 1) % 3;
            if (z == 0) {
                y++;
            }
    
            o = (o + 1) % 4;
            if (o == 0) {
                m++;
            }
    
            b = (b + 1) % 4;
            if (b == 0) {
                a = (a + 1) % 4;
            }
        }
    }
    
    public void VariacionespesosIJ() {
        int m = 0;
        int z = 0;

        for (int n = 0; n < 240; n++) {
            DeltaPesosIJ[m][z] = epsilon * (Deseados[m][0] - yi[m]) * Deltai[m] * yj[n];
            z = (z + 1) % 4;
            if (z == 0) {
                m++;
            }
        }
    }
    
    public void VariacionesumbralesI() {
        for(int n=0; n <60;n++){
            DeltaUmbralesI[n] = epsilon*(Deseados[n][0]-yi[n])*Deltai[n]*(-1);
        }
    }
    
   public void VariacionesPesosJKTotales(){
        int m = 0;
        int o = 0;

        for (int n = 0; n < 12; n++) {
            for (int i = 0; i < 24; i += 4) {
                for (int j = 0; j < 49; j += 4) {
                    DeltaPesosJKRojos[m][o] = DeltaPesosJK[m + i][o] + DeltaPesosJK[m + i + j][o];
                    o++;
                }
                m++;
                o = 0;
            }
        } 
   }
   
    public void VariacionesumbralesJKTotales() {
       for (int n = 0; n < DeltaUmbralesJRojos.length; n++) {
           int sum = 0;
           for (int i = 0; i < 240; i += 4) {
               sum += DeltaUmbralesJ[n + i];
           }
           DeltaUmbralesJRojos[n] = sum;
      }
   }
   
   public void VariacionespesosIJTotales() {
        int m = 0;
        int o = 0;

        for (int n = 0; n < 4; n++) {
            int sum = 0;
            for (int i = 0; i < 60; i++) {
                sum += DeltaPesosIJ[m + i][o];
                if ((i + 1) % 4 == 0) {
                    o++;
                }
            }
            DeltaPesosIJRojos[n] = sum;
            if (o == 4) {
                o = 0;
                m += 60;
            }
        }
   }
   
   public void VariacionesumbralesITotales() {
       for(int n=0; n <DeltaUmbralesI.length;n++){
            DeltaUmbralesIRojos += DeltaUmbralesI[n];
       }  
   }
    
   public void NuevosPesosJK(){ 
       for (int b=0; b < NuevosPesosJK.length; b++) {
            for (int c=0; c < NuevosPesosJK[b].length; c++) {
                NuevosPesosJK[b][c] = PesosJK[b][c]+DeltaPesosJKRojos[b][c];
                PesosJK[b][c] = NuevosPesosJK[b][c];
            }
       }
   }
   
   public void NuevosUmbralesJK(){
       for (int y=0; y < NuevosUmbralesJ.length; y++) {
            NuevosUmbralesJ[y] = UmbralesJ[y] + DeltaUmbralesJRojos[y];
            UmbralesJ[y] = NuevosUmbralesJ[y];
       }
   }

   public void NuevosPesosIJ(){
       for (int y=0; y < NuevosPesosIJ.length; y++) {
            NuevosPesosIJ[y] = PesosIJ[y] + DeltaPesosIJRojos[y];
            PesosIJ[y] = NuevosPesosIJ[y];
       }
   }
    
   public void NuevosUmbralesIJ(){
       NuevosUmbralesI =  UmbralesI + DeltaUmbralesIRojos;
       UmbralesI = NuevosUmbralesI;
   }
}
