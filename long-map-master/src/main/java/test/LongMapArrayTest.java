package test;


import de.comparus.opensource.longmap.LongMapImplArray;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class LongMapArrayTest {

    @Test
    public void testClear() {
        final LongMapImplArray<String> map = new LongMapImplArray<>();

        for (long i = 0L; i < 30L; i++) {
            map.put(i, "qq" + i);
        }

        map.clear();

        final long[] expectedKeys = new long[0];
        final String[] expectedValues = new String[0];

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertTrue(Arrays.equals(expectedKeys, map.keys()));
        assertTrue(Arrays.equals(expectedValues, map.values()));
    }

    @Test
    public void testPutAndGet() {
        final LongMapImplArray<String> map = new LongMapImplArray<>();

        map.put(1L, "qq1L");
        map.put(12L, "qq12L");
        map.put(2L, "qq2L");

        final int expectedSize = 3;
        final long[] expectedKeys = new long[]{1L, 12L, 2L};
        final String[] expectedValues = new String[]{"qq1L", "qq12L", "qq2L"};

        assertEquals(expectedSize, map.size());
        assertTrue(Arrays.equals(expectedKeys, map.keys()));
        assertTrue(Arrays.equals(expectedValues, map.values()));

        assertEquals("qq1L", map.get(1L));
        assertEquals("qq12L", map.get(12L));
        assertEquals("qq2L", map.get(2L));
        
        assertNull(map.get(3L));
    }

    @Test
    public void testRemove() {
        final LongMapImplArray<String> map = new LongMapImplArray<>();

        map.put(1L, "qq1L");
        map.put(12L, "qq12L");
        map.put(2L, "qq2L");
        map.put(15L, "qq15L");

        final long sizeBefore = map.size();

        long[] expectedKeys;
        String[] expectedValues;

        map.remove(2L);
        expectedKeys = new long[]{1L, 12L, 15L};
        expectedValues = new String[]{"qq1L", "qq12L", "qq15L"};
        assertEquals(sizeBefore - 1, map.size());
        assertTrue(Arrays.equals(expectedKeys, map.keys()));
        assertTrue(Arrays.equals(expectedValues, map.values()));
        assertNull(map.get(2L));

        map.remove(1L);
        expectedKeys = new long[]{12L, 15L};
        expectedValues = new String[]{"qq12L", "qq15L"};
        assertEquals(sizeBefore - 2, map.size());
        assertTrue(Arrays.equals(expectedKeys, map.keys()));
        assertTrue(Arrays.equals(expectedValues, map.values()));
        assertNull(map.get(1L));

        map.remove(12L);
        expectedKeys = new long[]{15L};
        expectedValues = new String[]{"qq15L"};
        assertEquals(sizeBefore - 3, map.size());
        assertTrue(Arrays.equals(expectedKeys, map.keys()));
        assertTrue(Arrays.equals(expectedValues, map.values()));
        assertNull(map.get(12L));

        map.remove(12L);
        assertEquals(sizeBefore - 3, map.size());
        assertTrue(Arrays.equals(expectedKeys, map.keys()));
        assertTrue(Arrays.equals(expectedValues, map.values()));
        assertNull(map.get(12L));

        map.remove(15L);
        expectedKeys = new long[0];
        expectedValues = new String[0];
        assertEquals(0, map.size());
        assertTrue(Arrays.equals(expectedKeys, map.keys()));
        assertTrue(Arrays.equals(expectedValues, map.values()));

        assertNull(map.get(15L));
    }
}