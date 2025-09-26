import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class CombinedOOMDemo {

    // Static map to simulate memory leak
    static Map<Integer, String> cache = new HashMap<>();

    public static void main(String[] args) {
        // Run OOM and Prevention versions
        runDesignIssueOOM();
        runDesignIssuePrevention();

        runMemoryLeakOOM();
        runMemoryLeakPrevention();
    }

    // ==================== CASE 1: DESIGN ISSUE ====================

    // ❌ Wrong Design: Loads too much data at once
     private static void runDesignIssueOOM() {
        List<int[]> list = new ArrayList<>();
        try {
            System.out.println("\n---- Running DESIGN ISSUE OOM ----");
            while (true) {
                list.add(new int[1000000]); // keeps adding large chunks
                System.out.println("List size: " + list.size());
            }
        } catch (OutOfMemoryError e) {
            System.err.println("OutOfMemoryError occurred due to DESIGN ISSUE!");
        }
    }



    // ✅ Prevention: Use limit (pagination / streaming approach)
   private static void runDesignIssuePrevention() {
        System.out.println("\n---- Running DESIGN ISSUE PREVENTION ----");
        for (int i = 0; i < 10; i++) { // limit to 10 instead of infinite
            int[] data = new int[1000000];
            System.out.println("Loaded chunk " + (i + 1));
            // process data here, then let it go out of scope → GC cleans it
        }
        System.out.println("Handled data in chunks, no OOM!");
    }

    // ==================== CASE 2: MEMORY LEAK ====================

    // ❌ Memory Leak: Data kept forever in static map
     private static void runMemoryLeakOOM() {
        try {
            System.out.println("\n---- Running MEMORY LEAK OOM ----");
            for (int i = 0; i < 10000000; i++) {
                cache.put(i, "Data-" + i); // never removed → leak
            }
        } catch (OutOfMemoryError e) {
            System.err.println("OutOfMemoryError occurred due to MEMORY LEAK!");
        }
    }

    // ✅ Prevention: Use WeakHashMap (auto removes unused keys)
   private static void runMemoryLeakPrevention() {
        System.out.println("\n---- Running MEMORY LEAK PREVENTION ----");
        Map<Integer, String> safeCache = new WeakHashMap<>();
        for (int i = 0; i < 1000; i++) {
            safeCache.put(i, "Data-" + i);
        }
        System.out.println("Safe cache size: " + safeCache.size());
        System.out.println("WeakHashMap allows GC to clean unused entries!");
    }

}
