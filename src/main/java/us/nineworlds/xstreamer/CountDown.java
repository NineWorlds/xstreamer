package us.nineworlds.xstreamer;
// Note.  This example uses the images from http://www.cs.uic.edu/~i340/mp2images/index.html

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CountDown extends JFrame {
   private JButton plainButton, fancyButton;
   private int count;

   private Timer timeClock, timeClock2;
   private Icon iconArray[];

   // set up GUI
   public CountDown()
   {
      super( "Timer Example" );
      

      String names[] = 
         { "0.gif", "1.gif", "2.gif", "3.gif", "4.gif", 
           "5.gif", "6.gif", "7.gif", "8.gif", "9.gif" };

      // create and add icons
      iconArray = new Icon [ names.length ];

      for ( int count = 0; count < names.length; count++ ) 
        {
         iconArray[ count ] = new ImageIcon ( names[ count ] );
        }


      int delay = 1000;
      timeClock = new Timer(delay, new TimerHandler () );
      timeClock2 = new Timer(delay, new TimerHandler2 () );

      // get content pane and set its layout
      Container container = getContentPane();
      container.setLayout( new FlowLayout() );

      // create buttons
      plainButton = new JButton( "Show Delayed Message" );
      container.add( plainButton );


      fancyButton = new JButton( "Click to Count Down", iconArray[9] );

      container.add( fancyButton );

      // create an instance of inner class ButtonHandler
      // to use for button event handling 
      ButtonHandler handler = new ButtonHandler();
      ButtonHandler1 handler1 = new ButtonHandler1();
      ButtonHandler2 handler2 = new ButtonHandler2();
      fancyButton.addActionListener( handler2 );
      plainButton.addActionListener( handler1 );

      setSize( 275, 175 );
      setVisible( true );

   } // end CountDown constructor

   public static void main( String args[] )
   { 
      CountDown application = new CountDown();
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }

   // inner class for button event handling
   private class ButtonHandler implements ActionListener {

      // handle button event
      public void actionPerformed( ActionEvent event )
      {
         JOptionPane.showMessageDialog( CountDown.this,
            "You pressed: " + event.getActionCommand() );
      }

   } // end private inner class ButtonHandler



   // inner class for button event handling
   private class ButtonHandler1 implements ActionListener {

      // handle button event
      public void actionPerformed( ActionEvent event )
      {
         timeClock.start();

         JOptionPane.showMessageDialog( CountDown.this,
            "You pressed: " + event.getActionCommand() );
      }

   } // end private inner class ButtonHandler1

   // inner class for timer event handling
   private class TimerHandler implements ActionListener {

      // handle button event
      public void actionPerformed( ActionEvent event )
      {
         JOptionPane.showMessageDialog( CountDown.this,
            "Timer Expired"  );

	timeClock.stop();

      }

   } // end private inner class TimerHandler



   // inner class for button event handling
   private class ButtonHandler2 implements ActionListener {

      // handle button event
      public void actionPerformed( ActionEvent event )
      {
        count = 9;
	fancyButton.setIcon( iconArray[count] );
        fancyButton.setEnabled( false );

	timeClock2.start();
      }

   } // end private inner class ButtonHandler

   // inner class for timer event handling
   private class TimerHandler2 implements ActionListener {

      // handle button event
      public void actionPerformed( ActionEvent event )
      {
        count--;

        if (count < 0)
           {
    	    timeClock2.stop();
            fancyButton.setEnabled( true );
           }
        else
	   {
            fancyButton.setIcon (iconArray[count]);
           }
      }

   } // end private inner class TimerHandler2


} // end class ButtonTest
