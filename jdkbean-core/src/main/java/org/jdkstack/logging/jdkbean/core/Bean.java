package org.jdkstack.logging.jdkbean.core;

public class Bean {

  private boolean singleton;
  private Object obj;
  private Class<?> classObj;
  private String name;

  public boolean isSingleton() {
    return this.singleton;
  }

  public void setSingleton(final boolean singleton) {
    this.singleton = singleton;
  }

  public Object getObj() {
    return this.obj;
  }

  public void setObj(final Object obj) {
    this.obj = obj;
  }

  public Class<?> getClassObj() {
    return this.classObj;
  }

  public void setClassObj(final Class<?> classObj) {
    this.classObj = classObj;
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }
}
