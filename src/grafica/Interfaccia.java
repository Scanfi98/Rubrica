/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

/**
 *
 * @author Giovanni
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import listener.AltKeyListener;
import rubrica.*;

public final class Interfaccia extends JPanel {

    //COSTANTI
    private final int LISTA_WITDH = 400;
    private final int LISTA_HIGH = 460;

    private final GraphicsDevice DEFAULT_SCREEN_SIZE = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    private final Dimension FRAME_DIM = new Dimension(700, 500);
    private final Dimension PANEL_LISTA_DIM = new Dimension(LISTA_WITDH, LISTA_HIGH);
    private final Dimension PANEL_BOTTONI_DIM = new Dimension((FRAME_DIM.width - PANEL_LISTA_DIM.width), FRAME_DIM.height);
    private final Dimension BUTTON = new Dimension(300, 400);
    private final Dimension SCREEN_SIZE = new Dimension(DEFAULT_SCREEN_SIZE.getDisplayMode().getWidth(), DEFAULT_SCREEN_SIZE.getDisplayMode().getHeight());

    //VARIABILI
    private JFrame JF_frame;

    private JPanel JP_lista;
    private JPanel JP_opzioni;
    private PannelloImmagine JP_immagine;

    private JList JLS_contatti;
    private JScrollPane JSP_scroll;

    private JLabel JL_titolo;
    private JLabel JL_instruction;
    private JLabel JL_nome;
    private JLabel JL_numero;
    private JLabel JL_indirizzo;
    private JLabel JL_email;

    private JButton JB_crea;
    private JButton JB_modifica;
    private JButton JB_cancella;
    private JButton JB_ricarica;

    private JToggleButton JTB_multi;

    private final HashMap<String, Persona> HM_modello = new HashMap();

    private Box B_frame_box;
    private Box B_list_box;
    private Box B_bottoni_box;
    private Box B_scheda_box;

    public Robot R_r;

    public Interfaccia() {
        this.initComponents();
        loadInitialListValues();
    }

    private void initComponents() {

        //INIT COMPONENTS
        JF_frame = new JFrame("Rubrica");
        JP_lista = new JPanel();
        JP_opzioni = new JPanel();
        JP_immagine = new PannelloImmagine(null);
        JLS_contatti = new JList();
        JSP_scroll = new JScrollPane();
        JL_titolo = new JLabel("RUBRICA", SwingConstants.CENTER);
        JL_instruction = new JLabel("Tieni premuto ALT per vedere la scheda", SwingConstants.CENTER);
        JB_modifica = new JButton("Modifica");
        JB_crea = new JButton("Crea contatto");
        JB_cancella = new JButton("Rimovi");
        JB_ricarica = new JButton("Ricarica lista");
        JTB_multi = new JToggleButton("Selezione Multipla");

        //INIT BOX LAYOUT
        B_frame_box = Box.createHorizontalBox();
        B_list_box = Box.createHorizontalBox();
        B_bottoni_box = Box.createVerticalBox();
        B_scheda_box = Box.createVerticalBox();

        //FRAME
        JF_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF_frame.setVisible(true);
        JF_frame.setPreferredSize(FRAME_DIM);
        JF_frame.setResizable(false);
        JF_frame.setLocationRelativeTo(null);
        JF_frame.setMaximumSize(SCREEN_SIZE);
        JF_frame.setLayout(new BorderLayout());
        JF_frame.pack();

        //BOTTONI
        JB_modifica.setSize(BUTTON);
        JB_crea.setSize(BUTTON);
        JB_cancella.setSize(BUTTON);
        JTB_multi.setSize(BUTTON);
        JB_ricarica.setSize(BUTTON);

        JB_modifica.setMaximumSize(BUTTON);
        JB_crea.setMaximumSize(BUTTON);
        JB_cancella.setMaximumSize(BUTTON);
        JTB_multi.setMaximumSize(BUTTON);
        JB_ricarica.setMaximumSize(BUTTON);

        JB_modifica.setBackground(Color.WHITE);
        JB_crea.setBackground(Color.WHITE);
        JB_cancella.setBackground(Color.WHITE);
        JTB_multi.setBackground(Color.WHITE);
        JB_ricarica.setBackground(Color.WHITE);

        //TITOLO
        JL_titolo.setPreferredSize(new Dimension(JF_frame.getWidth(), 40));
        JL_titolo.setFont(new Font("Arial", Font.BOLD, 30));

        //LABEL
        JL_instruction.setPreferredSize(new Dimension(BUTTON.width, 30));
        JL_instruction.setMaximumSize(new Dimension(BUTTON.width, 30));
        JL_instruction.setVisible(false);

        JL_nome = new JLabel("", SwingConstants.CENTER);
        JL_nome.setPreferredSize(new Dimension(BUTTON.width, 30));
        JL_numero = new JLabel("", SwingConstants.CENTER);
        JL_numero.setPreferredSize(new Dimension(BUTTON.width, 30));
        JL_indirizzo = new JLabel("", SwingConstants.CENTER);
        JL_indirizzo.setPreferredSize(new Dimension(BUTTON.width, 30));
        JL_email = new JLabel("", SwingConstants.CENTER);

        //PANNELLI
        JP_lista.setVisible(true);
        JP_lista.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JP_lista.setLayout(new BorderLayout());
        JP_lista.setPreferredSize(PANEL_LISTA_DIM);
        JP_lista.setMaximumSize(new Dimension(LISTA_WITDH, JF_frame.getMaximumSize().height));
        JP_lista.setBorder(new EmptyBorder(20, 20, 20, 20));

        JP_opzioni.setVisible(true);
        JP_opzioni.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JP_opzioni.setPreferredSize(PANEL_BOTTONI_DIM);
        JP_opzioni.setMaximumSize(new Dimension((JF_frame.getMaximumSize().width - LISTA_WITDH), JF_frame.getMaximumSize().height));
        JP_opzioni.setLayout(new BorderLayout());
        JP_opzioni.setBorder(new EmptyBorder(20, 0, 0, 20));

        //LISTA
        JLS_contatti.setListData(HM_modello.keySet().toArray());
        JLS_contatti.setLayoutOrientation(JList.VERTICAL);
        JLS_contatti.setFixedCellHeight(25);
        JLS_contatti.setFixedCellWidth(LISTA_WITDH);
        JLS_contatti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //SCROLL BAR
        JSP_scroll.setViewportView(JLS_contatti);
        JSP_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JSP_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JSP_scroll.setBackground(Color.WHITE);

        //ADDING PARTS
        JF_frame.add(JL_titolo, BorderLayout.NORTH);
        JF_frame.add(B_frame_box, BorderLayout.CENTER);

        B_frame_box.add(JP_lista);
        B_frame_box.add(JP_opzioni);

        JP_lista.add(B_list_box);

        B_list_box.add(JSP_scroll);

        JP_opzioni.add(B_bottoni_box, BorderLayout.CENTER);

        B_bottoni_box.add(JB_crea);
        B_bottoni_box.add(Box.createVerticalStrut(20));
        B_bottoni_box.add(JB_modifica);
        B_bottoni_box.add(Box.createVerticalStrut(20));
        B_bottoni_box.add(JB_cancella);
        B_bottoni_box.add(Box.createVerticalStrut(20));
        B_bottoni_box.add(JB_ricarica);
        B_bottoni_box.add(Box.createVerticalStrut(20));
        B_bottoni_box.add(JTB_multi);
        B_bottoni_box.add(Box.createVerticalStrut(20));
        B_bottoni_box.add(JL_instruction);
        B_bottoni_box.add(Box.createVerticalGlue());

        B_scheda_box.add(Box.createVerticalBox());
        B_scheda_box.add(JP_immagine);
        B_scheda_box.add(Box.createGlue());
        B_scheda_box.add(Box.createVerticalStrut(20));
        B_scheda_box.add(JL_nome);
        B_scheda_box.add(Box.createVerticalStrut(20));
        B_scheda_box.add(JL_numero);
        B_scheda_box.add(Box.createVerticalStrut(20));
        B_scheda_box.add(JL_indirizzo);
        B_scheda_box.add(Box.createVerticalStrut(20));
        B_scheda_box.add(JL_email);
        B_scheda_box.add(Box.createVerticalStrut(20));

        //LISTNERS
        JF_frame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("Starting program");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (JTB_multi.isSelected()) {
                    R_r.keyRelease(KeyEvent.VK_CONTROL);
                    System.out.println("Robot disable");
                    System.out.println("Ended program");
                } else {
                    System.out.println("Ended program");
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Ended program");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                if (JTB_multi.isSelected()) {
                    R_r.keyRelease(KeyEvent.VK_CONTROL);
                    if (GlobalVariables.stream_enable) {
                        GlobalVariables.cos.write("Robot disable");
                    }
                    System.out.println("Robot disable");
                }
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                if (JTB_multi.isSelected()) {
                    R_r.keyPress(KeyEvent.VK_CONTROL);
                    if (GlobalVariables.stream_enable) {
                        GlobalVariables.cos.write("Robot enable");
                    }
                    System.out.println("Robot enable");
                }

            }

            @Override
            public void windowActivated(WindowEvent e) {
                if (JTB_multi.isSelected()) {
                    R_r.keyPress(KeyEvent.VK_CONTROL);
                    if (GlobalVariables.stream_enable) {
                        GlobalVariables.cos.write("Robot enable");
                    }
                    System.out.println("Robot enable");
                }
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                if (JTB_multi.isSelected()) {
                    R_r.keyRelease(KeyEvent.VK_CONTROL);
                    if (GlobalVariables.stream_enable) {
                        GlobalVariables.cos.write("Robot disable");
                    }
                    System.out.println("Robot disable");
                }
            }
        });

        //Key Listener cmd
        JF_frame.addKeyListener(new AltKeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_L) {
                    if (e.isShiftDown()) {
                        GlobalVariables.terminale = new InterfacciaTerminal();
                    }
                }
            }

        });

        //Alterna il pannello con il tasto ALT
        JLS_contatti.addKeyListener(new AltKeyListener() {
            int i = 0;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ALT && !JLS_contatti.isSelectionEmpty()) {
                    loadFieldValue();
                    if (i == 0) {
                        if (GlobalVariables.stream_enable) {
                            GlobalVariables.cos.write("Showing information for " + JLS_contatti.getSelectedValue());
                        }
                        System.out.println("Showing information for " + JLS_contatti.getSelectedValue());
                        JP_opzioni.remove(B_bottoni_box);
                        JP_opzioni.add(B_scheda_box);
                        JF_frame.revalidate();
                    }
                    i++;

                } else {
                    if (GlobalVariables.stream_enable) {
                        GlobalVariables.cos.write("Must select an element");
                    }
                    System.err.println("Must select an element");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_L) {
                    if (e.isShiftDown()) {
                        GlobalVariables.terminale = new InterfacciaTerminal();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_ALT && !JLS_contatti.isSelectionEmpty()) {
                    i = 0;
                    JP_opzioni.remove(B_scheda_box);
                    JP_opzioni.add(B_bottoni_box);
                    JF_frame.revalidate();
                }
            }

        });

        JB_crea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GlobalVariables.crea = new InterfacciaCrea();
            }
        });

        //Utilizzo del comando modifica
        JB_modifica.addActionListener((new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GlobalVariables.modifica = new InterfacciaModifica(HM_modello.get((String) JLS_contatti.getSelectedValue()));
                } catch (NullPointerException ex) {
                    if (GlobalVariables.stream_enable) {
                        GlobalVariables.cos.write("Must select an element");
                    }
                    System.err.println("Must select an element");
                }
            }
        }));

        //Ricarica la lista
        JB_ricarica.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalVariables.carica.reloadList();
                if (GlobalVariables.stream_enable) {
                    GlobalVariables.cos.write("Reloaded List");
                }
                System.out.println("Reloaded List");
            }
        }));

        //Cancella i/il valori/e selezionati/o
        JB_cancella.addActionListener((new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Rimuovi r;
                if (!JTB_multi.isSelected() && !JLS_contatti.isSelectionEmpty()) {
                    r = new Rimuovi((String) JLS_contatti.getSelectedValue());
                    if (GlobalVariables.stream_enable) {
                        GlobalVariables.cos.write(JLS_contatti.getSelectedValue() + " deleted");
                    }
                    System.out.println(JLS_contatti.getSelectedValue() + " deleted");
                    r.rimuoviNome();
                    GlobalVariables.carica.reloadList();

                } else {
                    int[] values = JLS_contatti.getSelectedIndices();
                    ArrayList<String> selezionati = new ArrayList();
                    for (int i = 0; i < values.length; i++) {
                        JLS_contatti.setSelectedIndex(i);
                        selezionati.add((String) JLS_contatti.getSelectedValue());
                    }
                    r = new Rimuovi(selezionati);

                    Iterator it = selezionati.iterator();

                    while (it.hasNext()) {
                        if (GlobalVariables.stream_enable) {
                            GlobalVariables.cos.write(it.next() + " deleted");
                        }
                        System.out.println(it.next() + " deleted");
                    }

                    r.rimuoviLista();
                    GlobalVariables.carica.reloadList();
                }
            }
        }));

        //Gestisce la selezione multipla
        JTB_multi.addActionListener((new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    R_r = new Robot();

                    if (JTB_multi.isSelected()) {
                        if (GlobalVariables.stream_enable) {
                            System.out.println("Multiple selection enable");
                        }
                        System.out.println("Multiple selection enable");
                        JB_modifica.setVisible(false);
                        JB_crea.setVisible(false);
                        JB_ricarica.setVisible(false);
                        JLS_contatti.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                        R_r.keyPress(KeyEvent.VK_CONTROL);
                        if (GlobalVariables.stream_enable) {
                            GlobalVariables.cos.write("Robot enable");
                        }
                        System.out.println("Robot enable");

                    } else if (!JTB_multi.isSelected()) {
                        if (GlobalVariables.stream_enable) {
                            GlobalVariables.cos.write("Multiple selection disable");
                        }
                        System.out.println("Multiple selection disable");
                        R_r.keyRelease(KeyEvent.VK_CONTROL);
                        if (GlobalVariables.stream_enable) {
                            GlobalVariables.cos.write("Robot disable");
                        }
                        System.out.println("Robot disable");
                        JLS_contatti.clearSelection();
                        JLS_contatti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        JB_modifica.setVisible(true);
                        JB_crea.setVisible(true);
                        JB_ricarica.setVisible(true);
                    }
                } catch (AWTException ex) {
                    if (GlobalVariables.stream_enable) {
                            GlobalVariables.cos.write("Fail on robot creation");
                        }
                    System.err.println("Fail on robot creation");
                }
            }
        }));

        JLS_contatti.addListSelectionListener((new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                Persona p = HM_modello.get(JLS_contatti.getSelectedValue());
                if(p.isImgPath()){
                    JP_immagine.setImmagine(p.getImg());
                } else {

                }
                if (!JLS_contatti.isSelectionEmpty() && !JTB_multi.isSelected()) {
                    JL_instruction.setVisible(true);
                } else if (!JLS_contatti.isSelectionEmpty() && JL_instruction.isVisible() && !JTB_multi.isSelected()) {
                    JLS_contatti.clearSelection();
                    JL_instruction.setVisible(false);
                }

            }
        }));
    }

    /*---------------------------AGGIUNTA VALORI------------------------------*/
    public void addValueToList(Persona p) {
        HM_modello.put((p.getNome() + " " + p.getCognome()), p);
        GlobalVariables.carica.add(p);
        JLS_contatti.setListData(HM_modello.keySet().toArray());

    }//FUNZIONA

    public void loadInitialListValues() {
        HM_modello.clear();
        Persona p[];
        p = GlobalVariables.carica.getArrayFromList();
        if (p != null) {
            for (Persona temp : p) {
                HM_modello.put((temp.getNome() + " " + temp.getCognome()), temp);
            }
        }
        JLS_contatti.setListData(HM_modello.keySet().toArray());
    }//FUNZIONA

    private void loadFieldValue() {
        if (!JLS_contatti.isSelectionEmpty()) {
            Persona p = HM_modello.get((String) JLS_contatti.getSelectedValue());
            JL_nome.setText(p.getNome() + " " + p.getCognome());
            JL_numero.setText(p.getPrefisso() + " " + p.getN_phone());
            JL_indirizzo.setText(p.getIndirizzo());
            JL_email.setText(p.getEmail());
        }
    }//FUNZIONA

    public String selectedItem() {
        return ((String) JLS_contatti.getSelectedValue());
    }

    public JList getJLS_contatti() {
        return JLS_contatti;
    }

    public HashMap<String, Persona> getMap() {
        return HM_modello;
    }

}
