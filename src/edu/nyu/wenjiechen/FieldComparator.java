package edu.nyu.wenjiechen;

import java.util.Comparator;
import static edu.nyu.wenjiechen.Order.*;

public final class FieldComparator implements Comparator<Record> {
  private Field field;
  private Order order;

  public FieldComparator(Field field, Order order) {
    this.field = field;
    this.order = order;
  }

  private int compareNumbers(String s1, String s2) {
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
    int ret;
    switch (field) {
    case HOST_ID:
      ret = compareNumbers(o1.host_id, o2.host_id);
      return order == ASC ? ret : ret * -1;
    case HOST_NAME:
      ret = o1.host_name.compareTo(o2.host_name);
      return order == ASC ? ret : ret * -1;
    case IP_ADDRESS:
      // WRONG!!!
      ret = compareNumbers(o1.ip_address, o2.ip_address);
      return order == ASC ? ret : ret * -1;
    case LOAD_AVG_15MIN:
      ret = o1.load_avg_15min.compareTo(o2.load_avg_15min);
      return order == ASC ? ret : ret * -1;
    case LOAD_AVG_1MIN:
      ret = o1.load_avg_1min.compareTo(o2.load_avg_1min);
      return order == ASC ? ret : ret * -1;
    case LOAD_AVG_5MIN:
      ret = o1.load_avg_5min.compareTo(o2.load_avg_5min);
      return order == ASC ? ret : ret * -1;
    case OS:
      ret = o1.operative_system.compareTo(o2.operative_system);
      return order == ASC ? ret : ret * -1;
    case SITE_ID:
      ret = o1.site_id.compareTo(o2.site_id);
      return order == ASC ? ret : ret * -1;
    case SITE_LOCATION:
      ret = o1.site_location.compareTo(o2.site_location);
      return order == ASC ? ret : ret * -1;
    case SITE_NAME:
      ret = o1.site_name.compareTo(o2.site_name);
      return order == ASC ? ret : ret * -1;
    default:
      return Integer.MIN_VALUE;
    }
  }
}
