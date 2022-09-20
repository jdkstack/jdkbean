package org.jdkstack.logging.jdkbean.core.bean;

import org.jdkstack.jdkbean.api.bean.Bean;

public class BeanService implements Bean {

  private boolean singleton;
  private Object obj;
  private Class<?> classObj;
  private String name;

  @Override
  public boolean isSingleton() {
    return this.singleton;
  }

  @Override
  public void setSingleton(final boolean singleton) {
    this.singleton = singleton;
  }

  @Override
  public Object getObj() {
    return this.obj;
  }

  @Override
  public void setObj(final Object obj) {
    this.obj = obj;
  }

  @Override
  public Class<?> getClassObj() {
    return this.classObj;
  }

  @Override
  public void setClassObj(final Class<?> classObj) {
    this.classObj = classObj;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(final String name) {
    this.name = name;
  }
}
