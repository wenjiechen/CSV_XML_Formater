package edu.nyu.wenjiechen;

import java.util.List;

import edu.nyu.wenjiechen.Record.FieldComparator;

public interface IFormater {

  /**
   * 
   */
  public IFormater parse();

  /**
   * 
   * @param format
   * @param outputPath
   * @throws IllegalArgumentException
   */
  public void formatOutput(Format format, String outputPath)
      throws IllegalArgumentException;

  /**
   * 
   * @param field
   * @param value
   * @return
   * @throws IllegalArgumentException
   */
  public IFormater filter(Field field, String value)
      throws IllegalArgumentException;;

  /**
   * 
   * @param comparators
   * @return
   */
  public IFormater sort(List<FieldComparator> comparators);

  /**
   * 
   * @return
   */
  public List<Record> getRecords();
}