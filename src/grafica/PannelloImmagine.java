/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

/**
 *
 * @author scanferla.giovanni
 */
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PannelloImmagine extends JPanel{

    private BufferedImage immagine;
    private String imgPath;

    public PannelloImmagine(String imgPath){
        this.setLayout(new BorderLayout());
        this.imgPath = imgPath;
		try {
			immagine = ImageIO.read(new File(imgPath));
		} catch (IOException ex) {
			
		}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(immagine, ((this.getWidth() - immagine.getWidth())/2), ((this.getHeight()- immagine.getHeight())/2), null);
    }
	
    public BufferedImage getImmagine() {
        return immagine;
    }

    public void setImmagine(BufferedImage immagine) {
        this.immagine = immagine;
    }   

}
