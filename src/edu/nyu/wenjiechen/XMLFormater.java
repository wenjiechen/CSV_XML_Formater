package edu.nyu.wenjiechen;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import static edu.nyu.wenjiechen.FieldName.*;

public class XMLFormater extends AbstractFormater {

  public XMLFormater(String filePath) {
    super(filePath);
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
        System.out.print(a.getData() + ", ");
        fields[fieldNum++] = (String) a.getData();
      }

      // iterate hosts
      Element hosts = site.element("Hosts");
      for (Element host : hosts.elements()) {
        fieldNum = 3;
        Attribute host_id = host.attribute("id");
        fields[fieldNum++] = (String) host_id.getData();
        System.out.println(host_id.getData());
        //
        for (Element ele : host.elements()) {
          fields[fieldNum++] = (String) ele.getData();
          System.out.println(ele.getData());
        }
      }
    }
    System.out.println(Arrays.toString(fields));
    System.out.println(fields[OS.ordinal()]);

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
