package edu.nyu.wenjiechen;

import static edu.nyu.wenjiechen.Field.HOST_ID;
import static edu.nyu.wenjiechen.Field.LOAD_AVG_1MIN;
import static edu.nyu.wenjiechen.Format.XML;
import static edu.nyu.wenjiechen.Record.FieldComparator.Order.ASC;
import static edu.nyu.wenjiechen.Record.FieldComparator.Order.DES;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.wenjiechen.Record.FieldComparator;

/**
 * Test case 3. Output formatted file into "testOutput" directory
 * 
 * @author Wenjie Chen
 * 
 */
public class CSVFormaterTest {

  private String path;
  private CSVFormater csvFormater;
  private String outputDir;
  private String delimiter;

  @Before
  public void setup() {
    path = "coding_exercise" + File.separator
        + "ipsoft_perf_counters_csv_sample_data.txt";
    delimiter = ",";
    csvFormater = new CSVFormater(delimiter, path);
    outputDir = "testOutput";
    File dir = new File(outputDir);
    if (!dir.exists() && !dir.isDirectory()) {
      dir.mkdirs();
    }

  }

  @Test
  public void sortByLoadAvg1MinTest() {
    List<FieldComparator> comparators = Arrays.asList(Record.getComparator(
        LOAD_AVG_1MIN, ASC));
    csvFormater.parse().sort(comparators);
    FieldComparator load1comparator = comparators.get(0);
    List<Record> records = csvFormater.getRecords();
    for (int i = 1; i < records.size(); i++) {
      Assert.assertTrue(load1comparator.compare(records.get(i - 1),
          records.get(i)) <= 0);
    }
    csvFormater.formatOutput(XML, outputDir + File.separator
        + "sortByLoadAvg1MinTest.xml");
  }

  @Test
  public void sortByLoadHostIdDesTest() {
    List<FieldComparator> comparators = Arrays.asList(Record.getComparator(
        HOST_ID, DES));
    csvFormater.parse().sort(comparators);
    FieldComparator hostIdcomparator = Record.getComparator(HOST_ID, ASC);
    List<Record> records = csvFormater.getRecords();
    for (int i = 1; i < records.size(); i++) {
      Assert.assertTrue(hostIdcomparator.compare(records.get(i - 1),
          records.get(i)) >= 0);
    }
    csvFormater.formatOutput(XML, outputDir + File.separator
        + "sortByLoadHostIdDesTest.xml");
  }

  @Test
  public void sortByLoadAvg1MinByLoadHostIdDesTest() {
    List<FieldComparator> comparators = Arrays.asList(
        Record.getComparator(LOAD_AVG_1MIN, ASC),
        Record.getComparator(HOST_ID, DES));
    csvFormater.parse().sort(comparators);
    FieldComparator load1comparator = comparators.get(0);
    FieldComparator hostIdcomparator = Record.getComparator(HOST_ID, ASC);
    List<Record> records = csvFormater.getRecords();
    for (int i = 1; i < records.size(); i++) {
      Assert.assertTrue(hostIdcomparator.compare(records.get(i - 1),
          records.get(i)) >= 0);
      if (hostIdcomparator.compare(records.get(i - 1), records.get(i)) == 0) {
        Assert.assertTrue(load1comparator.compare(records.get(i - 1),
            records.get(i)) <= 0);
      }
    }
    csvFormater.formatOutput(XML, outputDir + File.separator
        + "Testcase3sortByLoadAvg1MinByLoadHostIdDesTest.xml");
  }
}
