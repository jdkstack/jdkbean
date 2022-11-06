package org.jdkstack.logging.jdkbean.core.context;

import java.lang.reflect.Executable;
import java.util.Locale;
import org.jdkstack.jdkbean.api.bean.Bean;
import org.jdkstack.jdkbean.api.context.Context;
import org.jdkstack.jdkbean.api.factory.Factory;
import org.jdkstack.logging.jdkbean.core.annotation.Component;
import org.jdkstack.logging.jdkbean.core.annotation.ComponentScan;
import org.jdkstack.logging.jdkbean.core.annotation.ConstructorResource;
import org.jdkstack.logging.jdkbean.core.bean.BeanService;
import org.jdkstack.logging.jdkbean.core.factory.BeanFactory;

public class ApplicationContext implements Context {

  private final Factory beanFactory = new BeanFactory();

  @Override
  public final void scan(Class<?> application) {
    try {
      // 1.扫描Bean.
      // 2.获取启动类上的注解.
      final ComponentScan componentScan = application.getAnnotation(ComponentScan.class);
      // 获取全限定名类.
      final String[] values = componentScan.value();
      // 循环每一个全限定名类.
      for (final String value : values) {
        // 反射加载类.
        final Class<?> classObj = Class.forName(value);
        this.parser(classObj);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void parser(final Class<?> classObj) throws Exception {
    // 这个类有Component注解吗?
    final Component component = classObj.getAnnotation(Component.class);
    if (component != null) {
      // 是单例吗?
      final boolean singleton = component.singleton();
      //
      String beanName = component.value();
      if ("".equals(beanName)) {
        String simpleName = classObj.getSimpleName();
        String substring1 = simpleName.substring(0, 1);
        String s = substring1.toLowerCase(Locale.getDefault());
        String substring2 = simpleName.substring(1);
        beanName = s + substring2;
      }
      // 构造函数注入.
      final Executable[] constructors = classObj.getConstructors();
      for (Executable constructor : constructors) {
        ConstructorResource constructorResource = constructor.getAnnotation(ConstructorResource.class);
        Object object = null;
        if (constructorResource != null) {
          final Class<?>[] parameterTypes = constructor.getParameterTypes();
          int parameterCount = constructor.getParameterCount();
          final Object[] objArr = new Object[parameterCount];
          final String[] names = constructorResource.value();
          for (int i = 0; i < parameterCount; i++) {
            // 查看有没有对应字段名的对象.
            final Bean bean = this.beanFactory.getBean(names[i]);
            if (bean != null) {
              //
              Object obj = bean.getObj();
              if (obj == null) {
                obj = bean.getClassObj().getConstructor().newInstance();
              }
              objArr[i] = obj;
            }
          }
          if (singleton) {
            object = classObj.getConstructor(parameterTypes).newInstance(objArr);
          }
        } else {
          // 没有构造函数有注解.
          if (singleton) {
            object = classObj.getConstructor().newInstance();
          }
        }
        final Bean bean = new BeanService();
        bean.setClassObj(classObj);
        bean.setName(beanName);
        if (singleton) {
          bean.setSingleton(true);
          bean.setObj(object);
        } else {
          bean.setSingleton(false);
          bean.setObj(null);
          // 不需要级联注入,当获取class类时,使用时动态注入.
        }
        this.beanFactory.addBean(beanName, bean);
      }
    }
  }

  @Override
  public final void addBean(String name, Bean bean) {
    this.beanFactory.addBean(name, bean);
  }

  @Override
  public final Bean getBean(String name) {
    return this.beanFactory.getBean(name);
  }

  @Override
  public final Object getObject(String name) {
    return this.beanFactory.getObject(name);
  }
}
