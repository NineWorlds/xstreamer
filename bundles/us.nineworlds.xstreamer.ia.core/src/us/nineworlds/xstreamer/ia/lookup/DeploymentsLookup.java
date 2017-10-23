package us.nineworlds.xstreamer.ia.lookup;

import java.util.List;


import us.nineworlds.iadata.DeploymentsDB;
import us.nineworlds.iadata.deployment.Deployment;
import us.nineworlds.iadata.deployment.Deployments;

public class DeploymentsLookup {
   
   private static DeploymentsLookup instance;
   
   private List<Deployments> deployments;
   
   public static DeploymentsLookup newInstance(DeploymentsDB deploymentsDB) {
      return instance = new DeploymentsLookup(deploymentsDB);
   }
   
   public static DeploymentsLookup getInstance() {
      return instance;
   }

   private DeploymentsLookup(DeploymentsDB deploymentsDB) {
      this.deployments = deploymentsDB.getDeployments();
   }
   
   public Deployment findDeploymentCard(String iaspecname, String faction) {
      for( Deployments deployment : deployments) {
    	 Deployment deploymentCard = deployment.getDeployment(); 
         if (deploymentCard.getIaspecname().equals(iaspecname) && deploymentCard.getFaction().toString().equals(faction)) {
            return deploymentCard;
         }
      }
      return null;
   }
   
   public List<Deployments> getDeployments() {
	   return deployments;
   }

}
