package extra_exercises;

import java.util.*;

public class HashMaps {

    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>(); // instantiate a new Hashmap

        map.put("Singapore", 6); // add key-value mapping into map
        map.put("China", 1600);
        map.put("India", 1500);

        map.putIfAbsent("Malaysia", 100); // check if key is present, if absent then add into map
        map.replace("Singapore", 7); // change the value mapped to a certain key

        System.out.println(map);

        System.out.println(map.keySet()); // print out all the keys

        System.out.println(map.values()); // print out all the values

        System.out.println(map.containsKey("Malaysia")); // check if Malaysia is a key in the Hashmap and return a
                                                         // boolean

        map.clear(); // to remove all mappings in the map

        System.out.println(map);

        Map<Integer, String> mapNew = new HashMap<>();
        mapNew.put(1, "One");
        mapNew.put(2, "Two");
        mapNew.put(3, "Three");

        System.out.println(mapNew);

        Map<String, Integer> reverseMapNew = new HashMap<>();
        reverseMapNew = reverse(mapNew);

        System.out.println(reverseMapNew);
    }

    public static Map<String, Integer> reverse(Map<Integer, String> map1) { // method to reverse key-value mapping from
                                                                            // one map to another map
        Map<String, Integer> map2 = new HashMap<String, Integer>();

        for (int a : map1.keySet()) {
            map2.put(map1.get(a), a);
        }
        return map2;

    }
}
