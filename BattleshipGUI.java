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
            buttonholder.setEnabled(false);
            //if() add if there is an object here and then change the button color?
        }
    }
    public String getPosition(){
        return row+"|"+column;
    }



}
public class BattleshipGUI extends JFrame{
    JFrame frame=new JFrame();
    JPanel board= new JPanel();
    JLabel label=new JLabel();
    JPanel instructions=new JPanel();
    JLabel feedback=new JLabel();
    JButton[][] buttons=new JButton[10][10];
    public BattleshipGUI(){
        initializeBoard();
        miniMap_initialize();
    }
    public void initializeBoard() {
        frame.setTitle("BattleShips!");
        frame.setSize(1000, 1000);
        instructions.setLayout(new BorderLayout());
        feedback.setText("Get Ready to Start");
        feedback.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        feedback.setBackground(Color.decode("#5DADE2"));
        feedback.setHorizontalAlignment(JLabel.CENTER);
        frame.add(feedback,BorderLayout.SOUTH);
        label.setText("Welcome to BattleShips! Place Your Ship!");
        label.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBackground(Color.decode("#5DADE2"));
        instructions.add(label);
        instructions.setSize(250,250);
        frame.add(instructions,BorderLayout.NORTH);
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
    public void miss(){
        //if(){
        feedback.setText("MISS!");

    }
    public void hit(){
       /// if()
        feedback.setText("HIT!");
    }
   /* public void turn(){
        if(){
           label.setText("Your Turn");
        }
        else{
            label.setText("AI Turn");
        }

    } */
    JFrame frame_mini=new JFrame();
    JPanel board_mini=new JPanel();
    JButton[][] buttons_mini=new JButton[10][10];
    public void miniMap_initialize(){
        frame_mini.setTitle("Enemy Board");
        frame_mini.setSize(250, 250);
        frame_mini.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_mini.add(board_mini);
        board_mini.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons_mini[i][j] = new JButton();
                buttons_mini[i][j].setOpaque(true);
                buttons_mini[i][j].setBackground(Color.BLUE);
                buttons_mini[i][j].setEnabled(false);
                board_mini.add(buttons_mini[i][j]);
                frame_mini.setVisible(true);

            }
        }
    }

}
