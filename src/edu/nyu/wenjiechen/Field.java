package edu.nyu.wenjiechen;

/**
 * Fields exist in input files. Need to be updated, if input files have new
 * field.
 * 
 * @author Wenjie Chen
 * 
 */
public enum Field {
  SITE_ID("site_id"),
  SITE_NAME("site_name"),
  SITE_LOCATION("site_location"),
  HOST_ID("host_id"),
  HOST_NAME("host_name"),
  IP_ADDRESS("ip_address"),
  OS("OS"),
  LOAD_AVG_1MIN("load_avg_1min"),
  LOAD_AVG_5MIN("load_avg_5min"),
  LOAD_AVG_15MIN("load_avg_15min");

  private String description;

  Field(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
