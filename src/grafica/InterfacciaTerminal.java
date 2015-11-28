/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import listener.JTADocumentListener;
import rubrica.GlobalVariables;
import terminal.BackSpaceAction;
import terminal.Commands;
import terminal.ConsoleOutputStream;
import terminal.EnterAction;

/**
 *
 * @author Giovanni
 */
public final class InterfacciaTerminal extends JPanel {

    private final GraphicsDevice DEFAULT_SCREEN_SIZE = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    private final Dimension FRAME_DIM = new Dimension(600, 400);
    private final Dimension CONSOLE_DIM = new Dimension(550, 250);
    
    private final Dimension SCREEN_SIZE = new Dimension(((DEFAULT_SCREEN_SIZE.getDisplayMode().getWidth()) / 2), DEFAULT_SCREEN_SIZE.getDisplayMode().getHeight());

    private JFrame JF_frame;
    private JTextArea JTA_console;
    private JScrollPane JSP_console;
    private JPanel JP_pannello;

    private EnterAction azioneEnter;
    
    private String comando = "";

    public InterfacciaTerminal() {
        initComponent();
        initStream();
    }

    private void initComponent() {

        JF_frame = new JFrame("Console");
        JTA_console = new JTextArea();
        JSP_console = new JScrollPane(JTA_console);
        JP_pannello = new JPanel();
        
        JF_frame.setVisible(true);        
        JF_frame.setSize(FRAME_DIM);
        JF_frame.setPreferredSize(FRAME_DIM);
        JF_frame.setMaximumSize(SCREEN_SIZE);
        JF_frame.setMinimumSize(FRAME_DIM);
        JF_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JF_frame.setLayout(new BorderLayout());

        
        JP_pannello.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JP_pannello.setSize(FRAME_DIM);
        JP_pannello.setPreferredSize(new Dimension(FRAME_DIM.width, (FRAME_DIM.height - 50)));
        JP_pannello.setMaximumSize(SCREEN_SIZE);
        JP_pannello.setMinimumSize(FRAME_DIM);
        JP_pannello.setLayout(new BorderLayout());
        
        azioneEnter = new EnterAction(comando,  JF_frame, JTA_console);
        JTA_console.getActionMap().put(JTA_console.getInputMap().get(KeyStroke.getKeyStroke("ENTER")), azioneEnter);

        JTA_console.setPreferredSize(CONSOLE_DIM);
        JTA_console.setLineWrap(true);
        JTA_console.setMaximumSize(SCREEN_SIZE);
        JTA_console.setFont(new Font("Arial", Font.TRUETYPE_FONT, 14));
        JTA_console.setForeground(Color.WHITE);
        JTA_console.setBackground(Color.BLACK);
        JTA_console.append("RUBRICA@USER## ");

        //http://stackoverflow.com/questions/5943672/automatic-dynamic-expansion-contraction-of-jtextarea-in-java?rq=1
        JSP_console.setViewportView(JTA_console);
	    JSP_console.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JSP_console.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JSP_console.setBackground(Color.WHITE);
        JSP_console.setBorder(new LineBorder(Color.BLACK, 5));
        JSP_console.setPreferredSize(new Dimension(60, JTA_console.getHeight()));
        
        
        JP_pannello.add(JSP_console, BorderLayout.CENTER);
        JF_frame.add(JP_pannello, BorderLayout.CENTER);

        JTA_console.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Commands prova = new Commands(comando);
                    azioneEnter.setComando(comando);         
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && comando.equals("")) {
                    JTA_console.getActionMap().put(JTA_console.getInputMap().get(KeyStroke.getKeyStroke("BACK_SPACE")), new BackSpaceAction(JTA_console, false));

                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && !comando.equals("")) {
                    String c = comando;
                    comando = "";
                    for (int i = 0; i < c.length() - 1; i++) {
                        comando = comando + c.charAt(i);
                    }
                } else {
                    comando = comando + e.getKeyChar();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                    
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {                 
                    JSP_console.validate();
                    comando = "";
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    JTA_console.getActionMap().put(JTA_console.getInputMap().get(KeyStroke.getKeyStroke("BACK_SPACE")), new BackSpaceAction(JTA_console, true));
                }
                
            }
        });
        
		JTA_console.getDocument().addDocumentListener(new JTADocumentListener(JTA_console));
    }

    public void initStream() {
        GlobalVariables.stream_enable = true;
        GlobalVariables.cos = new ConsoleOutputStream(JTA_console);
    }

    public JTextArea getJTA_console() {
        return JTA_console;
    }
}
