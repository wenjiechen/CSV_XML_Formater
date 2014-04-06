package edu.nyu.wenjiechen;

import static edu.nyu.wenjiechen.Field.*;
import static edu.nyu.wenjiechen.Format.*;
import static edu.nyu.wenjiechen.Record.FieldComparator.Order.*;

import java.util.Arrays;
import java.util.List;

import edu.nyu.wenjiechen.Record.FieldComparator;

public class Test {
  public static void main(String[] args) {
    String path = "coding_exercise\\ipsoft_perf_counters_xml_sample_data.xml";
    XMLFormater x = new XMLFormater(path);
    List<FieldComparator> cs1 = Arrays.asList(Record.getComparator(OS, ASC),
        Record.getComparator(HOST_ID, DES));
    List<FieldComparator> cs2 = Arrays.asList(Record.getComparator(HOST_NAME,
        ASC));
    List<FieldComparator> cs3 = Arrays.asList(
        Record.getComparator(LOAD_AVG_1MIN, ASC),
        Record.getComparator(HOST_ID, DES));

    x.parse().sort(cs1).format(CSV).output("output1.csv");
    x.parse().filter(SITE_NAME, "NY-01").sort(cs2).format(CSV)
        .output("output2.csv");
    x.parse().sort(cs3).format(CSV).output("output3.csv");
  }
}
