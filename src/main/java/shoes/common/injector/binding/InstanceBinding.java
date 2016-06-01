package shoes.common.injector.binding;

import shoes.common.injector.Binding;
import shoes.common.injector.Linker;

public final class InstanceBinding<T>
implements Binding<T> {
  private final T instance;

  public InstanceBinding(T instance){
    this.instance = instance;
  }

  @Override
  public T get() {
    return this.instance;
  }

  @Override
  public void link(Linker linker) {

  }
}