package us.nineworlds.xstreamer.jobs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.github.xws.XwsSpec;

import freemarker.template.Configuration;
import freemarker.template.Template;
import us.nineworlds.xstreamer.XStreamer;

public class GenerateSquadJob implements Job {

   public GenerateSquadJob() {
   }

   @Override
   public void execute(JobExecutionContext context) throws JobExecutionException {

      JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
      String filename = jobDataMap.getString("filename");
      String playerNumber = jobDataMap.getString("player");
      XwsSpec squad = null;
      
      if (playerNumber.equals("1")) {
         squad = XStreamer.getPlayer1();
      } else {
         squad = XStreamer.getPlayer2();
      }
      
      Configuration config = XStreamer.getFreemarkerConfig();
      Writer player1SquadFile = null;
      try {
         player1SquadFile = new FileWriter(new File(filename));
         Template squadTemplate = config.getTemplate("squadOverlay.ftl");

         Map<String, Object> input = new HashMap<>();

         input.put("xwsspec", squad);

         squadTemplate.process(input, player1SquadFile);
      } catch (Exception e) {
         throw new JobExecutionException(e);
      } finally {
         if (player1SquadFile != null) {
            try {
               player1SquadFile.close();
            } catch (IOException e) {
               throw new JobExecutionException(e);
            }    
         }
      }
   }
}
