package us.nineworlds.xstreamer;

import static org.quartz.JobBuilder.newJob;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ThresholdingOutputStream;
import org.joda.time.Period;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.swixml.jsr296.SwingApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.guidokessels.ships.Ship;
import com.github.xws.XwsSpec;

import freemarker.template.Configuration;
import freemarker.template.Template;
import us.nineworlds.xstreamer.jobs.CountDownJob;
import us.nineworlds.xstreamer.jobs.GenerateSquadJob;
import us.nineworlds.xstreamer.model.lookup.ShipsLookup;

public class XStreamer extends SwingApplication {

   private static XwsSpec player1;   
   private static XwsSpec player2;
   private static Scheduler scheduler;
   private static long countDownTime;
   private static Configuration freemarkerConfig;
   private static us.nineworlds.xstreamer.Configuration configuration;

   public static void main(String[] args) throws Exception {
      scheduler = StdSchedulerFactory.getDefaultScheduler();
      scheduler.start();
      
      configuration = us.nineworlds.xstreamer.Configuration.getInstance();
      
      ObjectMapper mapper = new ObjectMapper();
      InputStream shipInputStream = XStreamer.class.getResourceAsStream("/xws-data/ships.json");
      List<Ship> shipData = Arrays.asList(mapper.readValue(XStreamer.class.getResourceAsStream("/xws-data/ships.json"), Ship[].class));
      IOUtils.closeQuietly(shipInputStream);
      
      ShipsLookup.newInstance(shipData);

      player1 = mapper.readValue(new File(configuration.getPlayer1XWSFilePath()), XwsSpec.class);
      player2 = mapper.readValue(new File(configuration.getPlayer2XWSFilePath()), XwsSpec.class);
      
      initFreemarker();
      SwingApplication.launch(XStreamer.class, args);
   }

   @Override
   protected void shutdown() {
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
   
   public static XwsSpec getPlayer1() {
      return player1;
   }

   public static void setPlayer1(XwsSpec player1) {
      XStreamer.player1 = player1;
   }
   
   public static XwsSpec getPlayer2() {
      return player2;
   }

   public static void setPlayer2(XwsSpec player2) {
      XStreamer.player2 = player2;
   }

   private static void initFreemarker() throws Exception {
      freemarkerConfig = new Configuration();
      freemarkerConfig.setDirectoryForTemplateLoading(new File("templates"));
      
      GenerateSquadJob.createPlayerFile(configuration.getPlayer1OverlayFilePath(), "1", "squadJob1");
      GenerateSquadJob.createPlayerFile(configuration.getPlayer2OverlayFilePath(), "2", "squadJob2");
   }


   public static Configuration getFreemarkerConfig() {
      return freemarkerConfig;
   }

   public static void setFreemarkerConfig(Configuration freemarkerConfig) {
      XStreamer.freemarkerConfig = freemarkerConfig;
   }
}
