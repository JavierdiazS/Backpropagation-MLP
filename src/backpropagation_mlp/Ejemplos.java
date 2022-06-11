package backpropagation_mlp;

public class Ejemplos {
    private String Ruta1 = "data/Posibles.txt";
    private String Ruta2 = "data/Deseados.txt";
    private String Ruta3 = "data/Written.txt";
    private RegistraTxt archivo = new RegistraTxt();
    
    private double Matriz[][] = new double[][]{{4.8, 6.2, 6.7},
                                               {0.5, 2.1, 3.0},
                                               {9.0, 1.2, 4.5}};
    double Posibles[][] = archivo.LeerArchivo(Ruta1);
    double Deseados[][] = archivo.LeerArchivo(Ruta2);
    
    public void Deseados(){
        for(int i=0; i<Deseados.length; i++){
            for(int j=0; j<Deseados[i].length; j++){
                //System.out.println(Deseados[i][j]+"\t");
            }
            //System.out.println("");
        }
        //System.out.println("");
    }
    public void Posibles(){
        for(int i=0; i<Posibles.length; i++){
            for(int j=0; j<Posibles[i].length; j++){
                //System.out.println(i+" | "+j);
            }
            //System.out.println("");
        }
        //System.out.println("");
    }
    public void Escrbir(){
        archivo.EscribirArchivo(Matriz, Ruta3);
    }
}
