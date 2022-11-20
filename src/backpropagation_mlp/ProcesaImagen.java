package backpropagation_mlp;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ProcesaImagen
{
    
    public ProcesaImagen(){
        
    }
    
    public BufferedImage LeerImagen(String Path)
    {
        File Archivo = new File(Path);
        BufferedImage Imagen = null;
        
        try
        {
            Imagen = ImageIO.read(Archivo);
        }
        catch(IOException ex)
        {
            System.out.print("Error al Leer la Imagen: "+ex.getMessage());
        }
        
        return Imagen;
    }
    
    public void GuardarImagen(BufferedImage Imagen, String Path)
    {
        try
        {
            ImageIO.write(Imagen, "PNG", new File(Path));
        }
        catch(IOException ex)
        {
            System.out.print("Error al Guardar la Imagen: "+ex.getMessage());
        }
    }
    
    public BufferedImage ModificarImagen(BufferedImage Original, double[][] valores)
    {
        Color color;
        int a, inputRGB[] = new int[3];
        int RGB, outputRGB[] = new int[3];
        int width = Original.getWidth(), height = Original.getHeight();
        BufferedImage Modificada = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        
        for(int h=0; h<Original.getHeight(); h++)
        {
            for(int w=0; w<Original.getWidth(); w++)
            {
                
                
                color = new Color(Original.getRGB(w, h));
                a = color.getAlpha();
                inputRGB[0] = color.getRed();
                inputRGB[1] = color.getGreen();
                inputRGB[2] = color.getBlue();
                
                outputRGB = ModificarPixel(inputRGB, valores);
                
                RGB = (a << 24) | (outputRGB[0] << 16) | (outputRGB[1] << 8) | outputRGB[2];
                
                Modificada.setRGB(w, h, RGB);
            }
        }
        
        return Modificada;
    }
    
    private int[] ModificarPixel(int[] inputRGB, double[][] valores)
    {
        int outputRGB[] = new int[3];
        MultiplicaMatrices mm = new MultiplicaMatrices();
        outputRGB = mm.ViMd_Vi(inputRGB, valores);
        return outputRGB;
    }
    
    public int[][][] MatrizImagen(BufferedImage Imagen)
    {
        
        int Array3D[][][] = new int[3][Imagen.getHeight()][Imagen.getWidth()];  
        
        for(int h=0; h<Imagen.getHeight(); h++)
        {
            for(int w=0; w<Imagen.getWidth(); w++)
            {
                Color color = new Color(Imagen.getRGB(w, h));
                Array3D[0][h][w] = color.getRed();
                Array3D[1][h][w] = color.getGreen();
                Array3D[2][h][w] = color.getBlue();
            }
        }
        
        return Array3D;
    }
}

