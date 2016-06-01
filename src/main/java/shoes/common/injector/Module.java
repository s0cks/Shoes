package shoes.common.injector;

public abstract class Module{
  private final Binder binder = new Binder();

  protected abstract void configure();

  protected Binder binder(){
    return this.binder;
  }

  protected <T> BindingBuilder bind(Class<T> tClass){
    return this.binder().bind(tClass);
  }

  protected final void putBindings(Linker linker){
    this.binder.push(linker);
  }
}