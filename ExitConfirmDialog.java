import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
*A small dialog asking the player to confirm exiting the game,
*with the option to restart instead. Shown when the main game
*window is closed.
*
*@author Sfiso Mthembu MTHSFI005
*/
public class ExitConfirmDialog extends JFrame implements ActionListener
{
   private Checkers owner;

   public ExitConfirmDialog(Checkers owner)
   {
      this.owner = owner;

      setTitle("Confirm");
      setSize(300,100);
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      getContentPane().setBackground(Color.LIGHT_GRAY);
      setLayout(new BorderLayout());

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout());
      buttonPanel.setBackground(Color.LIGHT_GRAY);

      JLabel sure = new JLabel("Are You Sure You Want To Exit?");
      add(sure, BorderLayout.CENTER);

      JButton cancel = new JButton("Cancel");
      cancel.setBackground(Color.WHITE);
      buttonPanel.add(cancel);
      cancel.addActionListener(this);

      JButton restart = new JButton("Restart");
      restart.setBackground(Color.GREEN);
      buttonPanel.add(restart);
      restart.addActionListener(this);

      JButton exit = new JButton("Exit");
      exit.setBackground(Color.RED);
      buttonPanel.add(exit);
      exit.addActionListener(this);

      add(buttonPanel, BorderLayout.SOUTH);

      addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
         {
            setVisible(false);
         }
      });
   }

   public void actionPerformed(ActionEvent e)
   {
      String word = e.getActionCommand();

      if (word.equals("Exit"))
      {
         System.exit(0);
      }
      else if (word.equals("Cancel"))
      {
         setVisible(false);
      }
      else if (word.equals("Restart"))
      {
         setVisible(false);
         owner.dispose();
         Checkers newGame = new Checkers();
         newGame.setVisible(true);
      }
   }
}
