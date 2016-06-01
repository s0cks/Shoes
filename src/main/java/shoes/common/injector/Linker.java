package shoes.common.injector;

import shoes.common.injector.binding.ReflectiveInjectionBinding;

import java.util.HashMap;
import java.util.Map;

public final class Linker{
  protected final Map<Key<?>, Binding<?>> bindings = new HashMap<>();

  public <T> Binding<T> request(Key<T> tKey){
    Binding<T> tBinding = ((Binding<T>) this.bindings.get(tKey));
    if(tBinding != null){
      tBinding.link(this);
      return tBinding;
    }

    if(tKey.annotations != null && tKey.annotations.length > 0) throw new IllegalStateException("Cannot create binding for " + tKey.rawType.getName());
    tBinding = ((Binding<T>) ReflectiveInjectionBinding.create(tKey.rawType));
    tBinding.link(this);
    return tBinding;
  }
}