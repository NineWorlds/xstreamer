package us.nineworlds.xstreamer;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TreeSelectionEvent;

import org.jdesktop.application.Action;
import org.jdesktop.observablecollections.ObservableCollections;

public class HelloWorldJFrame extends JFrame {

   public JButton btn;
   public JTextArea ta;
   public Container panel_dlg;
   public ActionMap actionMap;
   public JLabel statusbar;
   public JTextField tv;
   public JTable testTable;

   boolean connected = false;

   private String testValue = "TEST";

   List<SimpleBean> myData = ObservableCollections.observableList(new ArrayList<SimpleBean>());

   public Dimension getFrameSize() {
      return new Dimension(100, 600);
   }

   @Action
   public void comboAction() {

      System.out.println("comboAction");
   }

   @Action
   public void listAction() {

      System.out.println("listAction");
   }

   @Action(enabledProperty = "connected")
   public void test() {
      System.out.printf("hello world! %s\n", testValue);
      setTestValue("hello world!\n");
      firePropertyChange("testValue", null, "hello world!");

   }

   @Action
   public void selectNode(ActionEvent e) {

      TreeSelectionEvent ev = (TreeSelectionEvent) e.getSource();

      System.out.printf("selectNode path=%s\n", ev.getPath().toString());

      myData.add(new SimpleBean(ev.getPath().toString(), 0));

   }

   @Action
   public void selectRow(ActionEvent e) {

      ListSelectionEvent ev = (ListSelectionEvent) e.getSource();

      System.out.printf("selectRow firstIndex=%d lastIndex=%d valueIsAdjusting=%b\n", ev.getFirstIndex(),
            ev.getLastIndex(), ev.getValueIsAdjusting());

   }

   @Action
   public void activateRow(ActionEvent e) {

      System.out.printf("activate row [%d]\n", testTable.getSelectedRow());

   }

   @Action()
   public void show(ActionEvent e) {
      ta.setText("X:" + btn.getClientProperty("X") + "\n" + "Y:" + btn.getClientProperty("Y"));
      setConnected(true);

      TestDialog.showDialog(this);
   }

   public boolean isConnected() {
      return connected;
   }

   public void setConnected(boolean connected) {
      this.connected = connected;
      firePropertyChange("connected", null, connected);
   }

   public String getTestValue() {
      return testValue;
   }

   public void setTestValue(String testValue) {
      this.testValue = testValue;
      System.out.printf("updated %s\n", testValue);
   }

   protected void progressMessage(String message) {
      statusbar.setText(message);
   }

}
