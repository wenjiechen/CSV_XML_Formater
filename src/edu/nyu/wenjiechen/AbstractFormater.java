package edu.nyu.wenjiechen;


public interface AbstractFormater {

  /**
   * 
   */
  public abstract void parse();

  public abstract void output(String outputPath);

  public abstract void filter();

  public abstract void sorter();

}