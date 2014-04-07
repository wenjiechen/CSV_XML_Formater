package edu.nyu.wenjiechen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import edu.nyu.wenjiechen.Record.FieldComparator;

public class CSVFormater implements IFormater {
  private List<Record> records = new LinkedList<Record>();
  private String filePath;
  private String delimiter;

  public CSVFormater(String delimiter, String filePath) {
    this.filePath = filePath;
    this.delimiter = delimiter;
  }

  @Override
  public IFormater parse() {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(new File(this.filePath)));
      String line = null;
      int lineCnt = 0;
      while ((line = br.readLine()) != null) {
        if (++lineCnt == 1)
          continue;

        String[] data = line.split("\\s*" + this.delimiter + "\\s*");
        Record record = new Record(data);
        records.add(record);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (br != null)
          br.close();
      } catch (IOException ex) {
        ex.printStackTrace();
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

  private Document formatToXML() {
    // create xml document
    Document doc = DocumentHelper.createDocument();
    // root element
    Element sites = doc.addElement("Sites");
    Element site = null;
    Element hosts = null;
    Element host = null;
    for (Record r : records) {
      if (site == null || !site.attribute("id").getData().equals(r.site_id)) {
        site = sites.addElement("Site");
        site.addAttribute("id", r.site_id);
        site.addAttribute("name", r.site_name);
        site.addAttribute("location", r.site_location);
        site.addAttribute("xmlns", "http://example.ipsoft.com/coding.xsd");
        hosts = site.addElement("Hosts");
      }
      host = hosts.addElement("Host");
      host.addAttribute("id", r.host_id);
      host.addElement("Host_Name").addText(r.host_name);
      host.addElement("IP_address").addText(r.ip_address);
      host.addElement("OS").addText(r.operative_system);
      host.addElement("Load_avg_1min").addText(r.load_avg_1min);
      host.addElement("Load_avg_5min").addText(r.load_avg_5min);
      host.addElement("Load_avg_15min").addText(r.load_avg_15min);
    }
    return doc;
  }

  @Override
  public void formatOutput(Format format, String outputPath)
      throws IllegalArgumentException {
    Document doc = null;
    switch (format) {
    case CSV:
      throw new IllegalArgumentException("Does not support the format" + "\""
          + format + "\"");
    case XML:
      doc = formatToXML();
      break;
    default:
      throw new IllegalArgumentException("Does not support the format" + "\""
          + format + "\"");
    }

    try {
      FileWriter fileWriter = new FileWriter(outputPath);
      // auto indent format
      OutputFormat prettyFormater = OutputFormat.createPrettyPrint();
      XMLWriter xmlWriter = new XMLWriter(fileWriter, prettyFormater);
      xmlWriter.write(doc);
      xmlWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
