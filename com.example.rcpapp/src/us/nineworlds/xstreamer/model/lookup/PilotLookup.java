package us.nineworlds.xstreamer.model.lookup;

import java.io.File;

import org.apache.commons.configuration.PropertiesConfiguration;

public class PilotLookup {
   
   private static PilotLookup instance; 
   private PropertiesConfiguration configuration;
   
   public static PilotLookup getInstance() {
      if (instance == null) {
         instance = new PilotLookup();
      }
      return instance;
   }
     
   private PilotLookup() {
      initPilots();
   }
   
   private void initPilots() {
      try {
         configuration = new PropertiesConfiguration(this.getClass().getResource("/xws-data/pilots.properties"));
      } catch (Exception ex) {
         
      }
      
   }
   
   public String lookupPilot(String value) {
      if (configuration == null) {
         return value;
      }
      
      String pilot = configuration.getString(value);
      if (pilot == null) {
         pilot = value;
      }
      return pilot;
   }

}
