package shoes.common.injector.binding;

import shoes.common.injector.Binding;
import shoes.common.injector.Linker;
import shoes.common.injector.Provider;

public final class ProviderBinding<T>
implements Binding<T> {
  private final Provider<T> provider;

  public ProviderBinding(Provider<T> provider){
    this.provider = provider;
  }

  @Override
  public T get() {
    return this.provider.get();
  }

  @Override public void link(Linker linker) {}
}