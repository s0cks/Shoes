package shoes.common.injector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Key<T> {
  private static final Map<Type, Type> boxes = new HashMap<>();

  static {
    boxes.put(int.class, Integer.class);
    boxes.put(boolean.class, Boolean.class);
    boxes.put(short.class, Short.class);
    boxes.put(long.class, Long.class);
    boxes.put(double.class, Double.class);
    boxes.put(float.class, Float.class);
    boxes.put(void.class, Void.class);
    boxes.put(byte.class, Byte.class);
  }

  public static <T> Key<T> from(Class<T> clazz){
    if(boxes.containsKey(clazz)) clazz = ((Class<T>) boxes.get(clazz));
    return new Key<>(clazz, clazz.getDeclaredAnnotations());
  }

  public static <T> Key<T> from(Class<T> tClass, Annotation... annotations){
    if(boxes.containsKey(tClass)) tClass = ((Class<T>) boxes.get(tClass));
    return new Key<>(tClass, annotations);
  }

  public static <T> Key<T> from(Type t){
    if(boxes.containsKey(t)) t = boxes.get(t);
    return new Key<>(t, Types.getRawType(t).getAnnotations());
  }

  public static <T> Key<T> from(Type t, Annotation... annotations){
    if(boxes.containsKey(t)) t = boxes.get(t);
    return new Key<>(t, annotations);
  }

  public final Class<? super T> rawType;
  public final Type type;
  public final Annotation[] annotations;
  private final int hash;

  public Key(Type type, Annotation[] annotations) {
    this.type = type;
    this.annotations = annotations;
    this.rawType = ((Class<? super T>) Types.getRawType(type));
    this.hash = this.type.hashCode() * 31;
  }

  @Override
  public int hashCode() {
    return this.hash;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Key<?>)) return false;

    Key<?> other = ((Key<?>) o);
    return Types.equals(this.type, other.type)
           && Arrays.equals(this.annotations, other.annotations);
  }
}