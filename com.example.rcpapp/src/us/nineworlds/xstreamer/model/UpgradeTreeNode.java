package us.nineworlds.xstreamer.model;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class UpgradeTreeNode extends DefaultMutableTreeNode {
   
   private static final long serialVersionUID = 1L;
   String upgradeSlot;
   List<String> upgradeOptions;
   
   public UpgradeTreeNode(String upgradeSlot, List<String> upgrades) {
      this.upgradeSlot = upgradeSlot;
      this.upgradeOptions = upgrades;
   }
   
   @Override
public String toString() {
      return upgradeSlot;
   }
   
   @Override
   public int getChildCount() {
      if (upgradeOptions == null || upgradeOptions.isEmpty()) {
         return 0;
      }
      
      return upgradeOptions.size();
   }
   
   @Override
   public TreeNode getChildAt(int index) {
      UpgradeTypeTreeNode type = new UpgradeTypeTreeNode(upgradeOptions.get(index));
      return type;
   }
   
}
