package backpropagation_mlp;

import java.awt.image.BufferedImage;

public class Main {
    
    private void Ejecutar(){
        int Iterar = 0;
        
        Perceptron Datos = new Perceptron();
        NuevoPerceptron pesosfinales = new NuevoPerceptron();
        ProcesaImagen observador = new ProcesaImagen();
        String Ruta4 = "C:\\Users\\CAMILO\\Documents\\UNIVERSIDAD\\INTELIGENCIA ARTIFICIAL\\Backpropagation_MLP\\images\\ModImage1.png";
        Datos.pesosJK();
        Datos.pesosIJ();
        Datos.umbralesJK();
        Datos.umbralesI();
        pesosfinales.NeuronasOcultas();
        
        while(Iterar < 55000){
            Datos.Posibles();
            Datos.Deseados();
            Datos.neuronasOcultas();
            Datos.neuronasEfectoras();
            Datos.ECM();
            Datos.VariacionesPesosJK();
            Datos.VariacionesumbralesJK();
            Datos.VariacionespesosIJ();
            Datos.VariacionesumbralesI();
            Datos.VariacionesPesosJKTotales();
            Datos.VariacionesumbralesJKTotales();
            Datos.VariacionespesosIJTotales();
            Datos.VariacionesumbralesITotales();
            Datos.NuevosPesosJK();
            Datos.NuevosUmbralesJK();
            Datos.NuevosPesosIJ();
            Datos.NuevosUmbralesIJ();
            Iterar++;
        }
        
        pesosfinales.NeuronasEjectoras();
        pesosfinales.ImagenFinal();
        
        BufferedImage Imagen3 = pesosfinales.getImagen2();
        observador.GuardarImagen(Imagen3, Ruta4);
        
        int Array3D[][][] = observador.MatrizImagen(Imagen3);
        int cont = 0;
        
        for(int c=0; c<Array3D.length; c++)
        {
            for(int h=0; h<Array3D[c].length; h++)
            {
                for(int w=0; w<Array3D[c][h].length; w++)
                {
                    System.out.print(Array3D[c][h][w]+"\t");
                    //System.out.println(c + " | " + h + " | " + w);
                    cont++;
                }
                System.out.println("");
            }
            System.out.println("");
        }
        System.out.println("Contador: " + cont);
                
    }
    public static void main(String[] args) {
        FrmCamara.main(args);
        new Main().Ejecutar();  
    }
    
}
