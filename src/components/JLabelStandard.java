/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author Giovanni
 */
public class JLabelStandard extends JLabel {

    public JLabelStandard(String text) {
        super(text);
    }

    public JLabelStandard(Dimension label) {
        this.setSize(label);
        this.setPreferredSize(label);
    }

    public JLabelStandard(Dimension label, String text) {
        super(text);
        this.setSize(label);
        this.setPreferredSize(label);
    }

    public JLabelStandard() {

    }

}
