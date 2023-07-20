package com.project.text;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageText {
    private String path;
    BufferedImage image;
    private int width;
    private int height;
    private int escala_x;
    private int escala_y;
    private String blackText;
    private String greyText;
    private String whiteText;
    private int[] pixels;

    public ImageText(String path, int escala_x, int escala_y, String blackText, String greyTest, String whiteText){
        this.path = path;
        this.escala_x = escala_x;
        this.escala_y = escala_y; //Escala de la imagen
        this.blackText = blackText;
        this.greyText = greyTest; //Texto para letras negras, grises, blancas
        this.whiteText = whiteText;
        try {
            image = ImageIO.read(new File(this.path));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        width = image.getWidth();
        height = image.getHeight();
        pixels = genPixels();
    }

    public int[] genPixels(){
        //Extrae los pixeles en una lista int
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
        return pixels;
    }


    public void generarTexto(String nameFile){
        try{
            FileWriter writer = new FileWriter("txt//"+nameFile, false);
            BufferedWriter buffer = new BufferedWriter(writer);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    //Convierte los valores de los pixeles en los estandar RGB entre 0 y 255
                    int pixelValue = pixels[y * width + x];
                    int red = (pixelValue >> 16) & 0xff;
                    int green = (pixelValue >> 8) & 0xff;
                    int blue = pixelValue & 0xff;
                    //clasifica los colores entre blancos, negros, grises, y repite el texto según la escala
                    if (red<=100 && green<=100 && blue<=100){
                        for (int i=0; i<escala_x; i++){
                            buffer.write(blackText);
                        }
                        
                    }
                    else if (red<=200 && green<=200 && blue<=200 && red>=100 && green>=100 && blue>=100){
                        for (int i=0; i<escala_x; i++){
                            buffer.write(greyText);
                        }
                    }
                    else{
                        for (int i=0; i<escala_x; i++){
                            buffer.write(whiteText);
                        }
                    }
                }
                for (int i=0; i<escala_y; i++){
                    buffer.newLine();
                        }
            }
            buffer.close();
        }
        catch (IOException e){
            e.printStackTrace();

        }
    }
}
