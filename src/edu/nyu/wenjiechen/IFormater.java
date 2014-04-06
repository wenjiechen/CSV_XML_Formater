package edu.nyu.wenjiechen;

import java.util.List;

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
  public void output(Format format, String outputPath)
      throws IllegalArgumentException;

  /**
   * 
   * @param field
   * @param value
   * @return
   */
  public IFormater filter(Field field, String value);

  /**
   * 
   * @param fieldComparator
   * @return
   */
  public IFormater sort(RecordComparator fieldComparator);
  
  public List<Record> getRecords();

}