import static java.util.Objects.hash;

/**
 * Напишите свою, простую, эффективную реализацию алгоритма Hash Map с открытой адресацией, для ключей типа int и
 * значений типа long.
 * Реализуйте операции put, get, size (remove не требуется, поддержка стандартного интерфейса Map не требуется).
 * Продемонстрируйте работоспособность реализации с помощью соответствующих тестов.
 */
public class MyMap {
  private static final int DEFAULT_CAPACITY = 16;
  private int capacity;
  private int size;

  private Entry[] entries;

  /**
   * Constructs an empty hashMap with the default initial capacity
   * (16) and the default load factor (0.5).
   */
  public MyMap() {
    capacity = DEFAULT_CAPACITY;
    entries = new Entry[capacity];
  }

  public MyMap(int inputSize) {
    capacity = inputSize;
    entries = new Entry[capacity];
  }

  /**
   * Returns the number of key-value mappings in this map.
   *
   * @return the number of key-value mappings in this map
   */
  public int size() {
    return size;
  }


  public void put(int key, long val) {
    if (size >= capacity / 2) resize(2 * capacity);
    add(key, val);
  }

  /**
   * Returns the value to which the specified key is mapped,
   * or throws NoSuchElementException if this map contains no mapping for the key.
   *
   * @param key key with which the specified value is to be associated
   * @return value to be associated with the specified key
   */
  public Long get(int key) {
    int i = index(key);
    while (entries[i] != null) {

      if (entries[i].getKey() == key) {
        return entries[i].getValue();
      }
      if (i == capacity - 1) {
        i = 0;
      } else {
        i++;
      }
    }
    return null;
  }

  /**
   * Combines hash function for hash collisions resolving.
   *
   * @param key key with which the specified value is to be associated
   * @return index of array where required Entry object should be
   */
  private int index(int key) {
    return Math.abs(hash(key)) % capacity;
  }

  /**
   * Associates the specified value with the specified key in this map.
   * If the map previously contained a mapping for the key, the old
   * value is replaced.
   *
   * @param key key with which the specified value is to be associated
   * @param val value to be associated with the specified key
   */
  private void add(int key, long val) {
    int i = index(key);

    while (entries[i] != null) {

      if (entries[i].getKey() == key) {
        entries[i].setValue(val);
        return;
      }
      if (i == capacity - 1) {
        i = 0;
      } else {
        i++;
      }
    }
    entries[i] = new Entry(key, val);
    size++;
  }

  /**
   * Doubles table size if necessary.
   */
  private void resize(int capacity) {
    Entry[] old = entries;
    entries = new Entry[capacity];
    size = 0;
    this.capacity = capacity;

    for (Entry entry : old) {
      if (entry != null) {
        add(entry.getKey(), entry.getValue());
      }
    }
  }
}
