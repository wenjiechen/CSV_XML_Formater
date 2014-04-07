package edu.nyu.wenjiechen;

import static edu.nyu.wenjiechen.Field.HOST_ID;
import static edu.nyu.wenjiechen.Field.HOST_NAME;
import static edu.nyu.wenjiechen.Field.IP_ADDRESS;
import static edu.nyu.wenjiechen.Field.LOAD_AVG_15MIN;
import static edu.nyu.wenjiechen.Field.LOAD_AVG_1MIN;
import static edu.nyu.wenjiechen.Field.LOAD_AVG_5MIN;
import static edu.nyu.wenjiechen.Field.OS;
import static edu.nyu.wenjiechen.Field.SITE_ID;
import static edu.nyu.wenjiechen.Field.SITE_LOCATION;
import static edu.nyu.wenjiechen.Field.SITE_NAME;
import static edu.nyu.wenjiechen.Record.FieldComparator.Order.ASC;

import java.util.Comparator;

import edu.nyu.wenjiechen.Record.FieldComparator.Order;

/**
 * Record Objects are used to store the data parsed by IFormater. Provides
 * comparator of all fields in ASCENDING or DESCENDING order.
 * 
 * @author Wenjie Chen
 * 
 */
class Record {
  public final String site_id;
  public final String site_name;
  public final String site_location;
  public final String host_id;
  public final String host_name;
  public final String ip_address;
  public final String operative_system;
  public final String load_avg_1min;
  public final String load_avg_5min;
  public final String load_avg_15min;

  public Record(String[] fields) {
    site_id = fields[SITE_ID.ordinal()];
    site_name = fields[SITE_NAME.ordinal()];
    site_location = fields[SITE_LOCATION.ordinal()];
    host_id = fields[HOST_ID.ordinal()];
    host_name = fields[HOST_NAME.ordinal()];
    ip_address = fields[IP_ADDRESS.ordinal()];
    operative_system = fields[OS.ordinal()];
    load_avg_1min = fields[LOAD_AVG_1MIN.ordinal()];
    load_avg_5min = fields[LOAD_AVG_5MIN.ordinal()];
    load_avg_15min = fields[LOAD_AVG_15MIN.ordinal()];
  }

  /**
   * 
   * @param field
   * @return value of the field
   */
  public String getFieldValue(Field field) {
    switch (field) {
    case HOST_ID:
      return host_id;
    case HOST_NAME:
      return host_name;
    case IP_ADDRESS:
      return ip_address;
    case LOAD_AVG_15MIN:
      return load_avg_15min;
    case LOAD_AVG_1MIN:
      return load_avg_1min;
    case LOAD_AVG_5MIN:
      return load_avg_5min;
    case OS:
      return operative_system;
    case SITE_ID:
      return site_id;
    case SITE_LOCATION:
      return site_location;
    case SITE_NAME:
      return site_name;
    default:
      throw new IllegalArgumentException("Does not have the Field: " + "\""
          + field + "\"");
    }
  }

  private static FieldComparator[][] fieldComparators = new FieldComparator[Field
      .values().length][Order.values().length];

  /**
   * 
   * @param field
   *          need to be sorted
   * @param order
   *          sort the field in the indicated order
   * @return FieldComparator
   */
  public static FieldComparator getComparator(Field field, Order order) {
    int fieldPos = field.ordinal();
    int orderPos = order.ordinal();
    return (fieldComparators[fieldPos][orderPos] == null) ? (fieldComparators[fieldPos][orderPos] = new FieldComparator(
        field, order)) : fieldComparators[fieldPos][orderPos];
  }

  @Override
  public String toString() {
    return new String(site_id + ", " + site_name + ", " + site_location + ", "
        + host_id + ", " + host_name + ", " + ip_address + ", "
        + operative_system + ", " + load_avg_1min + ", " + load_avg_5min + ", "
        + load_avg_15min + "\n");
  }

  /**
   * 
   * @author Wenjie Chen
   * 
   */
  public static class FieldComparator implements Comparator<Record> {

    /**
     * Indicate ASCENDING or DESCENDING order
     * 
     * @author Wenjie Chen
     * 
     */
    public enum Order {
      ASC, DES,
    }

    private Field field;
    private Order order;

    private FieldComparator(Field field, Order order) {
      this.field = field;
      this.order = order;
    }

    private int compareNumberSequence(String s1, String s2) {
      String[] sa1 = s1.split("\\.");
      String[] sa2 = s2.split("\\.");
      for (int i = 0; i < sa1.length; i++) {
        int i1 = Integer.valueOf(sa1[i]);
        int i2 = Integer.valueOf(sa2[i]);
        if (i1 < i2)
          return -1;
        else if (i1 > i2)
          return 1;
      }
      return 0;
    }

    @Override
    public int compare(Record o1, Record o2) {
      if (o1 == o2) {
        return 0;
      } else if (o1 == null) {
        return 1;
      } else if (o2 == null) {
        return -1;
      }

      int ret;
      switch (field) {
      case HOST_ID:
        ret = compareNumberSequence(o1.host_id, o2.host_id);
        return order == ASC ? ret : ret * -1;
      case HOST_NAME:
        ret = o1.host_name.compareTo(o2.host_name);
        return order == ASC ? ret : ret * -1;
      case IP_ADDRESS:
        ret = compareNumberSequence(o1.ip_address, o2.ip_address);
        return order == ASC ? ret : ret * -1;
      case LOAD_AVG_15MIN:
        ret = compareNumberSequence(o1.load_avg_15min, o2.load_avg_15min);
        return order == ASC ? ret : ret * -1;
      case LOAD_AVG_1MIN:
        ret = compareNumberSequence(o1.load_avg_1min, o2.load_avg_1min);
        return order == ASC ? ret : ret * -1;
      case LOAD_AVG_5MIN:
        ret = compareNumberSequence(o1.load_avg_5min, o2.load_avg_5min);
        return order == ASC ? ret : ret * -1;
      case OS:
        ret = o1.operative_system.compareTo(o2.operative_system);
        return order == ASC ? ret : ret * -1;
      case SITE_ID:
        ret = compareNumberSequence(o1.site_id, o2.site_id);
        return order == ASC ? ret : ret * -1;
      case SITE_LOCATION:
        ret = o1.site_location.compareTo(o2.site_location);
        return order == ASC ? ret : ret * -1;
      case SITE_NAME:
        ret = o1.site_name.compareTo(o2.site_name);
        return order == ASC ? ret : ret * -1;
      default:
        throw new IllegalArgumentException("Does not have the Field: " + "\""
            + field + "\"");
      }
    }
  }
}
