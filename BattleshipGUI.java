import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class Button implements ActionListener{
    JButton buttonholder;
    int row,column;

    public Button(JButton button,int row,int column) {
        buttonholder=button;
        this.row=row;
        this.column=column;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonholder){
        }
    }
    public String getPosition(){
        return row+"|"+column;
    }



}
public class BattleshipGUI extends JFrame{
    JFrame frame=new JFrame();
    JPanel board= new JPanel();
    JButton[][] buttons=new JButton[10][10];
    public BattleshipGUI(){
        initializeBoard();
    }
    public void initializeBoard() {
        frame.setTitle("BattleShips!");
        frame.setSize(1000, 1000);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(board);
        board.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setOpaque(true);
                buttons[i][j].setBackground(Color.BLUE);
                board.add(buttons[i][j]);
                frame.setVisible(true);
                buttons[i][j].addActionListener(new Button(buttons[i][j],i,j));

            }
        }
    }


}
