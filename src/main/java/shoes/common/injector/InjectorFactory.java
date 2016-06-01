package shoes.common.injector;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;

public final class InjectorFactory{
  private InjectorFactory(){}

  public static Injector create(Module... modules){
    return new InjectorImpl(Arrays.asList(modules));
  }

  private static final class InjectorImpl
  implements Injector{
    private final Linker linker = new Linker();

    private InjectorImpl(Iterable<Module> modules){
      for(Module module : modules){
        module.configure();
        module.putBindings(this.linker);
      }
    }

    @Override
    public <T> T get(Key<T> tKey) {
      return this.linker.request(tKey).get();
    }

    @Override
    public <T> T get(Class<T> tClass) {
      return this.linker.request(Key.from(tClass)).get();
    }

    @Override
    public boolean hasBinding(Key<?> tKey) {
      return this.linker.bindings.containsKey(tKey);
    }

    @Override
    public boolean hasBinding(Class<?> tClass) {
      return this.linker.bindings.containsKey(Key.from(tClass));
    }

    @Override
    public List<Binding<?>> bindings() {
      return ImmutableList.copyOf(this.linker.bindings.values());
    }
  }
}