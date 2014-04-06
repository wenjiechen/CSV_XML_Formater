package edu.nyu.wenjiechen;

import java.util.Arrays;
import static edu.nyu.wenjiechen.Field.*;
import static edu.nyu.wenjiechen.FieldComparator.Order.*;

public class Test {
  public static void main(String[] args) {
    String path = "coding_exercise\\ipsoft_perf_counters_xml_sample_data.xml";
    XMLFormater x = new XMLFormater(path);
    x.parse().sort(new FieldComparator(IP_ADDRESS, DES));
    // x.filter(SITE_NAME, "NY-01");
  }
}
