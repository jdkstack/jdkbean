package org.jdkstack.logging.jdkbean.core;

@Component(value = "daoService", singleton = true)
public class DaoService {

  public String get() {
    return "ds";
  }
}
