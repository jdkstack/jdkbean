package org.jdkstack.logging.jdkbean.core;


public interface Factory {

  void addBean(String name, Bean bean);

  Bean getBean(String name);

  Object getObject(String name);
}
