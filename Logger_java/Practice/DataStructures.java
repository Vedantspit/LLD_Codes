import java.util.*;
import java.time.Instant;

public class DataStructures {
    public static void main(String[] args) {

        // ===== LIST =====
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(10);

        System.out.println("ArrayList: " + list);
        System.out.println("Time: " + Instant.now());

        // ===== SET =====
        Set<Integer> set = new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(10);
        System.out.println("HashSet: " + set);

        // ===== ORDERED SET =====
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(20);
        System.out.println("TreeSet: " + treeSet);

        // ===== MAP =====
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("A", 99);
        System.out.println("HashMap: " + map);

        // ===== ORDERED MAP =====
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("C", 3);
        treeMap.put("A", 1);
        treeMap.put("B", 2);
        System.out.println("TreeMap: " + treeMap);

        // ===== QUEUE =====
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println("Queue poll: " + queue.poll());
        System.out.println("Queue now: " + queue);

        // ===== PRIORITY QUEUE (MIN HEAP) =====
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(30);
        pq.add(10);
        pq.add(20);
        System.out.println("PriorityQueue poll: " + pq.poll());
        System.out.println("PriorityQueue now: " + pq);
    }
}
