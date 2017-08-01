package init;


import objects.Object;

import java.util.Set;

import com.google.common.collect.Sets;

import items.Item;



public class Items {

	private static final Set<Item> CACHE;
    public static final Item BOIS;
    public static final Item FIL;
    public static final Item PLANCHE;
    public static final Item CAILLOU;
    public static final Item HERBE;

  
    private static Item getRegisteredItem(int id)
    {
    	Item obj = (Item) Item.REGISTRY.getItem(id);
    	 if (!CACHE.add(obj))
         {
             throw new IllegalStateException("Invalid Items requested: " + id);
         }
         else
         {
             return obj;
         }
    }

    static
    {  
    	CACHE = Sets.<Item>newHashSet();
        BOIS = getRegisteredItem(1);
        FIL = getRegisteredItem(2);
        PLANCHE = getRegisteredItem(3);
        CAILLOU = getRegisteredItem(4);
        HERBE = getRegisteredItem(5);
        CACHE.clear();
    }
	
}
