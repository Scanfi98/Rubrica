/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

import components.JLabelStandard;
import components.JPanelStandard;
import components.JTFieldStandard;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import rubrica.*;

/**
 *
 * @author Giovanni
 */
public class InterfacciaCrea extends JPanel {

    private final Dimension FRAME = new Dimension(500, 255);
    private final Dimension PANELTEXT = new Dimension(480, 160);
    private final Dimension PANELBUTTON = new Dimension(500, 50);
    private final Dimension LABEL = new Dimension(175, 20);
    private final Dimension TEXTFIELD = new Dimension(240, 20);
    private final Dimension BUTTON = new Dimension(100, 40);
    
    private JDialog JD_frame;

    private JPanelStandard JP_text;
    private JPanelStandard JP_button;

    private JLabelStandard JL_nome;
    private JLabelStandard JL_cognome;
    private JLabelStandard JL_numero;
    private JLabelStandard JL_indirizzo;
    private JLabelStandard JL_email;

    private JTFieldStandard JTF_nome;
    private JTFieldStandard JTF_cognome;
    private JTFieldStandard JTF_numero;
    private JTFieldStandard JTF_indirizzo;
    private JTFieldStandard JTF_email;
    private JTFieldStandard JTF_prefisso;

    private JLabel JL_titolo;

    private JButton JB_fatto;
    private JButton JB_esci;

    public InterfacciaCrea() {
        this.initComponents();
    }

    private void initComponents() {

        //INIT
        JD_frame = new JDialog();

        JP_text = new JPanelStandard(PANELTEXT, Color.WHITE);
        JP_button = new JPanelStandard(PANELBUTTON, Color.WHITE);

        JL_nome = new JLabelStandard(LABEL, "Nome: ");
        JL_numero = new JLabelStandard(new Dimension((LABEL.width - 45), LABEL.height), "Numero: ");
        JL_indirizzo = new JLabelStandard(LABEL, "Indirizzo: ");
        JL_email = new JLabelStandard(LABEL, "Email: ");
        JL_cognome = new JLabelStandard(LABEL, "Cognome: ");
        
        JTF_nome = new JTFieldStandard(TEXTFIELD);
        JTF_cognome = new JTFieldStandard(TEXTFIELD);
        JTF_numero = new JTFieldStandard(TEXTFIELD);
        JTF_indirizzo = new JTFieldStandard(TEXTFIELD);
        JTF_email = new JTFieldStandard(TEXTFIELD);
        JTF_prefisso = new JTFieldStandard(new Dimension(40, TEXTFIELD.height),"+");
        
        
        JB_fatto = new JButton("Salva");
        JB_esci = new JButton("Annulla");

        JL_titolo = new JLabel("CREA NUOVO UTENTE ", SwingConstants.CENTER);

        //FRAME
        JD_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JD_frame.setVisible(true);
        JD_frame.setPreferredSize(FRAME);
        JD_frame.setResizable(false);
        JD_frame.setLocationRelativeTo(null);
        JD_frame.setLayout(new BorderLayout());
        JD_frame.setBackground(Color.WHITE);
        JD_frame.pack();

        //TITOLO     
        JL_titolo.setPreferredSize(new Dimension(JD_frame.getWidth(), 50));
        JL_titolo.setAlignmentX(CENTER_ALIGNMENT);
        JL_titolo.setAlignmentY(BOTTOM_ALIGNMENT);
        JL_titolo.setFont(new Font("Arial", Font.BOLD, 30));

        //PULSANTI
        JB_fatto.setSize(BUTTON);
        JB_fatto.setPreferredSize(BUTTON);
        JB_fatto.setMaximumSize(BUTTON);
        JB_fatto.setBackground(Color.white);
        JB_fatto.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (addPersona()) {
                JD_frame.dispose();
            }
            }
        });

        JB_esci.setSize(BUTTON);
        JB_esci.setPreferredSize(BUTTON);
        JB_esci.setMaximumSize(BUTTON);
        JB_esci.setBackground(Color.white);
        JB_esci.addActionListener(new ActionListener( ) {
            

            @Override
            public void actionPerformed(ActionEvent ae) {
                JD_frame.dispose();
            }
        });

        //AGGIUNTA COMPONETI
        JD_frame.add(JL_titolo, BorderLayout.NORTH);
        JD_frame.add(JP_text, BorderLayout.CENTER);
        JD_frame.add(JP_button, BorderLayout.PAGE_END);

        JP_text.add(JL_nome);
        JP_text.add(JTF_nome);

        JP_text.add(JL_cognome);
        JP_text.add(JTF_cognome);
        
        JP_text.add(JL_numero);
        JP_text.add(JTF_prefisso);
        JP_text.add(JTF_numero);

        JP_text.add(JL_indirizzo);
        JP_text.add(JTF_indirizzo);

        JP_text.add(JL_email);
        JP_text.add(JTF_email);

        JP_button.add(JB_fatto, BorderLayout.WEST);
        JP_button.add(JB_esci, BorderLayout.EAST);

        JD_frame.pack();
    }

    private boolean addPersona() {
        String nome = JTF_nome.getText();
        String cognome = JTF_cognome.getText();
        String numero = CreaUtente.removeSpace(JTF_numero.getText());
        String prefix = CreaUtente.removeSpace(JTF_prefisso.getText());
        String indirizzo = JTF_indirizzo.getText();
        String em = JTF_email.getText();
        
        String check = CreaUtente.removeSpace(nome);
        
        if(CreaUtente.removeSpace(prefix).equals("+")){
            prefix = "";
        }
        
        if (check.equals("")) {
            JL_nome.setFont(new Font("Arial", Font.BOLD, 14));
            return false;
        } else {
            CreaUtente c = new CreaUtente();
            c.newContactFile(nome, cognome, prefix, numero, indirizzo, em);

            Rubrica.t.loadList(new Persona(nome, cognome, prefix, numero, indirizzo, em));
            return true;
        }
    }
}
