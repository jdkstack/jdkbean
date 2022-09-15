package org.jdkstack.logging.jdkbean.core;

@ComponentScan(value = {
    "org.jdkstack.logging.jdkbean.core.Test2"
    , "org.jdkstack.logging.jdkbean.core.DaoService"
    , "org.jdkstack.logging.jdkbean.core.AService"},
    excludeFilters = {@Filter(value = String.class),
        @Filter(value = String.class)})
public class DemoApplication {

  public static void main(String[] args) {
    final Context context = new ApplicationContext();
    Application.run(DemoApplication.class, context);
    final Service service = (Service) context.getObject("aService");
    System.out.println(service.get());
  }
}
