package edu.nyu.wenjiechen;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLFormater implements AbstractFormater {
  private List<Record> records = new LinkedList<Record>();
  private File file;

  public XMLFormater(String filePath) {
    this.file = new File(filePath);
  }

  @Override
  public void parse() {
    String[] fields = new String[10];
    SAXReader reader = new SAXReader();
    Document doc = null;
    try {
      doc = reader.read(this.file);
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
    System.out.println(records);
  }

  public static void main(String[] args) {
    String path = "coding_exercise\\ipsoft_perf_counters_xml_sample_data.xml";
    XMLFormater x = new XMLFormater(path);
    x.parse();
  }

  @Override
  public void filter() {
    // TODO Auto-generated method stub

  }

  @Override
  public void sorter() {
    // TODO Auto-generated method stub

  }

  @Override
  public void output(String outputPath) {
    // TODO Auto-generated method stub

  }
}
