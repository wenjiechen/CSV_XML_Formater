package edu.nyu.wenjiechen;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.nyu.wenjiechen.Record.FieldComparator;

/**
 * Provides two utilities, which filter or sort Record Object, can be used by
 * IFormaters
 * 
 * @author Wenjie Chen
 * 
 */
class FilterSorter {
  /**
   * 
   * @param records
   *          Record objects need to be filtered
   * @param field
   *          need to be filtered
   * @param value
   *          filter condition
   * @throws IllegalArgumentException
   *           if the filed doesn't exist
   */
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

  /**
   * 
   * @param records
   *          Record objects need to be sorted
   * @param comparators
   *          list of field comparators
   */
  public static void sort(List<Record> records,
      List<FieldComparator> comparators) {
    for (Record.FieldComparator comparator : comparators) {
      Collections.sort(records, comparator);
    }
  }
}
