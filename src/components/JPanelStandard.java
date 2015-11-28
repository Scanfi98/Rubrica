/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Giovanni
 */
public class JPanelStandard extends JPanel {

    public JPanelStandard(Dimension label) {
        this.setPreferredSize(label);
        this.setSize(label);
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }
    
    public JPanelStandard(Dimension label, Color c){
        this.setPreferredSize(label);
        this.setSize(label);
        this.setBackground(c);
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }

    public JPanelStandard() {
    }
    
    
}
