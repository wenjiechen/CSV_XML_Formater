package edu.nyu.wenjiechen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import edu.nyu.wenjiechen.Record.FieldComparator;

public class XMLFormater implements IFormater {
  private List<Record> records;
  private String filePath;
  private String csvHeader = "site_id, site_name, site_location, host_id, "
      + "host_name, ip_address, operative_system, "
      + "load_avg_1min, load_avg_5min, load_avg_15min";

  public XMLFormater(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public IFormater parse() {
    String[] fields = new String[Field.values().length];
    records = new LinkedList<Record>();
    SAXReader reader = new SAXReader();
    Document doc = null;
    try {
      File file = new File(this.filePath);
      doc = reader.read(file);
    } catch (DocumentException e) {
      e.printStackTrace();
    }

    Element root = doc.getRootElement();
    // iterate each site
    for (Element site : root.elements()) {
      // attributes of site
      int fieldNum = 0;
      for (Attribute a : site.attributes()) {
        fields[fieldNum++] = (String) a.getData();
      }

      // iterate hosts
      Element hosts = site.element("Hosts");
      for (Element host : hosts.elements()) {
        fieldNum = 3;
        fields[fieldNum++] = (String) host.attribute("id").getData();
        for (Element ele : host.elements()) {
          fields[fieldNum++] = (String) ele.getData();
        }
        records.add(new Record(fields));
      }
    }
    return this;
  }

  @Override
  public IFormater filter(Field field, String value)
      throws IllegalArgumentException {
    FilterSorter.filter(records, field, value);
    return this;
  }

  @Override
  public IFormater sort(List<FieldComparator> comparators) {
    FilterSorter.sort(records, comparators);
    return this;
  }

  private String formatToCSV(Record record) {
    String newline = System.getProperty("line.separator");
    return new String(record.site_id + ", " + record.site_name + ", "
        + record.site_location + ", " + record.host_id + ", "
        + record.host_name + ", " + record.ip_address + ", "
        + record.operative_system + ", " + record.load_avg_1min + ", "
        + record.load_avg_5min + ", " + record.load_avg_15min + newline);
  }

  private String format(Format format) {
    StringBuilder sb = new StringBuilder();
    String formatedContent;
    switch (format) {
    case CSV:
      sb.append(csvHeader + System.getProperty("line.separator"));
      for (Record r : records) {
        sb.append(formatToCSV(r));
      }
      formatedContent = sb.toString();
      break;
    case XML:
      throw new IllegalArgumentException("Does not support the format" + "\""
          + format + "\"");
    default:
      throw new IllegalArgumentException("Does not support the format" + "\""
          + format + "\"");
    }
    return formatedContent;
  }

  @Override
  public void formatOutput(Format format, String filePath)
      throws IllegalArgumentException {
    String formatedContent = format(format);
    File file = new File(filePath);
    BufferedWriter bw;
    try {
      if (!file.exists()) {
        file.createNewFile();
      }
      bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
      bw.write(formatedContent);
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("XMLFormater output successfully!");
  }

}
