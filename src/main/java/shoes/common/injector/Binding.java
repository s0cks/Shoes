package shoes.common.injector;

public interface Binding<T>{
  public T get();
  public void link(Linker linker);
}