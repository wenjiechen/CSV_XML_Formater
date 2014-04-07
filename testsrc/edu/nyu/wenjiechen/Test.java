package edu.nyu.wenjiechen;

import static edu.nyu.wenjiechen.Field.*;
import static edu.nyu.wenjiechen.Format.*;
import static edu.nyu.wenjiechen.Record.FieldComparator.Order.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import edu.nyu.wenjiechen.Record.FieldComparator;

public class Test {
  private static void xmltest() {
    String path = "coding_exercise" + File.separator
        + "ipsoft_perf_counters_xml_sample_data.xml";
    XMLFormater x = new XMLFormater(path);
    List<FieldComparator> cs1 = Arrays.asList(Record.getComparator(OS, ASC),
        Record.getComparator(HOST_ID, DES));
    List<FieldComparator> cs2 = Arrays.asList(Record.getComparator(HOST_NAME,
        ASC));
    List<FieldComparator> cs3 = Arrays.asList(
        Record.getComparator(LOAD_AVG_1MIN, ASC),
        Record.getComparator(HOST_ID, DES));

    x.parse().sort(cs1).formatOutput(CSV, "output1.csv");
    x.parse().filter(SITE_NAME, "NY-01").sort(cs2)
        .formatOutput(CSV, "output2.csv");
    x.parse().sort(cs3).formatOutput(CSV, "output3.csv");
  }

  private static void csvtest() {
    String path = "coding_exercise" + File.separator
        + "ipsoft_perf_counters_csv_sample_data.txt";
    CSVFormater c = new CSVFormater(",", path);
    List<FieldComparator> cs1 = Arrays.asList(Record.getComparator(OS, ASC),
        Record.getComparator(HOST_ID, DES));
    // c.parse().filter(SITE_NAME, "NY-01").sort(cs1);
    c.parse().sort(cs1).formatOutput(XML, "output.xml");

  }

  public static void main(String[] args) {
    csvtest();
    xmltest();
  }
}
