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
import rubrica.GlobalVariables;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PannelloImmagine extends JPanel implements MouseListener{

    private BufferedImage immagine;
    private String imgPath;
    private boolean imgExist;

    public PannelloImmagine(String imgPath){
        this.setLayout(new BorderLayout());
        this.imgPath = imgPath;
		try {
			immagine = ImageIO.read(new File(imgPath));
            imgExist = true;
		} catch (IOException ex) {
			this.setBackground(Color.WHITE);
            imgExist = false;
		} catch (NullPointerException ex){
            this.setBackground(Color.WHITE);
            imgExist = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try{
            g.drawImage(immagine,0, 0, this.getWidth(), this.getHeight(), null);
        } catch(NullPointerException e){

        }
    }
	
    public BufferedImage getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        try {
            this.immagine = ImageIO.read(new File(immagine));
            imgExist = true;
            paintComponent(this.getGraphics());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!imgExist)GlobalVariables.si = new SelezionaImmagineInterfaccia();
        GlobalVariables.si.getSelected();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
