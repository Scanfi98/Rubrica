package grafica;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Created by Giovanni on 30/11/2015.
 */
public class SelezionaImmagineInterfaccia extends JFileChooser {

    private  JButton b;

    public SelezionaImmagineInterfaccia() {
        init();
    }

    private void init() {
        b = new JButton();
        this.setDialogTitle("Scegli il file da compilare");
        this.setVisible(true);
        this.setCurrentDirectory(new File("C:\\Users\\Giovanni\\AppData\\"));
        this.setPreferredSize(new Dimension(700, 500));
        this.setFileFilter(new FileNameExtensionFilter("Immagine (.png)","png"));
        this.setFileFilter(new FileNameExtensionFilter("Immagine (.jpg)","jpg"));
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    public String getSelected(){
        if(this.showOpenDialog(b) == JFileChooser.APPROVE_OPTION){
            return this.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }



}
