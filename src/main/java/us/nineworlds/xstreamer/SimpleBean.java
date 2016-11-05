/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package us.nineworlds.xstreamer;

/**
 *
 * @author sorrentino
 */
public class SimpleBean {
   private String name;
   private int age;
   private String field3;
   private String field4;

   public SimpleBean(String name, int age) {
      this.name = name;
      this.age = age;
      field3 = "<value field3>";
      field4 = "<value field4>";
   }

   @Override
   public String toString() {
      return new StringBuilder().append("name=").append(name).append(" age=").append(age).toString();
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public final String getField3() {
      return field3;
   }

   public final void setField3(String field3) {
      this.field3 = field3;
   }

   public final String getField4() {
      return field4;
   }

   public final void setField4(String field4) {
      this.field4 = field4;
   }

}