package us.nineworlds.xstreamer.ia.lookup;

import java.util.List;

import us.nineworlds.xstreamer.ia.model.maps.MapMetaData;
import us.nineworlds.xstreamer.ia.model.maps.Maps;

public class SkirmishMapsLookup {
   
   private static SkirmishMapsLookup instance;
   
   private List<MapMetaData> mapsMetaData;
   
   public static SkirmishMapsLookup newInstance(Maps commandCardDB) {
      instance = new SkirmishMapsLookup(commandCardDB);
      return instance;
   }
   
   public static SkirmishMapsLookup getInstance() {
      return instance;
   }

   private SkirmishMapsLookup(Maps maps) {
      this.mapsMetaData = maps.getMaps();
   }
   
   public MapMetaData findMap(String mapName) {
      for(MapMetaData map : mapsMetaData) {
    	  if (map.getName().equals(mapName)) {
    		  return map;
    	  }
      }
      return null;
   }
   
   public List<MapMetaData> getMaps() {
	   return mapsMetaData;
   }
}
