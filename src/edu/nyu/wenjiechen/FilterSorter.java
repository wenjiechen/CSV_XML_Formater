package edu.nyu.wenjiechen;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.nyu.wenjiechen.Record.FieldComparator;

class FilterSorter {
  public static void filter(List<Record> records, Field field, String value)
      throws IllegalArgumentException {
    if (Arrays.asList(Field.values()).contains(field) == false) {
      throw new IllegalArgumentException("Does not have the Field: " + "\""
          + field + "\"");
    }
    for (Iterator<Record> it = records.iterator(); it.hasNext();) {
      Record record = it.next();
      if (!record.getFieldValue(field).equals(value))
        it.remove();
    }
  }

  public static void sort(List<Record> records,
      List<FieldComparator> comparators) {
    for (Record.FieldComparator comparator : comparators) {
      Collections.sort(records, comparator);
    }
  }
}
