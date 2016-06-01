package shoes.common.injector;

import java.util.List;

public interface Injector{
  public <T> T get(Key<T> tKey);
  public <T> T get(Class<T> tClass);
  public boolean hasBinding(Key<?> tKey);
  public boolean hasBinding(Class<?> tClass);
  public List<Binding<?>> bindings();
}
