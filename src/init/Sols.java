package init;




import java.util.Set;

import com.google.common.collect.Sets;

import biome.Biome;
import biome.Sol_extends;




public class Sols {

	private static final Set<Sol_extends> CACHE;
    public static final Sol_extends HERBE;
    public static final Sol_extends BASE;
    public static final Sol_extends PIERRE;
  
    private static Sol_extends getRegisteredSol(int id)
    {
    	Sol_extends obj = (Sol_extends) Sol_extends.REGISTRY.getSol(id);
    	 if (!CACHE.add(obj))
         {
             throw new IllegalStateException("Invalid Sol requested: " + id);
         }
         else
         {
             return obj;
         }
    }

    static
    {  
    	CACHE = Sets.<Sol_extends>newHashSet();
    	HERBE = getRegisteredSol(0);
    	BASE = getRegisteredSol(1);
    	PIERRE = getRegisteredSol(2);
        CACHE.clear();
    }
	
}
