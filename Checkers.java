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
   ExitConfirmDialog exitDialog = new ExitConfirmDialog(this);
   private int player2Pieces=12;
   private int player1Pieces=12;
   private int player1Score=0;
   private int player2Score=0;
   private JLabel scoreLabel1;
   private JLabel scoreLabel2;
   private JLabel turnLabel;

   private static final Color PLAYABLE_SQUARE = new Color(0x5C3A21);
   private static final Color NON_PLAYABLE_SQUARE = new Color(0xEEDFB6);
   private static final Color SELECTED_SQUARE = new Color(0xF4D03F);
   private static final Color DEST_SQUARE = new Color(0x82E0AA);
   private static final Color PLAYER1_COLOR = new Color(0x1B4F72);
   private static final Color PLAYER2_COLOR = new Color(0xB03A2E);

   private static final int Rows = 8;
   private static final int Columns = 8;
   private boolean clickedOnce=false;
   private JButton[][] block;
   private int x2;
   private int y2;
   private int x1;
   private int y1;
   private boolean clickedP1 = false;
   private boolean clickedP2 = true;
   private java.util.List<int[]> highlightedDestinations = new java.util.ArrayList<int[]>();
   
   
   
   
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

   
   private ImageIcon player2 = new ImageIcon("player2.png");
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
      SwingUtilities.invokeLater(new Runnable()
      {
         public void run()
         {
            try
            {
               UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
            catch (Exception ex) {}
            Checkers play = new Checkers();
            play.setVisible(true);
         }
      });
   }

   
   
   
   public Checkers()
   {
      super("Checkers");
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      setResizable(false);

      addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
         {
            exitDialog.setVisible(true);
         }
      });

      JPanel board = new JPanel();

      setLayout(new BorderLayout());

      board.setLayout(new GridLayout(8,8));
      board.setPreferredSize(new Dimension(800,800));

      add(board, BorderLayout.CENTER);

      JPanel statusPanel = new JPanel(new GridLayout(1,3));
      statusPanel.setBackground(Color.DARK_GRAY);
      Font statusFont = new Font("SansSerif", Font.BOLD, 18);

      scoreLabel1 = new JLabel("Player 1 score: 0", SwingConstants.CENTER);
      scoreLabel1.setFont(statusFont);
      scoreLabel1.setForeground(PLAYER1_COLOR);
      scoreLabel1.setOpaque(true);
      scoreLabel1.setBackground(Color.DARK_GRAY);

      turnLabel = new JLabel("Player 1's turn", SwingConstants.CENTER);
      turnLabel.setFont(statusFont);
      turnLabel.setForeground(Color.WHITE);
      turnLabel.setOpaque(true);
      turnLabel.setBackground(Color.DARK_GRAY);

      scoreLabel2 = new JLabel("Player 2 score: 0", SwingConstants.CENTER);
      scoreLabel2.setFont(statusFont);
      scoreLabel2.setForeground(PLAYER2_COLOR);
      scoreLabel2.setOpaque(true);
      scoreLabel2.setBackground(Color.DARK_GRAY);

      statusPanel.add(scoreLabel1);
      statusPanel.add(turnLabel);
      statusPanel.add(scoreLabel2);
      add(statusPanel, BorderLayout.NORTH);

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
               block[row][col].setOpaque(true);
               block[row][col].setBorderPainted(false);
               block[row][col].setFocusPainted(false);
               board.add(block[row][col]);
         
            if ((row%2==0))
               {  
                  if (col%2==0)
                  {
               
                     block[row][col].setBackground(PLAYABLE_SQUARE);
                     block[row][col].setActionCommand(row+" "+col);
                     block[row][col].addActionListener(this);
                  }
            
                           
               }
            
       else if ((col%2!=0))
                  {  
                     if (row%2!=0)
                        {
                           block[row][col].setBackground(PLAYABLE_SQUARE);               
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
                              block[row][col].setBackground(NON_PLAYABLE_SQUARE);  
                           }
                     }
             else if ((row%2!=0))
                     {  
                        if (col%2==0)
                           {
                              block[row][col].setBackground(NON_PLAYABLE_SQUARE);                 
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

      pack();
      setLocationRelativeTo(null);
   }

   /**
   *Refreshes the on-screen score labels to match
   *player1Score and player2Score.
   */
   public void updateScoreLabels()
   {
      scoreLabel1.setText("Player 1 score: "+player1Score);
      scoreLabel2.setText("Player 2 score: "+player2Score);
   }

   /**
   *Refreshes the turn indicator to reflect whose move it currently is.
   */
   public void updateTurnLabel()
   {
      turnLabel.setText(!clickedP1 ? "Player 1's turn" : "Player 2's turn");
   }

   private boolean inBounds(int row, int col)
   {
      return row>=0 && row<Rows && col>=0 && col<Columns;
   }

   /**
   *Computes the legal destination squares for the piece at (row,col),
   *for the purpose of highlighting them on selection. Mirrors the move
   *rules applied in actionPerformed but does not enforce whose turn it is.
   */
   private java.util.List<int[]> legalDestinations(int row, int col)
   {
      java.util.List<int[]> moves = new java.util.ArrayList<int[]>();
      Icon pieceIcon = block[row][col].getIcon();
      boolean isP1 = (pieceIcon==icon || pieceIcon==king);
      boolean isP2 = (pieceIcon==icon2 || pieceIcon==king2);
      if (!isP1 && !isP2)
      {
         return moves;
      }
      boolean isKingPiece = (pieceIcon==king || pieceIcon==king2);

      int[][] dirs;
      if (isKingPiece)
      {
         dirs = new int[][]{{-1,-1},{-1,1},{1,-1},{1,1}};
      }
      else if (isP1)
      {
         dirs = new int[][]{{1,-1},{1,1}};
      }
      else
      {
         dirs = new int[][]{{-1,-1},{-1,1}};
      }

      for (int[] d : dirs)
      {
         int nr = row+d[0];
         int nc = col+d[1];
         if (inBounds(nr,nc) && block[nr][nc].getIcon()==null)
         {
            moves.add(new int[]{nr,nc});
         }

         int jr = row+2*d[0];
         int jc = col+2*d[1];
         if (inBounds(jr,jc) && block[jr][jc].getIcon()==null)
         {
            Icon midIcon = block[nr][nc].getIcon();
            boolean midIsOpponent = isP1 ? (midIcon==icon2 || midIcon==king2) : (midIcon==icon || midIcon==king);
            if (midIsOpponent)
            {
               moves.add(new int[]{jr,jc});
            }
         }
      }
      return moves;
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

         block[x1][y1].setBackground(SELECTED_SQUARE);
         highlightedDestinations = legalDestinations(x1,y1);
         for (int[] d : highlightedDestinations)
         {
            block[d[0]][d[1]].setBackground(DEST_SQUARE);
         }
      }

      else
      {
         String pair2 = e.getActionCommand();
         String[] pairXY2 = pair2.split(" ");
         x2 = convert(pairXY2[0]);
         y2 = convert(pairXY2[1]);
         System.out.println("this is two: y:"+y2+" x:"+x2);
         clickedOnce = false;

         block[x1][y1].setBackground(PLAYABLE_SQUARE);
         for (int[] d : highlightedDestinations)
         {
            block[d[0]][d[1]].setBackground(PLAYABLE_SQUARE);
         }
         highlightedDestinations.clear();


                 
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
                     player1Score++;
                     updateScoreLabels();
                 }
               
          else if (((x2==x1+2) && (y2==y1-2)) && ((block[x1+1][y1-1].getIcon()==icon2) ||  (block[x1+1][y1-1].getIcon()==king2)))
                 {
                    block[x2][y2].setIcon(block[x1][y1].getIcon());
                    block[x1][y1].setIcon(null);
                    block[x1+1][y1-1].setIcon(null);
                    clickedP1=true;
                    clickedP2=false;
                    player2Pieces--;
                    player1Score++;
                    updateScoreLabels();
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
                      player2Score++;
                      updateScoreLabels();
                   }   
           else if (((x2==x1-2) && (y2==y1-2)) && ((block[x1-1][y1-1].getIcon()==icon) || (block[x1-1][y1-1].getIcon()==king)))
                     {
                        block[x2][y2].setIcon(block[x1][y1].getIcon());
                        block[x1][y1].setIcon(null);
                        block[x1-1][y1-1].setIcon(null);
                        clickedP2=true;
                        clickedP1= false;
                        player1Pieces--;
                        player2Score++;
                        updateScoreLabels();
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
               player1Score++;
               updateScoreLabels();
            }
                  
            else if (((x2==x1+2) && (y2==y1-2)) && ((block[x1+1][y1-1].getIcon()==king2) || (block[x1+1][y1-1].getIcon()==icon2)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1+1][y1-1].setIcon(null);
               clickedP1=true;
               clickedP2=false;
               player2Pieces--;
               player1Score++;
               updateScoreLabels();
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
               player1Score++;
               updateScoreLabels();
            }
            else if (((x2==x1-2) && (y2==y1-2)) && ((block[x1-1][y1-1].getIcon()==king2) || (block[x1-1][y1-1].getIcon()==icon2)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1-1][y1-1].setIcon(null);
               clickedP1=true;
               clickedP2=false;
               player2Pieces--;
               player1Score++;
               updateScoreLabels();
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
               player2Score++;
               updateScoreLabels();
            }
                  
            else if(((x2==x1+2) && (y2==y1-2)) && ((block[x1+1][y1-1].getIcon()==king) || (block[x1+1][y1-1].getIcon()==icon)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1+1][y1-1].setIcon(null);
               clickedP1=false;
               clickedP2=true;
               player1Pieces--;
               player2Score++;
               updateScoreLabels();
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
               player2Score++;
               updateScoreLabels();
            }
            else if (((x2==x1-2) && (y2==y1-2)) && ((block[x1-1][y1-1].getIcon()==king) || (block[x1-1][y1-1].getIcon()==icon)))
            {
               block[x2][y2].setIcon(block[x1][y1].getIcon());
               block[x1][y1].setIcon(null);
               block[x1-1][y1-1].setIcon(null);
               clickedP1=false;
               clickedP2=true;
               player1Pieces--;
               player2Score++;
               updateScoreLabels();
            }
         }

         updateTurnLabel();
      }
   if(player1Pieces==0 || player2Pieces==0)
      {
         String winner = (player2Pieces==0) ? "Player 1" : "Player 2";
         String message = winner+" wins!\nFinal score - Player 1: "+player1Score+"  Player 2: "+player2Score;
         Object[] options = {"Restart","Exit"};
         int choice = JOptionPane.showOptionDialog(this, message, "Game Over",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, options, options[0]);
         if (choice == 1)
         {
            System.exit(0);
         }
         else
         {
            Checkers.this.dispose();
            Checkers newGame = new Checkers();
            newGame.setVisible(true);
         }
      }
   
   
   }
}