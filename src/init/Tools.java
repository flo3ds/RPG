package init;

import java.util.Set;
import com.google.common.collect.Sets;

import items.Item;
import tool.Tool;

public class Tools {

	private static final Set<Tool> CACHE;
    public static final Tool AXE;

  
    private static Tool getRegisteredTool(int id)
    {
    	Tool obj = (Tool) Tool.REGISTRY.getTool(id);
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
    	CACHE = Sets.<Tool>newHashSet();
    	AXE = getRegisteredTool(1);
        CACHE.clear();
    }
	
}
