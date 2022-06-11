package backpropagation_mlp;

import java.awt.image.BufferedImage;

public class NuevoPerceptron {
    
    String Ruta3 = "C:\\Users\\CAMILO\\Documents\\UNIVERSIDAD\\INTELIGENCIA ARTIFICIAL\\Backpropagation_MLP\\images\\Foto0.png";
    Perceptron pesos = new Perceptron();
    ProcesaImagen observador = new ProcesaImagen();
    BufferedImage Imagen1 = observador.LeerImagen(Ruta3);
    int Entradas3D[][][] = observador.MatrizImagen(Imagen1);
    
    
    private double PesosJK[][] = new double[][]{{-3.804610605772264,-4.238915876716798,13.211304353822026},{-9.352145396707805,-6.605437303515475,-1.4256927267393196},
                                                 {-2.305038350268713,-2.5395443455497793,7.708125996281176},{-2.859593458655195,-2.8033954550359814,9.206036559329878}}; 
    private double UmbralesJ[]= new double[]{1.6218606793619457,1.2042411278737968,1.3102890458022507,0.7555905814132692};
    private double PesosIJ[] = new double[]{-81.93779426149051,-40.94243773430155,-41.83408009395586,-46.022227812081};
    private double UmbralesI[] = new double[]{90.96806222428498};
    private double hj[] = new double[1228800];
    private double yj[] = new double[1228800];
    private double hi[] = new double[307200];
    private double yi[] = new double[307200];
    private double lambda = 0.03;
    private int yiTotal[][][] = new int[3][480][640];
    private int yiT1[] = new int[307200];
    private int yiT2[] = new int[307200];
    private int yiT3[] = new int[307200];
    private double yiTotalT[][] = new double[3][307200];
    BufferedImage Imagen2;
    
    
    public void NeuronasOcultas(){       
       int z = 0;
       int y = 0;
       int v = 0;
       for(int c=0; c<480; c++) {
            for(int h=0; h<640; h++) {
                for(int w=0; w<4; w++) {
                        if(y == 4){
                            z = 0;
                            y=0;
                        }
                        //System.out.println(v + " | " + c +" | "+ h + " | " + z);
                        hj[v] = (Entradas3D[0][c][h]*PesosJK[z][0])+(Entradas3D[1][c][h]*PesosJK[z][1])+(Entradas3D[2][c][h]*PesosJK[z][2])-UmbralesJ[z];z++;
                        y++;
                        v++;
                }
            }
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
    }
    public void NeuronasEjectoras(){
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
            //System.out.println("yi: " + yi[o]);  //AQUI
       }
    }
    public void ImagenFinal() {
        for(int a = 0;a < yi.length;a++) {
            if(yi[a] < 0){  
               yiT1[a] = -1;
               yiT2[a] = -1;
               yiT3[a] = -1;
            } else{         
               yiT1[a] = 1;
               yiT2[a] = 1;
               yiT3[a] = 1;
            }
        }   
        for(int a = 0;a < yiT1.length;a++) {
            //System.out.println(yiT1[a]+" | "+yiT2[a]+" | "+yiT3[a]);  //AQUI
        }
        //System.out.println("");
        
        for(int a = 0;a < yi.length;a++) {
            yiTotalT[0][a] = yiT1[a];
            yiTotalT[1][a] = yiT2[a];
            yiTotalT[2][a] = yiT3[a];
        }
        
        
       int n = 0;
       for(int x=0;x<480;x++){
           for(int b=0;b<640;b++){
               yiTotal[0][x][b] = yiT1[n];
               yiTotal[1][x][b] = yiT2[n];
               yiTotal[2][x][b] = yiT3[n];
               n+=1;
           }
       
        }
       
       for(int c=0; c<yiTotal.length; c++) {
            for(int h=0; h<yiTotal[c].length; h++) {
                for(int w=0; w<yiTotal[c][h].length; w++) {
                    //System.out.print(yiTotal[c][h][w]+"\t");
                }
                //System.out.println("");
            }
            //System.out.println("");
        }
       
       for(int a = 0;a < yiTotalT.length;a++) {
            for(int b = 0;b < yiTotalT[a].length;b++) {
            //System.out.print(yiTotalT[a][b]+"\t");  //AQUI
            }
            //System.out.println("");
        }
       
       Imagen2 = observador.ModificarImagen(Imagen1, yiTotalT);
       
    }

    /*public double[] getYi() {
        return yi;
    }

    public double[][] getYiTotalT() {
        return yiTotalT;
    }*/

    public BufferedImage getImagen2() {
        return Imagen2;
    }
     
}
