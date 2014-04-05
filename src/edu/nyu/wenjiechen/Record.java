package edu.nyu.wenjiechen;

import static edu.nyu.wenjiechen.FieldName.*;

public class Record {
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
}
