package us.nineworlds.xstreamer.jobs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import us.nineworlds.xstreamer.XStreamer;

@PersistJobDataAfterExecution
public class CountDownJob implements Job {

   public CountDownJob() {
   }

   public void execute(JobExecutionContext context) throws JobExecutionException {      
      Duration duration = new Duration(XStreamer.getCountDownTime());
      duration = duration.minus(1000);
      XStreamer.setCountDownTime(duration.getMillis());
            
      Period timeLeft = duration.toPeriod();
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
      String formattedTime = timeLeft.toString(formatter);
      File counterFile = new File("/home/dcarver/timer.txt");
      try {
         FileUtils.writeStringToFile(counterFile, formattedTime, "UTF-8");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
