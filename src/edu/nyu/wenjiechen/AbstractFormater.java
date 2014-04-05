package edu.nyu.wenjiechen;

import java.io.File;

public abstract class AbstractFormater {
  protected File file;

  public AbstractFormater(String filePath) {
    this.file = new File(filePath);
  }

  /**
   * 
   */
  public abstract void parse();

  public abstract void output(String outputPath);

  public abstract void filter();

  public abstract void sorter();

}