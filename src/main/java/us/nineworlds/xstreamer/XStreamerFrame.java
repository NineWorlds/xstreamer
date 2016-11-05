package us.nineworlds.xstreamer;

import static org.quartz.JobBuilder.newJob;

import java.awt.event.ActionEvent;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.joda.time.DateTime;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import us.nineworlds.xstreamer.jobs.CountDownJob;

public class XStreamerFrame extends JFrame {

   private static final String COUNT_DOWN_JOB = "countDownJob";
   private static final long serialVersionUID = 1L;
   private boolean timerRunning = false;
   private JobKey countDownJobKey;

   private TriggerKey countDownTriggerKey;
   JButton startPauseButton;
   JButton resetButton;

   JTextField timerHourTextField;
   JTextField timerMinutesTextField;
   JTextField timerSecondsTextField;

   public static JLabel countDownHoursLabel;
   public static JLabel countDownMinutesLabel;
   public static JLabel countDownSecondsLabel;

   @Action
   public void startTimer(ActionEvent event) {
      Scheduler scheduler = XStreamer.getScheduler();

      if (countDownTriggerKey == null) {
         setInitialTimer();
         scheduleTimer(scheduler);
         startPauseButton.setText("Pause");
         return;
      }

      if (timerRunning) {
         try {
            scheduler.deleteJob(countDownJobKey);
            System.out.println("Timer has been paused.");
            timerRunning = false;
            startPauseButton.setText("Resume");
         } catch (SchedulerException e) {
            e.printStackTrace();
         }
         return;
      }
      
      scheduleTimer(scheduler);
      System.out.println("Timer has resumed.");
      startPauseButton.setText("Pause");
      timerRunning = true;
   }

   private void setInitialTimer() {
      String inthours = timerHourTextField.getText();
      String intMins = timerMinutesTextField.getText();
      String intSeconds = timerSecondsTextField.getText();
      
      DateTime startTime = DateTime.now();
      DateTime endTime = startTime.plusHours(Integer.parseInt(inthours)).plusMinutes(Integer.parseInt(intMins)).plus(Integer.parseInt(intSeconds));
      
      XStreamer.setCountDownTime(endTime.getMillis() - startTime.getMillis());
   }
   
   @Action
   public void resetTimer(ActionEvent event) {
      setInitialTimer();
      try {
         XStreamer.getScheduler().deleteJob(countDownJobKey);
      } catch (SchedulerException e) {
         e.printStackTrace();
      }
      countDownJobKey = null;
      countDownTriggerKey = null;
      timerRunning = false;
      startPauseButton.setText("Start");
   }

   private void scheduleTimer(Scheduler scheduler) {
      SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
      JobDetail jobDetail = newJob(CountDownJob.class).withIdentity(COUNT_DOWN_JOB).storeDurably().build();
      countDownJobKey = jobDetail.getKey();

      Trigger trigger = TriggerBuilder.newTrigger().startNow().withIdentity(COUNT_DOWN_JOB)
            .withSchedule(simpleScheduleBuilder.withIntervalInSeconds(1).repeatForever()).build();
      countDownTriggerKey = trigger.getKey();
      try {
         scheduler.scheduleJob(jobDetail, trigger);
         timerRunning = true;
      } catch (SchedulerException e) {
         System.out.println("Error scheduling job");
      }
   }
}
