package init;


import objects.Object;

import java.util.Set;

import com.google.common.collect.Sets;



public class Objects {

	private static final Set<Object> CACHE;
    public static final Object PIERRE;
    public static final Object ARBRE;
    public static final Object CHEST;
    public static final Object SCIRIE;
    

  
    private static Object getRegisteredBlock(int id)
    {
    	Object obj = (Object) Object.REGISTRY.getObject(id);
    	 if (!CACHE.add(obj))
         {
             throw new IllegalStateException("Invalid Block requested: " + id);
         }
         else
         {
             return obj;
         }
    }

    static
    {  
    	CACHE = Sets.<Object>newHashSet();
        PIERRE = getRegisteredBlock(1);
        ARBRE = getRegisteredBlock(2);
        CHEST = getRegisteredBlock(3);
        SCIRIE = getRegisteredBlock(4);
        CACHE.clear();
    }
	
}
