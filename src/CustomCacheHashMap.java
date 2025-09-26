import java.util.*;

public class CustomCacheHashMap
{
    private final Map<Integer,String> map;
    public CustomCacheHashMap(int capacity)
    {

        map = new HashMap<>(capacity);
    }
    public void put(int k, String v)
    {
        map.put(k,v); //adding the data
    }
    public String get(int k)
    {
        return map.get(k); //retrieve any value
    }
    public boolean contains(int k)
    {
        return map.containsKey(k);
    }
    public int size()
    {
        return map.size();
    }

    public static void main(String[] args)
    {
        CustomCacheHashMap cache = new CustomCacheHashMap(3000);
        for(int i=0;i<2000;i++)
            cache.put(i, "value-"+i);
        System.out.println("Size after adding 2000 entries: " + cache.size());
        System.out.println("Random get(10): " + cache.get(10));
    }
}