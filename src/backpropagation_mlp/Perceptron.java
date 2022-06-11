package backpropagation_mlp;

public class Perceptron extends Ejemplos {
    private double PesosJK[][] = new double[4][3]; 
    private double UmbralesJ[]= new double[4];
    private double PesosIJ[] = new double[4];
    private double UmbralesI[] = new double[1];
    private double lambda = 0.03;
    private double epsilon = 0.5;
    private double hj[] = new double[240];
    private double yj[] = new double[240];
    private double Deltaj[] = new double[240];
    private double hi[] = new double[60];
    private double yi[] = new double[60];
    private double Deltai[] = new double[60];
    private double ECM[] = new double[60];
    private double lower = -0.1;
    private double upper = 0.1;
    private double ECMT = 0;
    private double DeltaPesosJK[][] = new double[240][3];
    private double DeltaUmbralesJ[] = new double[240];
    private double DeltaPesosIJ[][] = new double[60][4];
    private double DeltaUmbralesI[] = new double[60];
    private double DeltaPesosJKRojos[][] = new double[4][3];
    private double DeltaUmbralesJRojos[] = new double[4];
    private double DeltaPesosIJRojos[] = new double[4];
    private double DeltaUmbralesIRojos[] = new double[1];
    private double NuevosPesosJK[][] = new double[4][3]; 
    private double NuevosUmbralesJ[]= new double[4];
    private double NuevosPesosIJ[] = new double[4];
    private double NuevosUmbralesI[] = new double[1];
        
    public void pesosJK() {
        for (int x=0; x < PesosJK.length; x++) {
            for (int y=0; y < PesosJK[x].length; y++) {
                
                PesosJK[x][y] = Math.random() * (upper - lower) + lower;
                this.PesosJK = PesosJK;
                //System.out.println("Pesos de JK: " + PesosJK[x][y]);
            }
            //System.out.println();
        }
    }
    public void umbralesJK() {
        for (int x=0; x < UmbralesJ.length; x++) {
                UmbralesJ[x] = Math.random() * (upper - lower) + lower;
                this.UmbralesJ = UmbralesJ;
                //System.out.println("Umbrales de JK: " + UmbralesJ[x]);
        }
    }
    public void pesosIJ() {
        for (int x=0; x < PesosIJ.length; x++) {
                PesosIJ[x] = Math.random() * (upper - lower) + lower;
                this.PesosIJ = PesosIJ;
                //System.out.println("Pesos de IJ: " + PesosIJ[x]);
        }
    }
    public void umbralesI() {
        for (int x=0; x < UmbralesI.length; x++) {
                UmbralesI[x] = Math.random() * (upper - lower) + lower;
                this.UmbralesI = UmbralesI;
                //System.out.println("Umbrales I: " + UmbralesI[x]);
        }
    }
    
   public void neuronasOcultas() {
       int y = 0;
       int z = 0;
       for(int n=0; n < hj.length;n++){
           if(z == 4){
               z = 0;
               y++;
           }
           hj[n] = (Posibles[y][0]*PesosJK[z][0])+(Posibles[y][1]*PesosJK[z][1])+(Posibles[y][2]*PesosJK[z][2])-UmbralesJ[z];
           z++;
       }
       
       for (int x=0; x < hj.length; x++) {
                //System.out.println("hj: " + hj[x]);
        }
       
       for(int m=0; m < yj.length;m++){
           yj[m] = (2/(1+Math.pow(Math.E, (-lambda*hj[m]))))-1;
       }
       for (int o=0; o < yj.length; o++) {
            //System.out.println("yj: " + yj[o]);
       }
       
       for(int p=0; p < Deltaj.length; p++){
           Deltaj[p] = (2*lambda*Math.pow(Math.E, (-lambda*hj[p])))/(Math.pow((1+Math.pow(Math.E, (-lambda*hj[p]))),2));
       }
       for (int o=0; o < Deltaj.length; o++) {
            //System.out.println("Deltaj: " + Deltaj[o]);
       }
   } 
   
   public void neuronasEfectoras() {
       int h = 0;
       for(int p=0; p < hi.length; p++) {
           hi[p] = (yj[h]*PesosIJ[0])+(yj[h+1]*PesosIJ[1])+(yj[h+2]*PesosIJ[2])+(yj[h+3]*PesosIJ[3])-UmbralesI[0];
           h = h + 4;
       }
       for (int o=0; o < hi.length; o++) {
            //System.out.println("hi: " + hi[o]);
       }
       
       for(int p=0; p < yi.length; p++) {
           yi[p] = (2/(1+Math.pow(Math.E, (-lambda*hi[p]))))-1;
           
       }
       for (int o=0; o < yi.length; o++) {
            //System.out.println("yi: " + yi[o]);
       }
       
       for(int p=0; p < Deltai.length; p++) {
           Deltai[p] = (2*lambda*Math.pow(Math.E, (-lambda*hi[p])))/(Math.pow((1+Math.pow(Math.E, (-lambda*hi[p]))),2));
           
       }
       for (int o=0; o < Deltai.length; o++) {
            //System.out.println("Deltai: " + Deltai[o]);
       }    
   }
   
   public void ECM(){
        for (int x=0; x < ECM.length; x++) {
            ECM[x] = Math.pow(Deseados[x][0]-yi[x],2);
            //System.out.println(ECM[x]);
            ECMT+=ECM[x];
       }
       ECMT = ECMT/60;
       //System.out.println("ECM: " + ECMT);
       //System.out.println("ECM Total: " + ECMT);
       ECMT=0;
   }
   
   public void VariacionesPesosJK(){
        int y = 0;
        int z = 0;
        int m = 0;
        int o = 0;
        int a = 0;
        int b = 0;
        for(int n=0; n <720;n++){
           if(z == 3){
               z = 0;
               y++;
           }
           
           if(o == 12){
               o=0;
               m++;
           }
           
           if(b == 4){  //AQUI
               b=0;
               a++;
               if(a == 4){
                   a=0;
               }
           }
           DeltaPesosJK[y][z] = epsilon*((Deseados[m][0]-yi[m])*Deltai[m]*PesosIJ[a])*Deltaj[y]*Posibles[m][z];
           z++;
           o++;
           b++;
        }
        for (int x=0; x < DeltaPesosJK.length; x++) {
            for (int v=0; v < DeltaPesosJK[x].length; v++) {
                //System.out.println(DeltaPesosJK[x][v]);
            }
            //System.out.println();
        }
   }
   public void VariacionesumbralesJK() {
        int y = 0;
        int z = 0;
        int m = 0;
        int o = 0;
        int a = 0;
        int b = 0;
        for(int n=0; n <240;n++){
           if(z == 3){
               z = 0;
               y++;
           }
           
           if(o == 4){   
               o=0;
               m++;
           }
           
           if(b == 4){  
               b=0;
               a++;
               if(a == 4){
                   a=0;
               }
           }
           DeltaUmbralesJ[n] = epsilon*((Deseados[m][0]-yi[m])*Deltai[m]*PesosIJ[a])*Deltaj[y]*(-1); 
           z++;
           o++;
           b++;
        }
        for (int x=0; x < DeltaUmbralesJ.length; x++) {
                //System.out.println("DeltaUmbralesJ: " + DeltaUmbralesJ[x]);
        }
    }
    public void VariacionespesosIJ() {
        int z = 0;
        int m = 0;
        int o = 0;
        for(int n=0; n <240;n++){
           if(z == 4){
               z = 0;
           }
           if(o == 4){
               o=0;
               m++;
           }
           DeltaPesosIJ[m][z] = epsilon*(Deseados[m][0]-yi[m])*Deltai[m]*yj[n];
           z++;
           o++;
        }
        for (int x=0; x < DeltaPesosIJ.length; x++) {
            for (int k=0; k < DeltaPesosIJ[x].length; k++) {
                //System.out.println("DeltaPesosIJ: " + DeltaPesosIJ[x][k]);
            }
        }
    }
    public void VariacionesumbralesI() {
        for(int n=0; n <60;n++){
            DeltaUmbralesI[n] = epsilon*(Deseados[n][0]-yi[n])*Deltai[n]*(-1);
            //System.out.println(DeltaUmbralesI[n]);
        }
    }
   public void VariacionesPesosJKTotales(){
        int m = 0;
        int o = 0;
        for(int n=0; n <12;n++){
           
           if(o == 3){
               o=0;
               m++;
           }
           DeltaPesosJKRojos[m][o] = DeltaPesosJK[m][o]+DeltaPesosJK[m+4][o]+DeltaPesosJK[m+8][o]+DeltaPesosJK[m+12][o]+DeltaPesosJK[m+16][o]+DeltaPesosJK[m+20][o]+DeltaPesosJK[m+24][o]+DeltaPesosJK[m+28][o]+DeltaPesosJK[m+32][o]+DeltaPesosJK[m+36][o]+DeltaPesosJK[m+40][o]+DeltaPesosJK[m+44][o]+DeltaPesosJK[m+48][o]+DeltaPesosJK[m+52][o]+DeltaPesosJK[m+56][o]+DeltaPesosJK[m+60][o]+
                                     DeltaPesosJK[m+64][o]+DeltaPesosJK[m+68][o]+DeltaPesosJK[m+72][o]+DeltaPesosJK[m+76][o]+DeltaPesosJK[m+80][o]+DeltaPesosJK[m+84][o]+DeltaPesosJK[m+88][o]+DeltaPesosJK[m+92][o]+DeltaPesosJK[m+96][o]+DeltaPesosJK[m+100][o]+DeltaPesosJK[m+104][o]+DeltaPesosJK[m+108][o]+DeltaPesosJK[m+112][o]+DeltaPesosJK[m+116][o]+DeltaPesosJK[m+120][o]+DeltaPesosJK[m+124][o]+
                                     DeltaPesosJK[m+128][o]+DeltaPesosJK[m+132][o]+DeltaPesosJK[m+136][o]+DeltaPesosJK[m+140][o]+DeltaPesosJK[m+144][o]+DeltaPesosJK[m+148][o]+DeltaPesosJK[m+152][o]+DeltaPesosJK[m+156][o]+DeltaPesosJK[m+160][o]+DeltaPesosJK[m+164][o]+DeltaPesosJK[m+168][o]+DeltaPesosJK[m+172][o]+DeltaPesosJK[m+176][o]+DeltaPesosJK[m+180][o]+DeltaPesosJK[m+184][o]+DeltaPesosJK[m+188][o]+DeltaPesosJK[m+192][o]+
                                     DeltaPesosJK[m+196][o]+DeltaPesosJK[m+200][o]+DeltaPesosJK[m+204][o]+DeltaPesosJK[m+208][o]+DeltaPesosJK[m+212][o]+DeltaPesosJK[m+216][o]+DeltaPesosJK[m+220][o]+DeltaPesosJK[m+224][o]+DeltaPesosJK[m+228][o]+DeltaPesosJK[m+232][o]+DeltaPesosJK[m+236][o];
           o++;
        }
        for (int x=0; x < DeltaPesosJKRojos.length; x++) {
            for (int k=0; k < DeltaPesosJKRojos[x].length; k++) {
                //System.out.println("DeltaPesosJKRojos: " + DeltaPesosJKRojos[x][k]);
            }
        }   
   }
   
   public void VariacionesumbralesJKTotales() {
       for(int n=0; n <DeltaUmbralesJRojos.length;n++){
           DeltaUmbralesJRojos[n]= DeltaUmbralesJ[n]+DeltaUmbralesJ[n+4]+DeltaUmbralesJ[n+8]+DeltaUmbralesJ[n+12]+DeltaUmbralesJ[n+16]+DeltaUmbralesJ[n+20]+DeltaUmbralesJ[n+24]+DeltaUmbralesJ[n+28]+DeltaUmbralesJ[n+32]+DeltaUmbralesJ[n+36]+DeltaUmbralesJ[n+40]+DeltaUmbralesJ[n+44]+DeltaUmbralesJ[n+48]+DeltaUmbralesJ[n+52]+DeltaUmbralesJ[n+56]+DeltaUmbralesJ[n+60]+
                                     DeltaUmbralesJ[n+64]+DeltaUmbralesJ[n+68]+DeltaUmbralesJ[n+72]+DeltaUmbralesJ[n+76]+DeltaUmbralesJ[n+80]+DeltaUmbralesJ[n+84]+DeltaUmbralesJ[n+88]+DeltaUmbralesJ[n+92]+DeltaUmbralesJ[n+96]+DeltaUmbralesJ[n+100]+DeltaUmbralesJ[n+104]+DeltaUmbralesJ[n+108]+DeltaUmbralesJ[n+112]+DeltaUmbralesJ[n+116]+DeltaUmbralesJ[n+120]+DeltaUmbralesJ[n+124]+
                                    DeltaUmbralesJ[n+128]+DeltaUmbralesJ[n+132]+DeltaUmbralesJ[n+136]+DeltaUmbralesJ[n+140]+DeltaUmbralesJ[n+144]+DeltaUmbralesJ[n+148]+DeltaUmbralesJ[n+152]+DeltaUmbralesJ[n+156]+DeltaUmbralesJ[n+160]+DeltaUmbralesJ[n+164]+DeltaUmbralesJ[n+168]+DeltaUmbralesJ[n+172]+DeltaUmbralesJ[n+176]+DeltaUmbralesJ[n+180]+DeltaUmbralesJ[n+184]+DeltaUmbralesJ[n+188]+DeltaUmbralesJ[n+192]+
                                     DeltaUmbralesJ[n+196]+DeltaUmbralesJ[n+200]+DeltaUmbralesJ[n+204]+DeltaUmbralesJ[n+208]+DeltaUmbralesJ[n+212]+DeltaUmbralesJ[n+216]+DeltaUmbralesJ[n+220]+DeltaUmbralesJ[n+224]+DeltaUmbralesJ[n+228]+DeltaUmbralesJ[n+232]+DeltaUmbralesJ[n+236];
        }
       for (int x=0; x < DeltaUmbralesJRojos.length; x++) {
                //System.out.println("DeltaUmbralesJRojos: " + DeltaUmbralesJRojos[x]);
        }
   }
   
   public void VariacionespesosIJTotales() {
        int m = 0;
        int o = 0;
        for(int n=0; n <4;n++){
           
           if(o == 4){
               o=0;
               m++;
           }

           DeltaPesosIJRojos[n]= DeltaPesosIJ[m][o]+DeltaPesosIJ[m+1][o]+DeltaPesosIJ[m+2][o]+DeltaPesosIJ[m+3][o]+DeltaPesosIJ[m+4][o]+DeltaPesosIJ[m+5][o]+DeltaPesosIJ[m+6][o]+DeltaPesosIJ[m+7][o]+DeltaPesosIJ[m+8][o]+DeltaPesosIJ[m+9][o]+DeltaPesosIJ[m+10][o]+DeltaPesosIJ[m+11][o]+DeltaPesosIJ[m+12][o]+DeltaPesosIJ[m+13][o]+DeltaPesosIJ[m+14][o]+DeltaPesosIJ[m+15][o]+
                                     DeltaPesosIJ[m+16][o]+DeltaPesosIJ[m+17][o]+DeltaPesosIJ[m+18][o]+DeltaPesosIJ[m+19][o]+DeltaPesosIJ[m+20][o]+DeltaPesosIJ[m+21][o]+DeltaPesosIJ[m+22][o]+DeltaPesosIJ[m+23][o]+DeltaPesosIJ[m+24][o]+DeltaPesosIJ[m+25][o]+DeltaPesosIJ[m+26][o]+DeltaPesosIJ[m+27][o]+DeltaPesosIJ[m+28][o]+DeltaPesosIJ[m+29][o]+DeltaPesosIJ[m+30][o]+DeltaPesosIJ[m+31][o]+
                                    DeltaPesosIJ[m+32][o]+DeltaPesosIJ[m+33][o]+DeltaPesosIJ[m+34][o]+DeltaPesosIJ[m+35][o]+DeltaPesosIJ[m+36][o]+DeltaPesosIJ[m+37][o]+DeltaPesosIJ[m+38][o]+DeltaPesosIJ[m+39][o]+DeltaPesosIJ[m+40][o]+DeltaPesosIJ[m+41][o]+DeltaPesosIJ[m+42][o]+DeltaPesosIJ[m+43][o]+DeltaPesosIJ[m+44][o]+DeltaPesosIJ[m+45][o]+DeltaPesosIJ[m+46][o]+DeltaPesosIJ[m+47][o]+DeltaPesosIJ[m+48][o]+
                                     DeltaPesosIJ[m+49][o]+DeltaPesosIJ[m+50][o]+DeltaPesosIJ[m+51][o]+DeltaPesosIJ[m+52][o]+DeltaPesosIJ[m+53][o]+DeltaPesosIJ[m+54][o]+DeltaPesosIJ[m+55][o]+DeltaPesosIJ[m+56][o]+DeltaPesosIJ[m+57][o]+DeltaPesosIJ[m+58][o]+DeltaPesosIJ[m+59][o];
            
           o++;
        }
        for (int x=0; x < DeltaPesosIJRojos.length; x++) {
                //System.out.println("DeltaPesosIJRojos: " + DeltaPesosIJRojos[x]);
        }
   }
   public void VariacionesumbralesITotales() {
       for(int n=0; n <DeltaUmbralesI.length;n++){
            DeltaUmbralesIRojos[0] =+ DeltaUmbralesI[n];
       }
       for(int m=0; m <1;m++) {
            //System.out.println("DeltaUmbralesIRojos: "+DeltaUmbralesIRojos[m]);
       }  
   }
   public void NuevosPesosJK(){ 
       for (int b=0; b < NuevosPesosJK.length; b++) {
            for (int c=0; c < NuevosPesosJK[b].length; c++) {
                NuevosPesosJK[b][c] = PesosJK[b][c]+DeltaPesosJKRojos[b][c];
                PesosJK[b][c] = NuevosPesosJK[b][c];
            }
       }
       
       for (int n=0; n < NuevosPesosJK.length; n++) {
            for (int m=0; m < NuevosPesosJK[n].length; m++) {
                //System.out.println("Nuevos Pesos JK: "+NuevosPesosJK[n][m]);
            }
       }
   }
   
   public void NuevosUmbralesJK(){
       for (int y=0; y < NuevosUmbralesJ.length; y++) {
            NuevosUmbralesJ[y] = UmbralesJ[y] + DeltaUmbralesJRojos[y];
            UmbralesJ[y] = NuevosUmbralesJ[y];
       }
       for (int a=0; a <NuevosUmbralesJ.length; a++) {
            //System.out.println("Nuevos Umbrales JK: "+NuevosUmbralesJ[a]);
       }
   }

   public void NuevosPesosIJ(){
       for (int y=0; y < NuevosPesosIJ.length; y++) {
            NuevosPesosIJ[y] = PesosIJ[y] + DeltaPesosIJRojos[y];
            PesosIJ[y] = NuevosPesosIJ[y];
       }
       for (int a=0; a <NuevosPesosIJ.length; a++) {
            //System.out.println("Nuevos Pesos IJ: "+NuevosPesosIJ[a]);
       }
   }
    
   public void NuevosUmbralesIJ(){
       NuevosUmbralesI[0] =  UmbralesI[0] + DeltaUmbralesIRojos[0];
       //System.out.println("NuevosUmbrales I: "+NuevosUmbralesI[0]);
       UmbralesI[0] = NuevosUmbralesI[0];
   }
}
