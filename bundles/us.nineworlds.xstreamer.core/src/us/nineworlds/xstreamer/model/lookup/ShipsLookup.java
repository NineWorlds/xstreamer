package us.nineworlds.xstreamer.model.lookup;

import java.util.List;

import com.github.guidokessels.ships.Ship;

public class ShipsLookup {
   
   List<Ship> ships;
   private static ShipsLookup instance;
   
   public static ShipsLookup newInstance(List<Ship> ships) {
      return instance = new ShipsLookup(ships);
   }
   
   public static ShipsLookup getInstance() {
      return instance;
   }

   private ShipsLookup(List<Ship> ships) {
      this.ships = ships;
   }
   
   public Ship findShip(String xwsname) {
      for(Ship ship : ships) {
         if (ship.getXws().equals(xwsname)) {
            return ship;
         }
      }
      return null;
   }
   
   public List<Ship> getShips() {
	   return ships;
   }

}
