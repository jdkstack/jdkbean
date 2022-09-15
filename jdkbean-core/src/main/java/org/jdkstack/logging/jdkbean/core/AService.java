package org.jdkstack.logging.jdkbean.core;

@Component(value = "aService", singleton = true)
public class AService extends AbTest implements Service {

  private DaoService daoService;

  @ConstructorResource(value = {"daoService", "test2"})
  public AService(DaoService daoService, Test2 test2) {
    super(test2);
    this.daoService = daoService;
  }

  @Override
  public String get() {
    return "as" + daoService.get();
  }
}
