package us.nineworlds.xstreamer.jobs;

import static org.quartz.JobBuilder.newJob;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

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
      String player1Template = us.nineworlds.xstreamer.Configuration.getInstance().player1SquadTemplateFilePath();
      String player2Template = us.nineworlds.xstreamer.Configuration.getInstance().player2SquadTemplateFilePath();
      String filename = jobDataMap.getString("filename");
      String playerNumber = jobDataMap.getString("player");
      XwsSpec squad = null;
      Template squadTemplate;
      Configuration config = XStreamer.getFreemarkerConfig();
      
      
      Writer player1SquadFile = null;
      try {
         if (playerNumber.equals("1")) {
            squadTemplate = config.getTemplate(player1Template);
            squad = XStreamer.getPlayer1();
         } else {
            squadTemplate = config.getTemplate(player2Template);
            squad = XStreamer.getPlayer2();
         }
         player1SquadFile = new FileWriter(new File(filename));

         Map<String, Object> input = new HashMap<>();

         input.put("xwsspec", squad);

         squadTemplate.process(input, player1SquadFile);
      } catch (Exception e) {
         e.printStackTrace();
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
   
   public static void createPlayerFile(String filename, String playerNumber, String jobName) {
      JobDetail jobDetail = newJob(GenerateSquadJob.class)
            .withIdentity(jobName)
            .usingJobData("filename", filename)
            .usingJobData("player", playerNumber)
            .build();
      Trigger trigger = TriggerBuilder.newTrigger().startNow().withIdentity(jobName).build();
      
      try {
         XStreamer.getScheduler().scheduleJob(jobDetail, trigger);
      } catch (SchedulerException e) {
         System.out.println("Error scheduling job");
      }
   }

   
   
}
