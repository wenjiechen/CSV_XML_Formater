package edu.nyu.wenjiechen;

import static edu.nyu.wenjiechen.FieldName.*;

public class Test {
  public static void main(String[] args) {
    Record r = new Record.Builder().siteId("101").build();
    r = new Record.Builder().siteId("").siteName("").siteLocation("").build();
    FieldName f = SITE_ID;
    System.out.println(f);
  }
}
