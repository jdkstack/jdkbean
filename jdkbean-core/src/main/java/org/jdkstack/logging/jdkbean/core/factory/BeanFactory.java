package org.jdkstack.logging.jdkbean.core.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jdkstack.logging.jdkbean.core.Bean;
import org.jdkstack.logging.jdkbean.core.Factory;

public class BeanFactory implements Factory {

  private final Map<String, Bean> beansMap = new ConcurrentHashMap<>(256);

  @Override
  public void addBean(String name, Bean bean) {
    this.beansMap.put(name, bean);
  }

  @Override
  public Bean getBean(String name) {
    return this.beansMap.get(name);
  }

  @Override
  public Object getObject(String name) {
    return this.beansMap.get(name).getObj();
  }
}
