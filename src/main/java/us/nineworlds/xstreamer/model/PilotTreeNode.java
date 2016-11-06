package us.nineworlds.xstreamer.model;

import java.awt.image.PixelInterleavedSampleModel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.github.xws.Pilot;
import com.github.xws.Upgrades;
import com.google.common.collect.Iterables;
import com.mchange.v2.c3p0.impl.NewPooledConnection;

public class PilotTreeNode extends DefaultMutableTreeNode {

   Pilot pilot;
   Upgrades upgrades;
   
   public PilotTreeNode(Pilot userObject, boolean allowsChildren) {
      super(userObject, allowsChildren);
      pilot = userObject;
      upgrades = pilot.getUpgrades();
   }

   public PilotTreeNode(Pilot userObject) {
      super(userObject);
      pilot = userObject;
      upgrades = pilot.getUpgrades();
   }
   
   public String toString() {
     StringBuilder stringBuilder = new StringBuilder();
     stringBuilder.append(pilot.getName());
     stringBuilder.append("-");
     stringBuilder.append(pilot.getShip());
     stringBuilder.append("(");
     stringBuilder.append(pilot.getPoints());
     stringBuilder.append(")");
     return stringBuilder.toString();
   }
   
   @Override
   public int getChildCount() {
      if (upgrades != null) {
         return upgrades.getAdditionalProperties().size();
      }      
      return 0;
   }
   
   @Override
   public TreeNode getChildAt(int index) {
      Entry<String, Object> entry = Iterables.get(upgrades.getAdditionalProperties().entrySet(), index);
      UpgradeTreeNode upgradesTreeNode = new UpgradeTreeNode(entry.getKey(), (List<String>)entry.getValue());
    
      return upgradesTreeNode;
   }

}
