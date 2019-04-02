
import java.awt.*;
import java.applet.*;

public class Gomoku extends Applet
{
   public int rows = 20;
   public int cols = 20;
   public int squareSize = 30;
   public int width = squareSize * (cols-1);
   public int height = squareSize * (rows-1);
   public int lineWidth = 2;
   public int midLineWidth = lineWidth/2;
   public int count = 0;
   public int[] xCoord;
   public int[] yCoord;
   public int[] check;
   public int[][] board;
   public boolean player1;
   public boolean player2;
   public boolean test;
   public int num;
   
   public void init(){
      setSize(1080,720);
      xCoord = new int[361];
      yCoord = new int[361];
      check = new int[19];
      board = new int[19][19];
      test = true;
      num = 0;
      
   }

	public void paint(Graphics g)
	{
      // setup
      for (int i = 1; i < rows; i++){
         g.fillRect(squareSize-midLineWidth+210+i*30,40,lineWidth,height);
         g.fillRect(255,squareSize-midLineWidth+i*30-5,width,lineWidth);
         if (i == 10){
         g.fillRect(squareSize-midLineWidth+210+i*30,40,lineWidth,height);
         g.fillRect(255,squareSize-midLineWidth+i*30-5,width,lineWidth);
         }
         if (i<10){
         g.drawString(Integer.toString(i),i*30+236,36);
         g.drawString(Integer.toString(i),i*30+236,622);
         g.drawString(Integer.toString(i),242,i*30+30);
         g.drawString(Integer.toString(i),832,i*30+30);
         }
         else{
         g.drawString(Integer.toString(i),i*30+232,36);
         g.drawString(Integer.toString(i),i*30+232,622);
         g.drawString(Integer.toString(i),238,i*30+30);
         g.drawString(Integer.toString(i),828,i*30+30);

         }
        
      }
      g.setColor(Color.black);
      g.fillOval(356,141,8,8);
      g.fillOval(356,321,8,8);
      g.fillOval(356,501,8,8);
      
      g.fillOval(536,141,8,8);
      g.fillOval(536,321,8,8);
      g.fillOval(536,501,8,8);
      
      g.fillOval(716,141,8,8);
      g.fillOval(716,321,8,8);    
      g.fillOval(716,501,8,8);

      
      g.drawRect(235,20,width+40,height+40);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
      if (num%2==0&&num<=362&&player1!=true&&player2!=true){
         g.setColor(Color.black);
         g.drawString("It's Black's turn.",360,height+100);
      }
      else if (num%2!=0&&num<362&&player1!=true&&player2!=true){
         g.setColor(Color.black);
         g.drawString("It's White's turn",360,height+100);
      }
      
           
      for (int i = 0; i < 19; i ++){
         for (int j = 0; j < 19; j ++){
            if (board[i][j]==1){                 // 1 is black
               g.setColor(Color.black);
               g.fillOval(270+i*30-8,55+j*30-8,16,16);
            }
            
            if (board[i][j]==2){                   // 2 is white
               g.setColor(Color.black);
               g.drawOval(270+i*30-8,55+j*30-8,16,16);
               g.setColor(Color.white);
               g.fillOval(270+i*30-7,55+j*30-7,14,14);

            }
         }
      } 

      // if either player wins or the game ends in a draw, click to restart
      if (player1){
         g.setColor(Color.black);
         g.drawString("Black wins! Click to restart.",360,height+100);
      }
      
      else if (player2){
         g.setColor(Color.black);
         g.drawString("White wins! Click to restart.",360,height+100);
      }
      
      else if (count == 361){
         g.setColor(Color.black);
         g.drawString("It's a draw! Click to restart.",360,height+100);
      }
   }
             
   public boolean mouseDown(Event e, int x, int y){
      if (player1||player2||count == 361){
         for (int k = 0; k < 19; k++){
            xCoord[k]=0;
            yCoord[k]=0;
            check[k]=0;
            for (int i = 0; i < 19; i++){
               board[k][i]=0;
            }
         }
         player1=false;
         player2=false;
      }
      else if (num < 361 & x<822 & y <605 & x>257 & y>40){
         for (int i = 0; i < num; i ++){
            if ((int)(Math.round((x-270)/30.0))==xCoord[i]&&(int)(Math.round((y-55)/30.0))==yCoord[i])
            {
               test = false;
               num --;
            }
         } 
         if (test){
            board[(int)(Math.round((x-270)/30.0))][(int)(Math.round((y-55)/30.0))] = num%2+1;        
         }
         xCoord[num] = (int)(Math.round((x-270)/30.0));
         yCoord[num] = (int)(Math.round((y-55)/30.0));
         num ++;
         test = true; 
      }
      count ++;
      checking();
      repaint();
      return true;
   }

   // after each move is placed, check if the game has ended 
   public void checking(){
      //horizontal
      for (int i = 0; i < 15; i ++){
         for (int j = 0; j < 19; j ++){
            if(board[i][j] == 1 && board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && board[i][j] == board[i+3][j] && board[i][j] == board[i+4][j]){
               player1 = true;
            }
            if(board[i][j] == 2 && board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && board[i][j] == board[i+3][j] && board[i][j] == board[i+4][j]){
               player2 = true;
            }
         }
      }
      
      //vertical
      for (int i = 0; i < 19; i ++){
         for (int j = 0; j < 15; j ++){
            if(board[i][j] == 1 && board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && board[i][j] == board[i][j+3] && board[i][j] == board[i][j+4]){
               player1 = true;
            }
            if(board[i][j] == 2 && board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && board[i][j] == board[i][j+3] && board[i][j] == board[i][j+4]){
               player2 = true;
            }
         }
      }
      
      //diagonal  
      for (int i = 0; i < 15; i ++){
         for (int j = 0; j < 15; j ++){
            if(board[i][j] == 1 && board[i][j] == board[i+1][j+1] && board[i][j] == board[i+2][j+2] && board[i][j] == board[i+3][j+3] && board[i][j] == board[i+4][j+4]){
               player1 = true;
            }
            if(board[i][j] == 2 && board[i][j] == board[i+1][j+1] && board[i][j] == board[i+2][j+2] && board[i][j] == board[i+3][j+3] && board[i][j] == board[i+4][j+4]){
               player2 = true;
            }
         }
      }
      for (int i = 4; i < 19; i ++){
         for (int j = 0; j < 15; j ++){
            if(board[i][j] == 1 && board[i][j] == board[i-1][j+1] && board[i][j] == board[i-2][j+2] && board[i][j] == board[i-3][j+3] && board[i][j] == board[i-4][j+4]){
               player1 = true;
            }
            if(board[i][j] == 2 && board[i][j] == board[i-1][j+1] && board[i][j] == board[i-2][j+2] && board[i][j] == board[i-3][j+3] && board[i][j] == board[i-4][j+4]){
               player2 = true;
            }
         }
      }                        
      repaint();  
      }
}