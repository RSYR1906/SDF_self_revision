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
    }
}
