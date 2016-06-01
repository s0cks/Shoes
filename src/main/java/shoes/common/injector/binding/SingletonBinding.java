package shoes.common.injector.binding;

import shoes.common.injector.Binding;
import shoes.common.injector.Linker;

public final class SingletonBinding<T>
implements Binding<T> {
  private static final Object UNITIALIZED = new Object();

  private final Binding<T> delegate;
  private volatile Object instance = UNITIALIZED;

  public SingletonBinding(Binding<T> delegate){
    this.delegate = delegate;
  }

  @Override
  public T get() {
    if(this.instance == UNITIALIZED) this.instance = this.delegate.get();
    return (T) this.instance;
  }

  @Override
  public void link(Linker linker) {
    this.delegate.link(linker);
  }
}