import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class YahtzeeMain extends JFrame
{
	private JPanel pnlMain       = new JPanel();
	public int roll = 3;
	public int round = 13;
	private static JLabel [] dice = new JLabel[6];
	private static ImageIcon [] dicePics = new ImageIcon[6];
	private JCheckBox[] scoreOptions = new JCheckBox[14];
	private static JCheckBox [] diceOptions = new JCheckBox[5];
	private JLabel[] blanks = new JLabel[24];
	private JLabel[] lblScores = new JLabel[18];
	private JButton btnRoll = new JButton ("ROLL");
	private JButton btnNxtRound = new JButton ("Next Round");
	private JButton btnEndGame = new JButton ("End Game");
	private JPanel pnlDice = new JPanel();
	private int score = 0;
		{
			for(int i = 0; i < 14; i++)		
			{
				scoreOptions[i] = new JCheckBox();
				scoreOptions[i].setEnabled(false);
			}
			for(int i = 0; i < 24; i++)
			{
				blanks[i] = new JLabel("");
			}
			for(int i = 0; i < 18; i++)
			{
				lblScores[i] = new JLabel();
			}
		}
	public YahtzeeMain()
	   {
	      setTitle("Kelli's Yahtzee Game");                            // set the window title.
	      setSize(1000, 700);                  // set the size of the window.
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      pnlMain.setBackground(Color.pink);                    // change color to black
	      buildPanel();                                          // Build the panel and add it to the frame.
	      add(pnlMain);                                          // Add the panel to the frame's content pane.
	      setVisible(true);                                      // Display the window.
	    }
	private void buildPanel()
	  {     
			pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.PAGE_AXIS));
			
			//TITLE
			JPanel pnlTitle = new JPanel();
			pnlTitle.setBackground(Color.white);
			Font font = new Font("Jokerman", Font.PLAIN, 35);
		    JLabel textLabel = new JLabel("KELLI'S YAHTZEE GAME");
		    textLabel.setForeground(Color.magenta);
		    textLabel.setFont(font);
		    pnlTitle.add(textLabel);
		
		  // Build Picture panel
	      pnlDice.setBackground(Color.white);
	      pnlDice.setLayout(new GridLayout(2,5));
	      
	      for(int j = 0; j < 6; j++)
	      {
	      dicePics[j] = new ImageIcon("pic" + j + ".png");
	      }
	     
	      for(int i = 0; i < 5; i++)
	      {
	    	  dice[i] = new JLabel(dicePics[(int)(Math.random() * 6)]);
	    	  dice[i].setIcon(dicePics[(int)(Math.random() * 6)]);
	    	  pnlDice.add(dice[i]);
	      }
	   
	   	      
	     for(int k = 1; k < 6; k++)
	     {
	    	 diceOptions[k - 1] = new JCheckBox("KEEP DICE " + k);
	    	 pnlDice.add(diceOptions[k - 1]);
	    	 diceOptions[k-1].addActionListener(new diceOptionHandler());
	     }
	      
	      
	     
	       
	      // Build buttons panel
	      JPanel pnlButtons = new JPanel();
	      pnlButtons.setLayout(new GridLayout(10,7));
	      buttons(pnlButtons);
		  
	      //ROLL AND END GAME OPTIONS
	      JPanel pnlOptions = new JPanel();
	      btnRoll.setBackground(Color.green);
	      btnRoll.addActionListener(new BtnRollHandler());  
	      pnlOptions.add(btnRoll);
	      btnNxtRound.setBackground(Color.yellow);
	      btnNxtRound.setEnabled(false);
	      btnNxtRound.addActionListener(new BtnNxtRoundHandler());  
	      pnlOptions.add(btnNxtRound);
	      btnEndGame.setBackground(Color.cyan);
	      btnEndGame.setEnabled(false);
	    //  btnEndGame.addActionListener(new BtnHandler()); 
	      pnlOptions.add(btnEndGame);
	      
	      // Build main panel
	      pnlMain.add(pnlTitle);
		  pnlMain.add(pnlDice);
		  pnlMain.add(pnlButtons);		
		  pnlMain.add(pnlOptions);
	  }

	private class BtnRollHandler implements ActionListener 
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  if(roll > 0 && round > 0)
	    	  {

	    		  for(int i = 0; i < 5; i++)
	    		  {
	    			  if(diceOptions[i].isSelected() == false)
	    			  {
	    			  dice[i].setIcon(dicePics[(int)(Math.random() * 6)]);
	    	    	  pnlDice.add(dice[i]);
	    	    	  pnlMain.add(pnlDice);
	    		  
	    			  }
	    		  }
	    		  roll = roll - 1;
	    		 if(roll == 0)
	    		  {
	    			  btnRoll.setEnabled(false);
	    			  btnNxtRound.setEnabled(true);
	    			  for(int i = 0; i < 5; i++)
	    	    	  {
	    	    		if(diceOptions[i].isSelected())
	    	    		{
	    	    			diceOptions[i].setEnabled(false);
	    	    		}
	    	    	  }
	    			  for(int i = 0; i < 14; i++)		
	    				{
	    					scoreOptions[i].setEnabled(true);
	    				}
	    		  }
	    	  }
	      }
	   }
	private class BtnEndGameHandler implements ActionListener 
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  
	    	 
	      }
	   }
	private class BtnNxtRoundHandler implements ActionListener 
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	   btnRoll.setEnabled(true);
	    	   roll = 3;
	    	   btnNxtRound.setEnabled(false);
	    	   
	    	   for(int i = 0; i < 5; i++)
	    		  {
	    			  dice[i].setIcon(dicePics[(int)(Math.random() * 5)]);
	    	    	  pnlDice.add(dice[i]);
	    	    	  pnlMain.add(pnlDice);
	    		  }
	    	   for(int i = 0; i < 14; i++)		
				{
					scoreOptions[i].setEnabled(false);
				}
	    	   for(int i = 0; i < 5; i++)
	    	   {
	    		   diceOptions[i].setEnabled(true);
	    	   }
	    	   round = round - 1;
	    	 
	    	   if(round == 0)
	    		  {
	    			  btnRoll.setEnabled(false);
	    			  btnNxtRound.setEnabled(false);
	    			  btnEndGame.setEnabled(true);
	    		  }
	      }
	   }
	public void buttons (JPanel pnlButtons)
	{
		      pnlButtons.add(blanks[0]);
		      pnlButtons.add(blanks[1]);
		      JLabel lblRScore = new JLabel("Score");
		      pnlButtons.add(lblRScore);
		      pnlButtons.add(blanks[2]);
		      pnlButtons.add(blanks[3]);
		      pnlButtons.add(blanks[4]);
		      JLabel lblLScore = new JLabel("Score");
		      pnlButtons.add(lblLScore);

		      scoreOptions[0].addActionListener(new diceOptionHandler());
		      pnlButtons.add(scoreOptions[0]);
		      JLabel lblOnes = new JLabel("Ones");
		      pnlButtons.add(lblOnes);
		      pnlButtons.add(lblScores[0]);
		      pnlButtons.add(blanks[5]);
		      scoreOptions[7].addActionListener(new diceOptionHandler());
		      pnlButtons.add(scoreOptions[7]);
		      JLabel lblThreeKind = new JLabel("Three of a Kind");
		      pnlButtons.add(lblThreeKind);
		      pnlButtons.add(lblScores[8]);

		      pnlButtons.add(scoreOptions[1]);
		      scoreOptions[1].addActionListener(new diceOptionHandler());
		      JLabel lblTwos = new JLabel("Twos");
		      pnlButtons.add(lblTwos);
		      pnlButtons.add(lblScores[1]);
		      pnlButtons.add(blanks[8]);
		      pnlButtons.add(scoreOptions[8]);
		      scoreOptions[8].addActionListener(new diceOptionHandler());
		      JLabel lblFullHouse = new JLabel("Full House");
		      pnlButtons.add(lblFullHouse);
		      pnlButtons.add(lblScores[10]);
		     		      
		      pnlButtons.add(scoreOptions[2]);
		      scoreOptions[2].addActionListener(new diceOptionHandler());
		      JLabel lblThrees = new JLabel("Threes");
		      pnlButtons.add(lblThrees); 
		      pnlButtons.add(lblScores[2]);
		      pnlButtons.add(blanks[9]);
		      pnlButtons.add(scoreOptions[9]);
		      scoreOptions[9].addActionListener(new diceOptionHandler());
		      JLabel lblFourKind = new JLabel("Four of a Kind");
		      pnlButtons.add(lblFourKind);
		      pnlButtons.add(lblScores[11]);
		      
		      pnlButtons.add(scoreOptions[3]);
		      scoreOptions[3].addActionListener(new diceOptionHandler());
		      JLabel lblFours = new JLabel("Fours");
		      pnlButtons.add(lblFours);
		      pnlButtons.add(lblScores[3]);
		      pnlButtons.add(blanks[10]);
		      pnlButtons.add(scoreOptions[10]);
		      scoreOptions[10].addActionListener(new diceOptionHandler());
		      JLabel lblSmallStraight = new JLabel("Small Straight");
		      pnlButtons.add(lblSmallStraight);
		      pnlButtons.add(lblScores[12]);
		      
		      pnlButtons.add(scoreOptions[4]);
		      scoreOptions[4].addActionListener(new diceOptionHandler());
		      JLabel lblFives = new JLabel("Fives");
		      pnlButtons.add(lblFives);
		      pnlButtons.add(lblScores[4]);
		      pnlButtons.add(blanks[11]);
		      pnlButtons.add(scoreOptions[11]);
		      scoreOptions[11].addActionListener(new diceOptionHandler());
		      JLabel lblLargeStraight = new JLabel("Large Straight");
		      pnlButtons.add(lblLargeStraight);
		      pnlButtons.add(lblScores[13]);
		      
		      pnlButtons.add(scoreOptions[5]);
		      scoreOptions[5].addActionListener(new diceOptionHandler());
		      JLabel lblSixes = new JLabel("Sixes");
		      pnlButtons.add(lblSixes);
		      pnlButtons.add(lblScores[5]);
		      pnlButtons.add(blanks[12]);
		      pnlButtons.add(scoreOptions[12]);
		      scoreOptions[12].addActionListener(new diceOptionHandler());
		      JLabel lblYahtzee = new JLabel("Yahtzee");
		      pnlButtons.add(lblYahtzee);
		      pnlButtons.add(lblScores[14]);
		      
		      pnlButtons.add(blanks[13]);
		      JLabel lblBonus = new JLabel("BONUS");
		      pnlButtons.add(lblBonus);
		      pnlButtons.add(lblScores[6]);
		      pnlButtons.add(blanks[14]);
		      pnlButtons.add(scoreOptions[13]);
		      scoreOptions[13].addActionListener(new diceOptionHandler());
		      JLabel lblChance = new JLabel("CHANCE");
		      pnlButtons.add(lblChance);
		      pnlButtons.add(lblScores[15]);
		      
		      pnlButtons.add(blanks[15]);
		      JLabel lblLTotal = new JLabel("L TOTAL");
		      pnlButtons.add(lblLTotal);
		      pnlButtons.add(lblScores[7]);
		      pnlButtons.add(blanks[16]);
		      pnlButtons.add(blanks[17]);
		      JLabel lblRTotal = new JLabel("R TOTAL");
		      pnlButtons.add(lblRTotal);
		      pnlButtons.add(lblScores[16]);
		     
		      pnlButtons.add(blanks[18]);
		      pnlButtons.add(blanks[19]);
		      pnlButtons.add(blanks[20]);
		      pnlButtons.add(lblScores[17]);
		      pnlButtons.add(blanks[21]);
		      pnlButtons.add(blanks[22]);
		      pnlButtons.add(blanks[23]);
	}
	private class diceOptionHandler implements ActionListener  // handler for Previous/Next button
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	if(scoreOptions[0].isSelected())
	    	{
	    		lblScores[0].setText(YahtzeeMain.Ones(score));
	    	}
	    	if(scoreOptions[1].isSelected())
	    	{
	    		lblScores[1].setText(YahtzeeMain.Twos(score));
	    	} 
	    	if(scoreOptions[2].isSelected())
	    	{
	    		lblScores[2].setText(YahtzeeMain.Threes(score));
	    	} 
	    	if(scoreOptions[3].isSelected())
	    	{
	    		lblScores[3].setText(YahtzeeMain.Fours(score));
	    	} 
	    	if(scoreOptions[4].isSelected())
	    	{
	    		lblScores[4].setText(YahtzeeMain.Fives(score));
	    	} 
	    	if(scoreOptions[5].isSelected())
	    	{
	    		lblScores[5].setText(YahtzeeMain.Sixes(score));
	    	}
	    	if(scoreOptions[7].isSelected())
	    	{
	    		lblScores[8].setText(YahtzeeMain.ThreeOfaKind(score));
	    	} 
	    	for(int i = 0; i < 9; i++)
	    	{
	    		if(scoreOptions[i].isSelected() == false)
	    		{
	    			lblScores[i].setText("");
	    		}
	    	}
	      }
	   }
	public static String Ones(int scr)
	{
		for(int i = 0; i < 5; i++)
		{
			if(dice[i].getIcon() == dicePics[0])
			{
				scr = scr + 1;
			}
		}
		return Integer.toString(scr);
	}
	public static String Twos(int scr)
	{
		for(int i = 0; i < 5; i++)
		{
			if(dice[i].getIcon() == dicePics[1])
			{
				scr = scr + 2;
			}
		}
		return Integer.toString(scr);
	}
	public static String Threes(int scr)
	{
		for(int i = 0; i < 5; i++)
		{
			if(dice[i].getIcon() == dicePics[2])
			{
				scr = scr + 3;
			}
		}
		return Integer.toString(scr);
	}
	public static String Fours(int scr)
	{
		for(int i = 0; i < 5; i++)
		{
			if(dice[i].getIcon() == dicePics[3])
			{
				scr = scr + 4;
			}
		}
		return Integer.toString(scr);
	}
	public static String Fives(int scr)
	{
		for(int i = 0; i < 5; i++)
		{
			if(dice[i].getIcon() == dicePics[4])
			{
				scr = scr + 5;
			}
		}
		return Integer.toString(scr);
	}
	public static String Sixes(int scr)
	{
		for(int i = 0; i < 5; i++)
		{
			if(dice[i].getIcon() == dicePics[5])
			{
				scr = scr + 6;
			}
		}
		return Integer.toString(scr);
	}
	public static String ThreeOfaKind(int scr)
	{
		int scr_1 = 0;
		int scr_2 = 0;
		int scr_3 = 0;
		int scr_4 = 0;
		int scr_5 = 0;
		int scr_6 = 0;
		
		for(int i = 0; i < 5; i++)
		{
			if(dice[i].getIcon() == dicePics[0])
			{
				scr_1 = scr_1 + 1;
			}
			else if(dice[i].getIcon() == dicePics[1])
			{
				scr_2 = scr_2 + 1;
			}
			else if(dice[i].getIcon() == dicePics[2])
			{
				scr_3 = scr_3 + 1;
			}
			else if(dice[i].getIcon() == dicePics[3])
			{
				scr_4 = scr_4 + 1;
			}
			else if(dice[i].getIcon() == dicePics[4])
			{
				scr_5 = scr_5 + 1;
			}
			else if(dice[i].getIcon() == dicePics[5])
			{
				scr_6 = scr_6 + 1;
			}
		}
		
		if(scr_1 >= 3 || scr_2 >= 3 || scr_3 >= 3 || scr_4 >= 3 || scr_5 >= 3 || scr_6 >= 3)
		{
			for(int j = 0; j < 5; j++)
			{
				for (int k = 0; k < 5; k++)
				{
					if(dice[j].getIcon() == dicePics[k])
					{
						scr = scr + k;
					}
				}
			}
		}
		else
		{
			scr = 0;
		}
		return Integer.toString(scr);
	}
	public static void main (String[] args)
	{
		new YahtzeeMain();
	}
}