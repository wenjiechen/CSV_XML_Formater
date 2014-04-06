package edu.nyu.wenjiechen;

import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLFormater implements IFormater {
  private List<Record> records = new LinkedList<Record>();
  private File file;

  public XMLFormater(String filePath) {
    this.file = new File(filePath);
  }

  @Override
  public IFormater parse() {
    String[] fields = new String[Field.values().length];
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
    return this;
  }

  @Override
  public IFormater filter(Field field, String value) {
    for (Iterator<Record> it = records.iterator(); it.hasNext();) {
      Record record = it.next();
      if (!record.getFieldValue(field).equals(value))
        it.remove();
    }
    System.out.println(records);
    return this;
  }

  @Override
  public void output(Format format, String outputPath)
      throws IllegalArgumentException {
    if (format != Format.CSV)
      throw new IllegalArgumentException();
  }

  @Override
  public IFormater sort(RecordComparator fieldComparator) {
    Collections.sort(records, fieldComparator);
    System.out.println(records);
    return this;
  }

  @Override
  public List<Record> getRecords() {
    return records;
  }
}
