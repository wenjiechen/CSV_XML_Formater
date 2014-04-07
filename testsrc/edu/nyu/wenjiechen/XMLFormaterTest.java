package edu.nyu.wenjiechen;

import static edu.nyu.wenjiechen.Field.HOST_ID;
import static edu.nyu.wenjiechen.Field.HOST_NAME;
import static edu.nyu.wenjiechen.Field.OS;
import static edu.nyu.wenjiechen.Field.SITE_NAME;
import static edu.nyu.wenjiechen.Format.CSV;
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
 * Test case 1 and Test case 2. Output formatted file into "testOutput"
 * directory
 * 
 * @author Wenjie Chen
 * 
 */
public class XMLFormaterTest {
  private String path;
  private XMLFormater xmlFormater;
  private String outputDir;

  @Before
  public void setup() {
    path = "coding_exercise" + File.separator
        + "ipsoft_perf_counters_xml_sample_data.xml";
    xmlFormater = new XMLFormater(path);
    outputDir = "testOutput";
    File dir = new File(outputDir);
    if (!dir.exists() && !dir.isDirectory()) {
      dir.mkdirs();
    }
  }

  @Test
  public void sortByOSTest() {
    List<FieldComparator> comparators = Arrays.asList(Record.getComparator(OS,
        ASC));
    xmlFormater.parse().sort(comparators);
    FieldComparator OScomparator = comparators.get(0);
    List<Record> records = xmlFormater.getRecords();
    for (int i = 1; i < records.size(); i++) {
      Assert
          .assertTrue(OScomparator.compare(records.get(i - 1), records.get(i)) <= 0);
    }
    xmlFormater.formatOutput(CSV, outputDir + File.separator
        + "sortByOSTest.csv");
  }

  @Test
  public void sortByHostIdDesTest() {
    List<FieldComparator> comparators = Arrays.asList(Record.getComparator(
        HOST_ID, DES));
    xmlFormater.parse().sort(comparators);
    FieldComparator hostIdcomparator = Record.getComparator(HOST_ID, ASC);
    List<Record> records = xmlFormater.getRecords();
    for (int i = 1; i < records.size(); i++) {
      Assert.assertTrue(hostIdcomparator.compare(records.get(i - 1),
          records.get(i)) >= 0);
    }
    xmlFormater.formatOutput(CSV, outputDir + File.separator
        + "sortByHostIdDesTest.csv");
  }

  @Test
  public void sortByOSAndByHostIdDesTest() {
    List<FieldComparator> comparators = Arrays.asList(
        Record.getComparator(OS, ASC), Record.getComparator(HOST_ID, DES));
    xmlFormater.parse().sort(comparators);
    FieldComparator OScomparator = comparators.get(0);
    FieldComparator hostIdcomparator = Record.getComparator(HOST_ID, ASC);
    List<Record> records = xmlFormater.getRecords();
    for (int i = 1; i < records.size(); i++) {
      Assert.assertTrue(hostIdcomparator.compare(records.get(i - 1),
          records.get(i)) >= 0);
      if (hostIdcomparator.compare(records.get(i - 1), records.get(i)) == 0) {
        Assert.assertTrue(OScomparator.compare(records.get(i - 1),
            records.get(i)) <= 0);
      }
    }
    xmlFormater.formatOutput(CSV, outputDir + File.separator
        + "TestCase1sortByOSAndByHostIdDesTest.csv");
  }

  @Test
  public void filterBySiteNameTest() {
    xmlFormater.parse().filter(SITE_NAME, "NY-01");
    for (Record r : xmlFormater.getRecords()) {
      Assert.assertEquals("NY-01", r.site_name);
    }
    xmlFormater.formatOutput(CSV, outputDir + File.separator
        + "filterBySiteNameTest.csv");
  }

  @Test
  public void sortByHostNameTest() {
    List<FieldComparator> comparators = Arrays.asList(Record.getComparator(
        HOST_NAME, ASC));
    xmlFormater.parse().sort(comparators);
    FieldComparator hostNamecomparator = comparators.get(0);
    List<Record> records = xmlFormater.getRecords();
    for (int i = 1; i < records.size(); i++) {
      Assert.assertTrue(hostNamecomparator.compare(records.get(i - 1),
          records.get(i)) <= 0);
    }
    xmlFormater.formatOutput(CSV, outputDir + File.separator
        + "sortByHostNameTest.csv");
  }

  @Test
  public void filterBySiteNameSortByHostNameTest() {
    List<FieldComparator> comparators = Arrays.asList(Record.getComparator(
        HOST_NAME, ASC));
    xmlFormater.parse().filter(SITE_NAME, "NY-01").sort(comparators);

    FieldComparator hostNamecomparator = comparators.get(0);
    List<Record> records = xmlFormater.getRecords();
    for (int i = 1; i < records.size(); i++) {
      Assert.assertEquals("NY-01", records.get(i).site_name);
      Assert.assertTrue(hostNamecomparator.compare(records.get(i - 1),
          records.get(i)) <= 0);
    }
    xmlFormater.formatOutput(CSV, outputDir + File.separator
        + "Testcase2filterBySiteNameSortByHostNameTest.csv");
  }
}
