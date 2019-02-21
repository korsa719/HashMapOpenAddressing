import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class HashMapSimpleTest {


  private final MyMap map = new MyMap();

  @Before
  public void before() {
    map.put(2, 3000);
    map.put(3, 2000);
    map.put(4, 1000);
  }

  @Test
  public void testPut() {
    map.put(1, 4000);
    assertEquals(4, map.size());
    assertEquals(map.get(1), 4000L, 0);
    assertNotNull(map.get(1));
  }

  @Test
  public void testGetShouldFailWithInvalidKey() {
    assertNull(map.get(111));
  }

  @Test
  public void testKeyValueThresholds() {
    MyMap myMap = new MyMap();
    myMap.put(Integer.MAX_VALUE, Long.MAX_VALUE);
    assertEquals(Long.MAX_VALUE, myMap.get(Integer.MAX_VALUE), 0);
    myMap.put(Integer.MIN_VALUE, Long.MIN_VALUE);
    assertEquals(Long.MIN_VALUE, myMap.get(Integer.MIN_VALUE), 0);
  }

  @Test
  public void testSizeWhenElementsAdded() {
    final MyMap hashMap = new MyMap();
    hashMap.put(0, 0);
    hashMap.put(1, 1);
    hashMap.put(2, 2);
    hashMap.put(3, 3);

    assertEquals(4, hashMap.size());
  }

  @Test
  public void testSizeWhenDuplicatedKeysAdded() {
    final MyMap hashMap = new MyMap();
    hashMap.put(0, 0);
    hashMap.put(1, 1);
    hashMap.put(1, 2);
    hashMap.put(3, 3);
    hashMap.put(3, 7);
    Assert.assertEquals(3, hashMap.size());
  }

  @Test
  public void testSizeMethodWithRandomInt() {
    final MyMap map = new MyMap();
    //The size is not specified because the JDK hashmap expands automatically.
    final HashMap<Integer, Long> mapJDK = new HashMap<>();
    Random random = new Random();
    for (int i = 0; i < 997 * 0.5; i++) {
      int k = random.nextInt(1000000);
      long v = (long) random.nextInt(100);
      map.put(k, v);
      mapJDK.put(k, v);
    }
    assertEquals(map.size(), mapJDK.size());
  }

  @Test
  public void testGetMethodWithDifferentKeysAndValues() {
    MyMap map = new MyMap();
    map.put(1, 10L);
    map.put(2, 11L);
    map.put(3, 12L);

    long v1 = map.get(1);
    long v2 = map.get(2);
    long v3 = map.get(3);

    assertEquals("get() should return value 10 with key 1", 10L, v1);
    assertEquals("get() should return value 11 with key 2", 11L, v2);
    assertEquals("get() should return value 12 with key 3", 12L, v3);

    assertEquals("Should be 3 elements in map", 3, map.size());
  }

}
