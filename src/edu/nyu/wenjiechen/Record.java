package edu.nyu.wenjiechen;

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

  public static class Builder {
    private String site_id;
    private String site_name;
    private String site_location;
    private String host_id;
    private String host_name;
    private String ip_address;
    private String operative_system;
    private String load_avg_1min;
    private String load_avg_5min;
    private String load_avg_15min;

    public Builder siteId(String site_id) {
      this.site_id = site_id;
      return this;
    }

    public Builder siteName(String site_name) {
      this.site_name = site_name;
      return this;
    }

    public Builder siteLocation(String site_location) {
      this.site_location = site_location;
      return this;
    }

    public Builder hostId(String host_id) {
      this.host_id = host_id;
      return this;
    }

    public Builder hostName(String host_name) {
      this.host_name = host_name;
      return this;
    }

    public Builder ipAddress(String ip_address) {
      this.ip_address = ip_address;
      return this;
    }

    public Builder operativeSystem(String operative_system) {
      this.operative_system = operative_system;
      return this;
    }

    public Builder loadAvg1min(String load_avg_1min) {
      this.load_avg_1min = load_avg_1min;
      return this;
    }

    public Builder loadAvg5min(String load_avg_5min) {
      this.load_avg_5min = load_avg_5min;
      return this;
    }

    public Builder loadAvg15min(String load_avg_15min) {
      this.load_avg_15min = load_avg_15min;
      return this;
    }

    public Record build() {
      return new Record(this);
    }
  }

  private Record(Builder builder) {
    site_id = builder.site_id;
    site_name = builder.site_name;
    site_location = builder.site_location;
    host_id = builder.host_id;
    host_name = builder.host_name;
    ip_address = builder.ip_address;
    operative_system = builder.operative_system;
    load_avg_1min = builder.load_avg_1min;
    load_avg_5min = builder.load_avg_5min;
    load_avg_15min = builder.load_avg_15min;
  }

  public Record(String[] fields) {
    
  }
}
