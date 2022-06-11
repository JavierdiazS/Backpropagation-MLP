package backpropagation_mlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class RegistraTxt {
    
    private PrintWriter writer = null;
    private BufferedReader reader = null;
    
    private void AbrirArchivo(String Path, String absolutePath)
    {
        try
        {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(absolutePath)));
        }
        catch(Exception ex)
        {
            System.out.println("FALLA al Abrir el Archivo: '"+Path+"' -> "+ex.getMessage());
        }
    }
    
    public double[][] LeerArchivo(String Path)
    {
        int fil=0, col=0;
        String sMatrix[][] = null;
        
        try
        {
            String linea;
            File Archivo = new File(Path);
            String absolutePath = Archivo.getAbsolutePath();
            
            if(Archivo.exists())
            {
                AbrirArchivo(Path, absolutePath);
                
                while((linea = reader.readLine())!=null)
                {
                    fil++;
                    StringTokenizer tokenizer = new StringTokenizer(linea, " / ");
                    if(fil==1)
                    {
                        col = tokenizer.countTokens();
                    }
                }
                
                reader.close();
                AbrirArchivo(Path, absolutePath);
                
                int i = 0;
                sMatrix = new String[fil][col];
                while((linea = reader.readLine())!=null)
                {
                    StringTokenizer tokenizer = new StringTokenizer(linea, " / ");
                    int datos = tokenizer.countTokens();
                    for(int j=0; j<datos; j++)
                    {
                        sMatrix[i][j] = tokenizer.nextToken();
                    }
                    i++;
                }
                
                reader.close();
            }
            else
            {
                System.out.println("NO se encuentra el Archivo: '"+Path+"'..!");
            }
        }
        catch (Exception ex)
        {
            System.out.println("FALLA al Leer el Archivo: '"+Path+"' -> "+ex.getMessage());
        }
        
        double Matrix[][] = new double[fil][col];
        for(int a=0; a<sMatrix.length; a++)
        {
            for(int b=0; b<sMatrix[0].length; b++)
            {
                Matrix[a][b] = Double.parseDouble(sMatrix[a][b]);
            }
        }
        
        return Matrix;
    }
    
    public void EscribirArchivo(double[][] Matrix, String Path)
    {
        String sMatrix[][];
        
        try
        {
            sMatrix = new String[Matrix.length][Matrix[0].length];
            for(int i=0; i<Matrix.length; i++)
            {
                for(int j=0; j<Matrix[0].length; j++)
                {
                    sMatrix[i][j] = Double.toString(Matrix[i][j]);
                }
            }
            
            try
            {
                writer = new PrintWriter(new FileOutputStream(Path));
                
                for(int i=0; i<sMatrix.length; i++)
                {
                    for(int j=0; j<sMatrix[0].length; j++)
                    {
                        if(j<sMatrix[0].length-1)
                        {
                            writer.print(sMatrix[i][j]+" / ");
                        }
                        else
                        {
                            writer.print(sMatrix[i][j]);
                        }
                    }
                    writer.println();
                }
                
                writer.close();
            }
            catch (FileNotFoundException ex)
            {
                System.out.println("FALLA al Escribir el Archivo: '"+Path+"' -> "+ex.getMessage());
            }
        }
        catch (Exception ex)
        {
            System.out.println("FALLA al Registrar las Sinapsis -> "+ex.getMessage());
        }
    }
}