/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Dimension;
import javax.swing.JTextField;

/**
 *
 * @author Giovanni
 */
public class JTFieldStandard extends JTextField {

    public JTFieldStandard(Dimension textfield, String titolo) {
        super(titolo);
        this.setPreferredSize(textfield);
        this.setSize(textfield);
    }

    public JTFieldStandard(String titolo) {
        super(titolo);
    }

    public JTFieldStandard(Dimension textfield) {
        this.setPreferredSize(textfield);
        this.setSize(textfield);
    }

    public JTFieldStandard() {

    }

}
