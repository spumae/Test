package test;

import de.comparus.opensource.longmap.LongMapImplArray;
import de.comparus.opensource.longmap.LongMapImplArrayList;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapEfficiencyTest {
    private static final int COUNT = 50000;
    private static final String VALUE = "test_string";

    @Test
    public void test_array() {
        long t0 = System.currentTimeMillis();
        LongMapImplArray<String> map = new LongMapImplArray<>();
        for (long i = 0L; i < COUNT; i++) {
            map.put(i, "qq");
        }
        long t1 = System.currentTimeMillis();
        System.out.println("LongMapImplArray " + (t1 - t0) + "ms, memory:" + ObjectSizeCalculator.getObjectSize(map) + " bytes");
    }

    @Test
    public void test_arrayList() {
        long t0 = System.currentTimeMillis();
        LongMapImplArrayList<String> map = new LongMapImplArrayList<>();
        for (long i = 0L; i < COUNT; i++) {
            map.put(i, "qq");
        }
        long t1 = System.currentTimeMillis();
        System.out.println("LongMapImplArrayList " + (t1 - t0) + "ms, memory:" + ObjectSizeCalculator.getObjectSize(map) + " bytes");
    }

    @Test
    public void test_hashMap() {
        long t0 = System.currentTimeMillis();
        Map<Long, String> map = new HashMap<>();
        for (long i = 0L; i < COUNT; i++) {
            map.put(i, "qq" + i);
        }
        long t1 = System.currentTimeMillis();
        System.out.println("HashMap " + (t1 - t0) + "ms, memory:" + ObjectSizeCalculator.getObjectSize(map) + " bytes");
    }
}
