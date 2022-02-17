/**
*
*
*This class represents a computer version of 
*the board game Checkers ((aka English draughts)
*
*@author Sfiso Mthembu MTHSFI005
*
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Checkers extends JFrame implements ActionListener 
{
   /**
   *Rows --the number of rows on the board of the game
   *
   *Columns --the number of columns on the board of the game 
   *
   *clickedOnce -- a bulean value that turns true when a button
   *              where a piece must be removed is pressed
   *
   *
   *block -- an array of buttons
   *
   *x2 -- the row number of the second button clicked
   *      (the button where a piece must be moved to)
   *
   *
   *y2 -- the column number of the second button clicked
   *      (the button where a piece must be moved to)
   *
   *
   *x1 -- the row number of the first clicked button 
   *      (the button where a piece must be moved to) 
   *
   *
   *y1 -- the column number of the second button clicked
   *      (the button where a piece must be moved to)
   *
   *
   *clickedP1 -- a boolean value that turns true Player 1 just clicked a button
   *
   *clickedP2 -- a boolean value that turns true Player 1 just clicked a button
   *
   *P2KING -- the icon used to crown a player 2 pice
   *
   *P1KING -- the icon used to crown a player 1 piece
   *
   *player1 -- the initial player 1 icon
   *
   *player2 -- the initial player 2 icon
   *
   */
   SmallWindow small = new SmallWindow();
   private int player2Pieces=12;
   private int player1Pieces=12;
   
   private static final int Rows = 8;
   private static final int Columns = 8;
   private boolean clickedOnce=false;
   private JButton[][] block;
   private int x2;
   private int y2;
   private int x1;
   private int y1;
   private boolean clickedP1 = false;
   private boolean clickedP2 = false;
   
   
   
   
   private ImageIcon P2KING = new ImageIcon("P2KING.PNG");
   Image img3 = P2KING.getImage();  
   Image newimg3 = img3.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;  
   ImageIcon king2 = new ImageIcon( newimg3 );


   private  ImageIcon P1KING = new ImageIcon("P1KING.PNG");
   Image p1King = P1KING.getImage();  
   Image newimg0 = p1King.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;  
   ImageIcon king = new ImageIcon( newimg0 );

   
      
   private ImageIcon player1 = new ImageIcon("player1.png");
   Image img = player1.getImage();  
   Image newimg = img.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;  
   ImageIcon icon = new ImageIcon( newimg );

   
   private ImageIcon player2 = new ImageIcon("Player2.png");
   Image img2 = player2.getImage();  
   Image newimg2 = img2.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;  
   ImageIcon icon2 = new ImageIcon( newimg2 );
   

   /**
   *
   *This constructor constructs a 900 by 900 pixels board for the game
   *and also puts in the required pieces for player 1 and player 2
   *
   *
   *
   *
   *
   */
   
   
   public static void main(String[] args)
   {
      Checkers play = new Checkers();
      play.setVisible(true);
   }

   
   
   
   public Checkers()
   {
      super("Checkers");
      setSize(900,900);
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    
      addWindowListener(new WindowActions());
      
      JPanel board = new JPanel();
    
      setLayout(new BorderLayout());
   
      board.setLayout(new GridLayout(8,8));
    
      add(board);
    
      block = new JButton[Rows][Columns]; 
    
      /*
      *
      *A definite loop that loops throught the rows and columns
      *of the 2D array "block" and adds buttons with their
      *colors ,actionListener and actionCommand.
      *
      *The color , action command and actionListener  
      *are added if the row and are either both even or both not even.
      *
      */
      for (int row=0;row < Rows; row++)
      {
         for (int col=0;col < Columns; col++)
            {
               block[row][col] =  new JButton();
               board.add(block[row][col]);       
         
            if ((row%2==0))
               {  
                  if (col%2==0)
                  {
               
                     block[row][col].setBackground(Color.GREEN);
                     block[row][col].setActionCommand(row+" "+col);
                     block[row][col].addActionListener(this);
                  }
            
                           
               }
            
       else if ((col%2!=0))
                  {  
                     if (row%2!=0)
                        {
                           block[row][col].setBackground(Color.GREEN);               
                           block[row][col].setActionCommand(row+" "+col);
                           block[row][col].addActionListener(this);
                        }
                  }
         
         }
      }   
      
      
      
      
      
      /**
      Sets colour to the useless buttons
      */
      
      
      for (int row=0;row < Rows; row++)
         {
            for (int col=0;col < Columns; col++)
               {
                  if ((col%2!=0)) 
                     {  
                        if (row%2==0)
                           {
                              block[row][col].setBackground(Color.RED);  
                           }
                     }
             else if ((row%2!=0))
                     {  
                        if (col%2==0)
                           {
                              block[row][col].setBackground(Color.RED);                 
                           }
                     }
               }
         }


      
      
      /**
      *
      *This definite loop sets icons to
      *the player 1 pieces
      *
      */
      
      
      for (int row=0;row < Rows; row++)
      {
         for (int col=0;col < Columns; col++)
            {
               if ((row==3) || (row==4) ||  (row==5)|| (row==6) || (row==7))
                  {
                     continue;
                  }
          else if ((row%2==0)) 
                  {  
                     if (col%2==0)
                        {
                           block[row][col].setIcon(icon);  
                        }
                  }
          else if ((col%2!=0))
                  {  
                     if (row%2!=0)
                        {
                            block[row][col].setIcon(icon);                 
                        }
                  }
            }
      }
               

      
      
      /**
      *
      *This definite loop sets icons to
      *the player 2 pieces
      *
      */     
      
      
      for (int row=0;row < Rows; row++)
      {
         for (int col=0;col < Columns; col++)
            {
               if ((row==0) || (row==1) ||  (row==2)|| (row==3) || (row==4))
                  {
                     continue;
                  }
          else if ((row%2==0)) 
                  {  
                     if (col%2==0)
                       {  
                           block[row][col].setIcon(icon2);  
                       }
                  }
          else if ((col%2!=0))
            {  
               if (row%2!=0)
                  {
                     block[row][col].setIcon(icon2);                 
                  }
            }
          }
      }
   }

    
   /**
   *
   *This method converts a string number to and integer
   *
   *@param stringNumber number in type String
   *@return the number in type int
   *@throws no exception
   *
   */
   
   



   public int convert(String stringNumber)
   {
      return Integer.parseInt(stringNumber);
   }

   
   public class SmallWindow extends JFrame
   
   {
      public SmallWindow()
      {

         
         setTitle("Confirm");
         setSize(300,100);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         getContentPane().setBackground(Color.LIGHT_GRAY);
         setLayout(new BorderLayout());
         
         JPanel buttonPanel = new JPanel();
         buttonPanel.setLayout(new FlowLayout());
         buttonPanel.setBackground(Color.LIGHT_GRAY);
                        
         JLabel sure = new JLabel("Are You Sure You Want To Exit?");
         add(sure,BorderLayout.CENTER);              
         
         JButton cancel = new JButton("Cancel");
         cancel.setBackground(Color.WHITE);
         buttonPanel.add(cancel);
         cancel.addActionListener(new actions());
                        
         JButton restart = new JButton("Restart");
         restart.setBackground(Color.GREEN);
         buttonPanel.add(restart);
         restart.addActionListener(new actions());
               
         JButton exit = new JButton("Exit");
         exit.setBackground(Color.RED);
         buttonPanel.add(exit);
         exit.addActionListener(new actions());
                        
         add(buttonPanel ,BorderLayout.SOUTH);
                        
                       
         addWindowListener(new WindowActions());
         
         
      }
   }
   
   
   
    public class actions implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      
      {
         String word = e.getActionCommand();
         
         if (word.equals("Exit"))
         {
            System.exit(0);
         }
         else if (word.equals("Cancel"))
         
         {
            small.setVisible(false);
            

         }
         
         
         else if (word.equals("Restart"))
         
         {
            Checkers newGame = new Checkers();
            newGame.setVisible(true);
         }
      
      }
   }

   
   
   public class WindowActions implements WindowListener
      {
         public void windowOpened(WindowEvent e){}
         
         public void windowClosing(WindowEvent e)
         
         {  
            //SmallWindow small = new SmallWindow();
            small.setVisible(true);
         }
         
         public void windowClosed(WindowEvent e){}
         
         public void windowIconified(WindowEvent e){}
         
         public void windowDeiconified(WindowEvent e){}
         
         public void windowActivated(WindowEvent e){}
         
         public void windowDeactivated(WindowEvent e){}
      }




/**
*
*This method handles events from all buttons
*and it is overriden from the interface Actionlistener
*
*
*@param e The action event from a pressed button 
*
*@throws Noo exception
*
*
*/


      public void actionPerformed(ActionEvent e)
      {
    
      
      
      
      if (!clickedOnce)
      {
         
         String pair = e.getActionCommand();
         String[] pairXY = pair.split(" ");
         x1 = convert(pairXY[0]);
         y1 = convert(pairXY[1]);
         System.out.println("this is one: y:"+y1+" x:"+x1);
         clickedOnce = true;
      }  
         
      else
      {
         String pair2 = e.getActionCommand();
         String[] pairXY2 = pair2.split(" ");
         x2 = convert(pairXY2[0]);
         y2 = convert(pairXY2[1]);
         System.out.println("this is two: y:"+y2+" x:"+x2);
         clickedOnce = false;
      
         
                 
         /**
         *
         *The code that handles how the player one pieces move
         *with regards to which buttons are clicked on the board
         *
         *
         *
         *
         **/   
         
         if (block[x1][y1].getIcon()==icon && block[x2][y2].getIcon()==null && (clickedP1 == false))
            {
                           
            
               if((x2==x1+1) && (y2==y1+1))
                  {
                     block[x2][y2].setIcon(block[x1][y1].getIcon());
                     block[x1][y1].setIcon(null);
                     clickedP1=true;
                     clickedP2=false;
               
                  }  
               
          else if((x2==x1+1) && (y2==y1-1))
                 {
                    block[x2][y2].setIcon(block[x1][y1].getIcon());
                    block[x1][y1].setIcon(null);
                    clickedP1=true;
                    clickedP2=false;
               
                 }  
               
          else if(((x2==x1+2) && (y2==y1+2)) && ((block[x1+1][y1+1].getIcon()==icon2) || (block[x1+1][y1+1].getIcon()==king2)))
                 {
                     block[x2][y2].setIcon(block[x1][y1].getIcon());
                     block[x1][y1].setIcon(null);
                     block[x1+1][y1+1].setIcon(null);
                     clickedP1=true;
                     clickedP2=false;
                     player2Pieces--;   
                 }
               
          else if (((x2==x1+2) && (y2==y1-2)) && ((block[x1+1][y1-1].getIcon()==icon2) ||  (block[x1+1][y1-1].getIcon()==king2)))
                 {
                    block[x2][y2].setIcon(block[x1][y1].getIcon());
                    block[x1][y1].setIcon(null);
                    block[x1+1][y1-1].setIcon(null);
                    clickedP1=true;
                    clickedP2=false;
                    player2Pieces-- ;
                 }
            }
         
                  
                  
                 
         /**
         *
         *The code that handles how the player two pieces move
         *with regards to which buttons are clicked on the board
         *
         *
         *
         *
         **/       
         
         else if (block[x1][y1].getIcon()==icon2 && block[x2][y2].getIcon()==null && (clickedP2 == false))
            {
               if((x2==x1-1) && (y2==y1+1))
                  {
                     block[x2][y2].setIcon(block[x1][y1].getIcon());
                     block[x1][y1].setIcon(null);
                     clickedP2=true;
                     clickedP1= false;
                  }  
           else if((x2==x1-1) && (y2==y1-1))
                   {
                      block[x2][y2].setIcon(block[x1][y1].getIcon());
                      block[x1][y1].setIcon(null);
                      clickedP2=true;
                      clickedP1= false;
                   }  
           else if(((x2==x1-2) && (y2==y1+2)) && ((block[x1-1][y1+1].getIcon()==icon) || (block[x1-1][y1+1].getIcon()==king)))
                   {
                      block[x2][y2].setIcon(block[x1][y1].getIcon());
                      block[x1][y1].setIcon(null);
                      block[x1-1][y1+1].setIcon(null);
                      clickedP2=true;
                      clickedP1= false;
                      player1Pieces--;         
                   }   
           else if (((x2==x1-2) && (y2==y1-2)) && ((block[x1-1][y1-1].getIcon()==icon) || (block[x1-1][y1-1].getIcon()==king)))
                     {
                        block[x2][y2].setIcon(block[x1][y1].getIcon());
                        block[x1][y1].setIcon(null);
                        block[x1-1][y1-1].setIcon(null);
                        clickedP2=true;
                        clickedP1= false;
                        player1Pieces--;
                     }
            }
      
         /**
         *The following code Declare a piece as king if it reaches the opposite last row
         *
 
         *
         *
         **/
         
         
         
         for (int i=0;i<8;i++)
         {
            if(block[0][i].getIcon()==icon2)
               {
                  block[0][i].setIcon(king2); 
               }
            
      else if(block[7][i].getIcon()==icon)
               {
                  block[7][i].setIcon(king);
               }
         }
      
      
         /**
         *
         *The following test statements control how a piece moves
         *after it has been crowned "king"
         *
         *
         */         
         
         
         
         /**
         For the king pieces of player 1
         */
         
         if((block[x1][y1].getIcon()==king && (block[x2][y2].getIcon()==null)) && clickedP1 == false)
         {
                  /**
                  *How Player king 1 pieces go down 
                  */ 
            
            if((x2==x1+1) && (y2==y1+1))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               clickedP1=true;
               clickedP2=false;
            }
                  
            else if((x2==x1+1) && (y2==y1-1))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               clickedP1=true;
               clickedP2=false;
            }
                  
            else if(((x2==x1+2) && (y2==y1+2)) && ((block[x1+1][y1+1].getIcon()==king2) || (block[x1+1][y1+1].getIcon()==icon2)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1+1][y1+1].setIcon(null);
               clickedP1=true;
               clickedP2=false;
               player2Pieces--;
            }
                  
            else if (((x2==x1+2) && (y2==y1-2)) && ((block[x1+1][y1-1].getIcon()==king2) || (block[x1+1][y1-1].getIcon()==icon2)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1+1][y1-1].setIcon(null);
               clickedP1=true;
               clickedP2=false;
               player2Pieces--;
            }
            
            /**
            *How Player 1 king pieces go up
            */
            else if((x2==x1-1) && (y2==y1+1))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               clickedP1=true;
               clickedP2=false;
              
            }
            else if((x2==x1-1) && (y2==y1-1))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               clickedP1=true;
               clickedP2=false;
               
            }
            else if(((x2==x1-2) && (y2==y1+2)) && ((block[x1-1][y1+1].getIcon()==king2) || (block[x1-1][y1+1].getIcon()==icon2)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1-1][y1+1].setIcon(null);
               clickedP1=true;
               clickedP2=false;
               player2Pieces--;
            }
            else if (((x2==x1-2) && (y2==y1-2)) && ((block[x1-1][y1-1].getIcon()==king2) || (block[x1-1][y1-1].getIcon()==icon2)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1-1][y1-1].setIcon(null);
               clickedP1=true;
               clickedP2=false;
               player2Pieces--;
            }
         }
              
                  
         /**
         For the king pieces of player 2
         */        
         
         else if (block[x1][y1].getIcon()==king2 && block[x2][y2].getIcon()==null && (clickedP2 == false))
         {
                   /**
                   *How player 2 King pieces go down
                   */
            
            
            if((x2==x1+1) && (y2==y1+1))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               clickedP1=false;
               clickedP2=true;
               
            }
                  
            else if((x2==x1+1) && (y2==y1-1))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               clickedP1=false;
               clickedP2=true;
              
            }
                  
            else if(((x2==x1+2) && (y2==y1+2)) && ((block[x1+1][y1+1].getIcon()==king) || (block[x1+1][y1+1].getIcon()==icon)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1+1][y1+1].setIcon(null);
               clickedP1=false;
               clickedP2=true;
               player1Pieces--;
            }
                  
            else if(((x2==x1+2) && (y2==y1-2)) && ((block[x1+1][y1-1].getIcon()==king) || (block[x1+1][y1-1].getIcon()==icon)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1+1][y1-1].setIcon(null);
               clickedP1=false;
               clickedP2=true;
               player1Pieces--;
            }
                 
               
             /**
               *How player 2 king pieces go 2 up
               */
            else if((x2==x1-1) && (y2==y1+1))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               clickedP1=false;
               clickedP2=true;
            }
            else if((x2==x1-1) && (y2==y1-1))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               clickedP1=false;
               clickedP2=true;
            }
            else if(((x2==x1-2) && (y2==y1+2)) && ((block[x1-1][y1+1].getIcon()==king) || (block[x1-1][y1+1].getIcon()==icon)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1-1][y1+1].setIcon(null);
               clickedP1=false;
               clickedP2=true;
               player1Pieces--;
            }
            else if (((x2==x1-2) && (y2==y1-2)) && ((block[x1-1][y1-1].getIcon()==king) || (block[x1-1][y1-1].getIcon()==icon)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1-1][y1-1].setIcon(null);
               clickedP1=false;
               clickedP2=true;
               player1Pieces--;
            }
         }   

      }
   if(player1Pieces==0 || player2Pieces==0)
      {
         SmallWindow win = new SmallWindow();
         win.setVisible(true);
      }
   
   
   }
}