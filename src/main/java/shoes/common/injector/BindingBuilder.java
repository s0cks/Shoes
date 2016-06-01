package shoes.common.injector;

import shoes.common.injector.binding.InstanceBinding;
import shoes.common.injector.binding.ProviderBinding;
import shoes.common.injector.binding.ReflectiveInjectionBinding;
import shoes.common.injector.binding.SingletonBinding;

import java.lang.annotation.Annotation;

public final class BindingBuilder {
  private final Binder binder;
  private final Class<?> clazz;
  private Annotation annotation;

  public BindingBuilder(Binder binder, Class<?> clazz) {
    this.binder = binder;
    this.clazz = clazz;
  }

  public BindingBuilder annotatedWith(Annotation annotation) {
    this.annotation = annotation;
    return this;
  }

  public void asSingleton() {
    if (this.annotation != null) {
      this.binder.bindings().put(Key.from(this.clazz, this.annotation), new SingletonBinding<>
                                                                       (ReflectiveInjectionBinding.create(this.clazz)));
    } else {
      this.binder.bindings().put(Key.from(this.clazz), new SingletonBinding<>(ReflectiveInjectionBinding.create
                                                                                                        (this.clazz)));
    }
  }

  public <T> void toInstance(T instance) {
    if (this.annotation != null) {
      this.binder.bindings().put(Key.from(this.clazz, this.annotation), new InstanceBinding<>(instance));
    } else {
      this.binder.bindings().put(Key.from(this.clazz), new InstanceBinding<>(instance));
    }
  }

  public <T> void toProvider(Provider<T> provider) {
    if (this.annotation != null) {
      this.binder.bindings().put(Key.from(this.clazz, this.annotation), new ProviderBinding<>(provider));
    } else {
      this.binder.bindings().put(Key.from(this.clazz), new ProviderBinding<>(provider));
    }
  }

  public <T> void to(Class<T> t) {
    if (this.annotation != null) {
      this.binder.bindings().put(Key.from(this.clazz, this.annotation), ReflectiveInjectionBinding.create(t));
    } else {
      this.binder.bindings().put(Key.from(this.clazz), ReflectiveInjectionBinding.create(t));
    }
  }
}