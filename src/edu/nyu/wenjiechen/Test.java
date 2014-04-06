package edu.nyu.wenjiechen;

import static edu.nyu.wenjiechen.Field.*;
import static edu.nyu.wenjiechen.Order.*;

public class Test {
  public static void main(String[] args) {
    String path = "coding_exercise\\ipsoft_perf_counters_xml_sample_data.xml";
    XMLFormater x = new XMLFormater(path);
    x.parse();
    // x.filter(SITE_NAME, "NY-01");
    System.out.println("----");
    x.sorter(new FieldComparator(IP_ADDRESS, ASC));
  }
}
