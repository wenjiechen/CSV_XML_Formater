package edu.nyu.wenjiechen;

import java.util.List;

import edu.nyu.wenjiechen.Record.FieldComparator;

/**
 * All formater need implement the IFormater interface.
 * 
 * @author Wenjie Chen
 * 
 */
public interface IFormater {

  /**
   * Read the file that needs to be formatted and parse it into Record Objects.
   * This method need to be called first before other methods.
   */
  public IFormater parse();

  /**
   * 
   * @param format
   *          the output format
   * @param outputPath
   *          output file at this path
   * @throws IllegalArgumentException
   *           if the format didn't support by the formatter
   */
  public void formatOutput(Format format, String outputPath)
      throws IllegalArgumentException;

  /**
   * 
   * @param field
   *          the filtering field
   * @param value
   *          the wanted value of the filtering field
   * @return this reference of current IFormater Object to concatenate following
   *         methods
   * @throws IllegalArgumentException
   *           the filtering field doesn't exist
   */
  public IFormater filter(Field field, String value)
      throws IllegalArgumentException;;

  /**
   * 
   * @param comparators
   *          list of FieldComparator used by sort
   * @return this reference of current IFormater Object to concatenate following
   *         methods
   */
  public IFormater sort(List<FieldComparator> comparators);

  /**
   * 
   * @return list of all records which are parsed by the IFormater
   */
  public List<Record> getRecords();
}