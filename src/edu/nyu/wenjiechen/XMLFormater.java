package edu.nyu.wenjiechen;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import static edu.nyu.wenjiechen.Order.*;

public class XMLFormater implements IFormater {
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

  @Override
  public void filter(Field field, String value) {
    for (Iterator<Record> it = records.iterator(); it.hasNext();) {
      Record record = it.next();
      if (!record.getFieldValue(field).equals(value))
        it.remove();
    }
    System.out.println(records);
  }

  @Override
  public void output(Format format, String outputPath)
      throws IllegalArgumentException {
    if (format != Format.CSV)
      throw new IllegalArgumentException();

  }

  private Comparator<Record> getComparator(Field field, Order order) {
    switch (field) {
    case HOST_ID:
      break;
    case HOST_NAME:
      break;
    case IP_ADDRESS:
      break;
    case LOAD_AVG_15MIN:
      break;
    case LOAD_AVG_1MIN:
      break;
    case LOAD_AVG_5MIN:
      break;
    case OS:
      break;
    case SITE_ID:
      return order == ASC ? FieldComparator.SITE_ID
          : FieldComparator.SITE_ID_DES;
    case SITE_LOCATION:
      break;
    case SITE_NAME:
      break;
    default:
      break;

    }
  }

  @Override
  public void sorter(FieldComparator fc) {
    Collections.sort(records, fc);
    System.out.println(records);
  }
}
