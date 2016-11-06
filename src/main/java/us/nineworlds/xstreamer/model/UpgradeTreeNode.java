package us.nineworlds.xstreamer.model;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class UpgradeTreeNode extends DefaultMutableTreeNode {
   
   private static final long serialVersionUID = 1L;
   String upgradeSlot;
   List<String> upgradeOptions;
   
   public UpgradeTreeNode(String upgradeSlot, List<String> upgrades) {
      this.upgradeSlot = upgradeSlot;
      this.upgradeOptions = upgrades;
   }
   
   public String toString() {
      StringBuilder stringBuilder = new StringBuilder("Upgrades: ");
      for(String options : upgradeOptions) {
         stringBuilder.append(options);
         stringBuilder.append(" ");
      }
      return stringBuilder.toString();
   }

}
