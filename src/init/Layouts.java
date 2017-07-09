package init;




import java.util.Set;

import com.google.common.collect.Sets;

import biome.Biome;
import layout.Layout;




public class Layouts {

	private static final Set<Layout> CACHE;
    public static final Layout SAC;
  
    private static Layout getRegisteredLayout(int id)
    {
    	Layout obj = (Layout) Layout.REGISTRY.getLayout(id);
    	 if (!CACHE.add(obj))
         {
             throw new IllegalStateException("Invalid Layout requested: " + id);
         }
         else
         {
             return obj;
         }
    }

    static
    {  
    	CACHE = Sets.<Layout>newHashSet();
    	SAC = getRegisteredLayout(1);
        CACHE.clear();
    }
	
}
