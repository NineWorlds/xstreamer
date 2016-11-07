package us.nineworlds.xstreamer.jobs;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.SwingUtilities;

import org.apache.commons.io.FileUtils;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerException;

import us.nineworlds.xstreamer.XStreamer;
import us.nineworlds.xstreamer.XStreamerFrame;

@PersistJobDataAfterExecution
public class CountDownJob implements Job {

   public CountDownJob() {
   }

   @Override
public void execute(JobExecutionContext context) throws JobExecutionException {
      long timeLeft = XStreamer.getCountDownTime();
      if (timeLeft <= 0) {
         try {
            XStreamer.getScheduler().deleteJob(context.getJobDetail().getKey());
         } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         return;
      }
      
      Duration duration = new Duration(XStreamer.getCountDownTime());
      duration = duration.minus(1000);
      XStreamer.setCountDownTime(duration.getMillis());
            
      final Period periodLeft = duration.toPeriod();
      PeriodFormatter formatter = new PeriodFormatterBuilder()
            .minimumPrintedDigits(2)
            .printZeroAlways()
            .appendHours()
            .appendSeparator(":")
            .minimumPrintedDigits(2)
            .printZeroAlways()
            .appendMinutes()
            .appendSeparator(":")
            .minimumPrintedDigits(2)
            .printZeroAlways()
            .appendSeconds()
            .toFormatter();
      String formattedTime = periodLeft.toString(formatter);
      Runnable updateUi = () -> { 
         int hours = periodLeft.getHours();
         int mins = periodLeft.getMinutes();
         int seconds = periodLeft.getSeconds();
         NumberFormat numberFormat = new DecimalFormat("00");
         XStreamerFrame.countDownHoursLabel.setText(numberFormat.format(hours));
         XStreamerFrame.countDownMinutesLabel.setText(numberFormat.format(mins));
         XStreamerFrame.countDownSecondsLabel.setText(numberFormat.format(seconds));
        };
      SwingUtilities.invokeLater(updateUi);

      File counterFile = new File("/home/dcarver/timer.txt");
      try {
         FileUtils.writeStringToFile(counterFile, formattedTime, "UTF-8");
      } catch (IOException e) {
         e.printStackTrace();
      } 
   }

}
