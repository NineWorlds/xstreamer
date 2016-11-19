package us.nineworlds.xstreamer.model;

import java.util.List;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.github.xws.Pilot;
import com.github.xws.Upgrades;
import com.google.common.collect.Iterables;

public class PilotTreeNode extends DefaultMutableTreeNode {

   private static final long serialVersionUID = -9033190175203649457L;
   private Pilot pilot;
   private Upgrades upgrades;
   
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
   
   @Override
public String toString() {
     StringBuilder stringBuilder = new StringBuilder();
     stringBuilder.append(pilot.getName());
     stringBuilder.append(" (");
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
