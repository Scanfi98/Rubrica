/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Giovanni
 */
public abstract class AltKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e){
        
    }

    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);
    
}
