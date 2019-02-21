/**
 * Class for storing key&value pairs.
 */
public class Entry {
  private int key;
  private long value;

  public Entry(int k, long v) {
    key = k;
    value = v;
  }

  public void setValue(long value) {
    this.value = value;
  }

  public int getKey() {
    return key;
  }

  public long getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Entry entry = (Entry) o;

    if (key != entry.key) return false;
    return value == entry.value;
  }

  @Override
  public int hashCode() {
    int result = key;
    result = 31 * result + (int) (value ^ (value >>> 32));
    return result;
  }
}