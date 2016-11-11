package us.nineworlds.xstreamer.model;

import javax.swing.tree.DefaultMutableTreeNode;

public class UpgradeTypeTreeNode extends DefaultMutableTreeNode {


   private static final long serialVersionUID = 1L;
   String upgradeName;
   
   public UpgradeTypeTreeNode(String upgrade) {
      upgradeName = upgrade;
      setAllowsChildren(false);
   }
   
   @Override
public String toString() {
      return upgradeName;
   }
   
   @Override
   public int getChildCount() {
      return 0;
   }
   
   
}
