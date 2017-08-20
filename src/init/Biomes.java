package init;




import java.util.Set;

import com.google.common.collect.Sets;

import biome.Biome;




public class Biomes {

	private static final Set<Biome> CACHE;
    public static final Biome FORET;
    public static final Biome BASE;
  
    private static Biome getRegisteredBiome(int id)
    {
    	Biome obj = (Biome) Biome.REGISTRY.getBiome(id);
    	 if (!CACHE.add(obj))
         {
             throw new IllegalStateException("Invalid Biome requested: " + id);
         }
         else
         {
             return obj;
         }
    }

    static
    {  
    	CACHE = Sets.<Biome>newHashSet();
        FORET = getRegisteredBiome(0);
        BASE = getRegisteredBiome(1);
        CACHE.clear();
    }
	
}
