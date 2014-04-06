package edu.nyu.wenjiechen;

import java.util.Comparator;
import static edu.nyu.wenjiechen.Order.*;

public final class FieldComparator implements Comparator<Record> {
  public static final Comparator<Record> SITE_ID = new SiteId();
  public static final Comparator<Record> SITE_ID_DES = new SiteIdDes();
  public static final Comparator<Record> SITE_NAME = new SiteName();
  public static final Comparator<Record> SITE_NAME_DES = new SiteNameDes();

  private Field field;
  private Order order;

  public FieldComparator(Field field, Order order) {
    this.field = field;
    this.order = order;
  }

  // public static final Comparator<Record> SITE_LOCATION = new ;
  // public static final Comparator<Record> SITE_LOCATION_DES = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;
  // public static final Comparator<Record> = new ;

  private static class SiteId implements Comparator<Record> {
    @Override
    public int compare(Record arg0, Record arg1) {
      return arg0.site_id.compareTo(arg1.site_id);
    }
  }

  private static class SiteIdDes implements Comparator<Record> {
    @Override
    public int compare(Record arg0, Record arg1) {
      return SITE_ID.compare(arg0, arg1) * -1;
    }
  }

  private static class SiteName implements Comparator<Record> {
    @Override
    public int compare(Record arg0, Record arg1) {
      return arg0.site_id.compareTo(arg1.site_id);
    }
  }

  private static class SiteNameDes implements Comparator<Record> {
    @Override
    public int compare(Record arg0, Record arg1) {
      return arg0.site_id.compareTo(arg1.site_id) * -1;
    }
  }

  @Override
  public int compare(Record o1, Record o2) {
    int ret;
    switch (field) {
    case HOST_ID:
      ret = o1.host_id.compareTo(o2.host_id);
      return order == ASC ? ret : ret * -1;
    case HOST_NAME:
      ret = o1.host_name.compareTo(o2.host_name);
      return order == ASC ? ret : ret * -1;
    case IP_ADDRESS:
      ret = o1.ip_address.compareTo(o2.ip_address);
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
  // private static class SiteName implements Comparator<Record> {
  // @Override
  // public int compare(Record arg0, Record arg1) {
  // return arg0.site_id.compareTo(arg1.site_id);
  // }
  // }

}
