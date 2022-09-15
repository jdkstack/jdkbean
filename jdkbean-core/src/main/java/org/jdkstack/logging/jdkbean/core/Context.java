package org.jdkstack.logging.jdkbean.core;

public interface Context {

  void scan(Class<?> application);

  void addBean(String name, Bean bean);

   Bean getBean(String name);

  Object getObject(String name);
}
