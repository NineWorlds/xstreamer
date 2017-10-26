package us.nineworlds.xstreamer.model.lookup;

import java.util.List;

import com.github.guidokessels.ships.Upgrades;

public class UpgradeLookup {
   
   List<Upgrades> upgrades;
   private static UpgradeLookup instance;
   
   public static UpgradeLookup newInstance(List<Upgrades> upgrades) {
      return instance = new UpgradeLookup(upgrades);
   }
   
   public static UpgradeLookup getInstance() {
      return instance;
   }

   private UpgradeLookup(List<Upgrades> upgrades) {
      this.upgrades = upgrades;
   }
   
   public Upgrades findUpgrade(String xwsname) {
      for(Upgrades upgrade : upgrades) {
         if (upgrade.getXws().equals(xwsname)) {
            return upgrade;
         }
      }
      return null;
   }
   
   public List<Upgrades> getUpgrades() {
	   return upgrades;
   }

}
