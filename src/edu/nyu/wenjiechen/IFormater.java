package edu.nyu.wenjiechen;


public interface IFormater {

  /**
   * 
   */
  public abstract void parse();

  public abstract void output(Format format, String outputPath)
      throws IllegalArgumentException;

  public abstract void filter(Field field, String value);

  public abstract void sorter(FieldComparator fc);

}