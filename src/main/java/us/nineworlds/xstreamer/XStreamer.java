package us.nineworlds.xstreamer;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.joda.time.Period;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.swixml.jsr296.SwingApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xws.XwsSpec;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class XStreamer extends SwingApplication {

   private static XwsSpec player1;   
   private static XwsSpec player2;
   private static Scheduler scheduler;
   private static long countDownTime;
   private static Configuration freemarkerConfig;

   public static void main(String[] args) throws Exception {
      scheduler = StdSchedulerFactory.getDefaultScheduler();
      scheduler.start();
      ObjectMapper mapper = new ObjectMapper();
      player1 = mapper.readValue(new File("player1.json"), XwsSpec.class);
      player2 = mapper.readValue(new File("player2.json"), XwsSpec.class);
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
      Template squadTemplate = freemarkerConfig.getTemplate("squadOverlay.ftl");
      Map<String, Object> input = new HashMap<>();
      input.put("xwsspec", player1);
      
      Writer player1SquadFile = new FileWriter(new File("player1.html"));
      try {
         squadTemplate.process(input, player1SquadFile);
      } finally {
         player1SquadFile.close();
      }
   }

   public static Configuration getFreemarkerConfig() {
      return freemarkerConfig;
   }

   public static void setFreemarkerConfig(Configuration freemarkerConfig) {
      XStreamer.freemarkerConfig = freemarkerConfig;
   }
   
}
