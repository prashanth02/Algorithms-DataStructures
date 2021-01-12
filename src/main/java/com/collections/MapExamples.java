package com.collections;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class MapExamples {
    /*
    TODO : Map reduce

    Map & SortedMap(extends Map) are 2 interfaces
    SortedMap is implemented by TreeMap for ordering

    Map is implemented by HashMap etc. LinkedHashMap extends HashMap.

    AbstractMap, ConcurrentHashMap, ConcurrentSkipListMap, EnumMap, HashMap, Hashtable,
    IdentityHashMap, LinkedHashMap, TreeMap, WeakHashMap etc. are major implementation of Maps.

    ConcurrentHashMap uses multiple buckets to store data.
    This avoids read locks and greatly improves performance over a HashTable.
    Both are thread safe, but there are obvious performance wins with ConcurrentHashMap.

    When you read from a ConcurrentHashMap using get(), there are no locks,
    contrary to the HashTable for which all operations are simply synchronized.

    HashMap is the best thing to use in a single threaded application.

    Hashtable is belongs to the Collection framework; ConcurrentHashMap belongs to the Executor framework.
    Hashtable uses single lock for whole data. ConcurrentHashMap uses multiple locks on segment level (16 by default) instead of object level i.e. whole Map.
    ConcurrentHashMap locking is applied only for updates. In case of retrievals, it allows full concurrency, retrievals reflect the results of the most recently completed update operations. So reads can happen very fast while writes are done with a lock.
    ConcurrentHashMap doesn't throw a ConcurrentModificationException if one thread tries to modify it while another is iterating over it and does not allow null values.
    ConcurrentHashMap returns Iterator, which fails-safe (i.e. iterator will make a copy of the internal data structure) on concurrent modification.
    ConcurrentHashMap uses a database shards logic (Segment<K, V>[] segments) is known as Concurrency-Level, i.e. divides the data into shards(segments) than puts locks on each shard (segment) instead of putting a single lock for whole data (Map). The default value is 16.
    */

    /*
    ConcurrentHashMap is used to store DBConnections in ERS
     */
    public static void main(String args[]) {
        Map map = concurrentHashMapTest();
        hashMapIterationsTest(map);
    }

    private static void hashMapIterationsTest(Map<String, String> map) {
        //iterate through a Map using the EntrySet
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        //using iterator
        Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        //Lambdas
        map.forEach((k, v) -> System.out.println((k + ":" + v)));
        //Using streams
        map.entrySet().stream().forEach(e -> System.out.println((e.getKey() + ":" + e.getValue())));
    }

    private static Map concurrentHashMapTest() {
        List<String> stringList = new ArrayList<>();

        //10 million, 10 to the power 7 we can find the difference in the total count if just HashMap is used.
        for(int i=0; i<10; i++) {
            stringList.add(""+i);
        }

        Map<String, String> stringConcurrentHashMap = new ConcurrentHashMap<>();
        stringList.parallelStream().forEach(e -> {
//            System.out.println("Thread name = " + Thread.currentThread().getName());
            stringConcurrentHashMap.put(e, e);
        });

        System.out.println("Map size = " + stringConcurrentHashMap.size());

        return stringConcurrentHashMap;
    }
}
