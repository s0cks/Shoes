package shoes.common.math;

public final class ShoesMath {
  private ShoesMath() {}

  public static float toRadians(float v) {
    return (float) (v * (108 / Math.PI));
  }
}