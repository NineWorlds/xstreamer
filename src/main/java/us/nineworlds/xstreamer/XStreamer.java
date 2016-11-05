package us.nineworlds.xstreamer;

import javax.swing.JFrame;

import org.joda.time.Period;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.swixml.jsr296.SwingApplication;

import com.github.xws.XwsSpec;

public class XStreamer extends SwingApplication {

   private static XwsSpec player1;
   private static XwsSpec player2;
   private static Scheduler scheduler;
   private static long countDownTime;

   public static void main(String[] args) throws Exception {
      scheduler = StdSchedulerFactory.getDefaultScheduler();
      scheduler.start();
      SwingApplication.launch(XStreamer.class, args);
   }

   @Override
   protected void shutdown() {
      // TODO Auto-generated method stub
      super.shutdown();
      try {
         scheduler.shutdown();
      } catch (SchedulerException e) {
         e.printStackTrace();
      }
   }

   @Override
   protected void startup() {
      try {
         JFrame jframe = render(new XStreamerFrame(), "xml/layout/main-layout.xml");
         show(jframe);
      } catch (Exception e) {
         e.printStackTrace();
         exit();
      }
   }

   public static Scheduler getScheduler() {
      return scheduler;
   }
   
   public static synchronized long getCountDownTime() {
      return countDownTime;
   }

   public static synchronized void setCountDownTime(long countDownTime) {
      XStreamer.countDownTime = countDownTime;
   }


}
