package us.nineworlds.xstreamer.model;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import com.github.xws.XwsSpec;

public class XWSquadTreeNode extends DefaultMutableTreeNode {
   
   private static final long serialVersionUID = 1L;
   private XwsSpec playerModel;  
   
   public XWSquadTreeNode(XwsSpec userObject, boolean allowsChildren) {
      super(userObject, allowsChildren);
      playerModel = userObject;
   }

   public XWSquadTreeNode(XwsSpec userObject) {
      super(userObject);
      playerModel = userObject;
   }
   
   @Override
public String toString() {
      return playerModel.getName();
   }

   @Override
   public int getChildCount() {
      return playerModel.getPilots().size();
   }
   
   @Override
   public TreeNode getChildAt(int index) {
      PilotTreeNode pilotTreeNode = new PilotTreeNode(playerModel.getPilots().get(index));
      return pilotTreeNode;
   }
   
}
