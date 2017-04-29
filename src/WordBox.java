
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;


import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class WordBox extends JLabel {
    
    private String s;
    Point original = new Point();
    private final Color DEFAULT_COLOR = Color.CYAN;
    private final Color CHANGE_COLOR = Color.yellow;
    private WordDragFrame parentFrame;
    
    public WordBox() {
        Random r = new Random();
        setOpaque(true);
        setBackground(DEFAULT_COLOR);
        setText("");
        setHorizontalAlignment(CENTER);
        setBorder(new LineBorder(Color.BLACK));
        //setBounds(r.nextInt(900), r.nextInt(500), s.length() * 10, 20);

    }
    
    public WordBox(String s, WordDragFrame j) {
        parentFrame=j;
        Font font = new Font("SANS_SERIF", Font.ITALIC|Font.BOLD, 12);
        FontMetrics fm=getFontMetrics(font);
        int width=fm.stringWidth(s);
        this.s = s;
        setFont(font);
        Random r = new Random();
        setOpaque(true);
        setBackground(DEFAULT_COLOR);
        setText(s);
        setHorizontalAlignment(CENTER);
        setBorder(new LineBorder(Color.BLACK));
       
        setBounds(r.nextInt(parentFrame.CUSTOM_WIDTH-100), r.nextInt(parentFrame.CUSTOM_HEIGHT-100), width+10, 20);
        
        addMouseListener(new MouseAdapter() {
            
        });
        addMouseMotionListener(new MouseMotionListener() {
            
            @Override
            public void mouseDragged(MouseEvent me) {
                setBackground(CHANGE_COLOR);
                Point p = me.getPoint();
                int newX=getX() + p.x - original.x;
                int newY=getY() + p.y - original.y;
                if (newX<parentFrame.getPanel().getX()||
                       newX>parentFrame.getPanel().getX()+parentFrame.getPanel().getWidth()-getWidth() ) return;
                if (newY<parentFrame.getPanel().getY()||
                       newY>parentFrame.getPanel().getY()+parentFrame.getPanel().getHeight()-getHeight()) return;
                setLocation(getX() + p.x - original.x, getY() + p.y - original.y);
            }
            
            @Override
            public void mouseMoved(MouseEvent me) {
            }
        });
        addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent me) {
                setBackground(CHANGE_COLOR);
                WordBox.this.original.setLocation(me.getPoint());
                if (me.getClickCount() == 2 && !me.isConsumed()) {
                    
                    int i = JOptionPane.showConfirmDialog(null,
                            "Delete this word?", "Delete?", JOptionPane.WARNING_MESSAGE);
                    if (i == 0) {
                        WordBox.this.setVisible(false);
                        WordBox.this.parentFrame.getList().remove(WordBox.this);
                        System.out.println(WordBox.this.parentFrame.getList().size());
                    }
                }
                
            }
            
            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(CHANGE_COLOR);
                WordBox.this.original.setLocation(me.getPoint());
            }
            
            @Override
            public void mouseReleased(MouseEvent me) {
                setBackground(DEFAULT_COLOR);
                WordBox.this.original.setLocation(me.getPoint());
            }
            
            @Override
            public void mouseEntered(MouseEvent me) {
                //setBackground(CHANGE_COLOR);
            }
            
            @Override
            public void mouseExited(MouseEvent me) {
                WordBox.this.original.setLocation(me.getPoint());
                setBackground(DEFAULT_COLOR);
            }
        });
        
    }
    
    public String getS() {
        return s;
    }
    
}
