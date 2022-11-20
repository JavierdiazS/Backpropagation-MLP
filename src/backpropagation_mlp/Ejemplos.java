package backpropagation_mlp;

public class Ejemplos {
    private String Ruta1 = "data/Posibles.txt";
    private String Ruta2 = "data/Deseados.txt";
    private RegistraTxt archivo = new RegistraTxt();
    double Posibles[][] = archivo.LeerArchivo(Ruta1);
    double Deseados[][] = archivo.LeerArchivo(Ruta2);
}
