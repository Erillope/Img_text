/**
 *
 * @author Erillope
 */
package com.project.text;

public class Text {
    public static void main(String[] args) {
        ImageText image = new ImageText("imagenes//takagixd.png", 2, 1, "█", "a", " ");
        
        image.generarTexto("imagen_text.txt");
        //Las imágenes se ingresan en la carpeta de imágenes y los textos se guardan en la carpeta txt
        //Al abrir el block de notas, configurar el tamaño de la fuente a 1 para que se visualize bien.

    }
}

