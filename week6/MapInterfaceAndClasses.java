
/*
In Java, the Map Interface is part of the java.util package and represents a collection of key-value pairs, where Keys should be unique, but values can be duplicated.

It provides efficient retrieval, insertion, and deletion operations based on keys.
Keys should be unique, but values can be duplicated.
HashMap and LinkedHashMap allow one null key, and TreeMap does NOT allow null keys (if natural ordering is used).
Use ConcurrentHashMap for thread-safe operations, or Collections.synchronizedMap() to make an existing map synchronized.
Implemented Classes of Map Interface
HashMap: Stores key-value pairs using hashing for fast access, insertion, and deletion.
LinkedHashMap: Similar to HashMap but maintains the insertion order of keys.
TreeMap: Stores key-value pairs in sorted order using natural ordering or a custom comparator.
Hashtable: A synchronized Map implementation that doesn’t allow null keys or values. 
*****methods implemented by the map interface
clear()	This method is used in Java Map Interface to clear and remove all of the elements or mappings from a specified Map collection.
containsKey(Object)	Checks if a key exists in the map.
containsValue(Object)	Checks if a value exists in the map.
entrySet()	Returns a set view of the map’s key-value pairs.
equals(Object)	Compares two maps for equality.
get(Object)	Returns the value for the given key, or null if not found.
hashCode()	This method is used in Map Interface to generate a hashCode for the given map containing keys and values.
isEmpty()	This method is used to check if a map is having any entry for key and value pairs. If no mapping exists, then this returns true.
keySet()	Returns a set view of the keys in the map.
put(Object, Object)	This method is used in Java Map Interface to associate the specified value with the specified key in this map.
putAll(Map)	This method is used in Map Interface in Java to copy all of the mappings from the specified map to this map.
remove(Object)	This method is used in Map Interface to remove the mapping for a key from this map if it is present in the map.
size()	This method is used to return the number of key/value pairs available in the map.
values()	Returns a collection view of the map’s values.
getOrDefault(Object key, V defaultValue)	Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.
merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)	If the specified key is not already associated with a value or is associated with null, associate it with the given non-null value.
putIfAbsent(K key, V value)	Adds a mapping only if the key is not already mapped.
*/
import java.util.*;
import java.lang.*;

class MapInterfaceAndClasses {
    public static void main(String args[]) {

        // Initialization of a Map using Generics
        Map<Integer, String> hm1 = new HashMap<Integer, String>();

        // Inserting the Elements
        hm1.put(new Integer(1), "Geeks");
        hm1.put(new Integer(2), "For");
        hm1.put(new Integer(3), "Geeks");

        for (Map.Entry mapElement : hm1.entrySet()) {
            int key = (int) mapElement.getKey();

            // Finding the value
            String value = (String) mapElement.getValue();

            System.out.println(key + " : " + value);
        }
    }
}
