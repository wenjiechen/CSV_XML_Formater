package edu.nyu.wenjiechen;

import java.util.List;

public interface IFormater {

  /**
   * 
   */
  public IFormater parse();

  public IFormater format(Format format) throws IllegalArgumentException;

  /**
   * 
   * @param format
   * @param outputPath
   * @throws IllegalArgumentException
   */
  public void output(String outputPath);

  /**
   * 
   * @param field
   * @param value
   * @return
   */
  public IFormater filter(Field field, String value)
      throws IllegalArgumentException;;

  /**
   * 
   * @param fieldComparator
   * @return
   */
  public IFormater sort(List<Record.FieldComparator> comparators);

  public List<Record> getRecords();

}