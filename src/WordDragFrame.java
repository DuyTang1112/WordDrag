

import java.awt.Color;
import java.awt.Image;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JMenuBar;


public class WordDragFrame extends JFrame implements ActionListener {

    private JPanel panel;
    private JMenuBar menu;
    private JMenu options;
    private JMenuItem addWord, exit;
    private List<WordBox> list;
    private String[] s;
    final int CUSTOM_WIDTH=1000;
    final int CUSTOM_HEIGHT=600;
    final int dict_size=354985;

    public WordDragFrame() {
        s= new String[dict_size];
        list= new ArrayList<WordBox>();
        try {
            int i=0;
            Scanner scan=new Scanner(new FileReader("dict"));
            while (scan.hasNextLine()){
                s[i++]=scan.nextLine();
            }
            scan.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordDragFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        panel = new JPanel();
        panel.setLayout(null);
        menu = new JMenuBar();
        setJMenuBar(menu);
        options = new JMenu("Options");
        addWord = new JMenuItem("Add a word");
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        addWord.addActionListener(this);
        options.add(addWord);
        options.add(exit);
        menu.add(options);
        Random r=new Random();
        for (int i=0;i<10;i++){
            list.add(new WordBox(s[r.nextInt(dict_size)], this));
           // System.out.println(s[i]);
            panel.add(list.get(i));
        }
        panel.setBackground(Color.getHSBColor((float)133, (float)0.2, (float)0.5));
        add(panel);
        setTitle("WordDrag 2017- By Duy Tang");
        setSize(CUSTOM_WIDTH, CUSTOM_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==addWord){
            String s=JOptionPane.showInputDialog(WordDragFrame.this, 
                        "Input a word:", "", JOptionPane.INFORMATION_MESSAGE);
            while (s.equals("")) {
                JOptionPane.showMessageDialog(WordDragFrame.this, "Please input something!", "", JOptionPane.WARNING_MESSAGE);
                s=JOptionPane.showInputDialog(WordDragFrame.this, 
                        "Input a word:", "", JOptionPane.INFORMATION_MESSAGE);
            }
            WordBox wb=new WordBox(s,this);
            list.add(wb);
            panel.add(wb);
            panel.repaint();
            
        }
    }
    public static void main(String[] args) {
        new WordDragFrame();
    }

    public List<WordBox> getList() {
        return list;
    }

    public JPanel getPanel() {
        return panel;
    }

    
    
}
