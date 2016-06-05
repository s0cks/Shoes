package shoes.common.math;

import net.minecraft.client.renderer.GlStateManager;

import javax.vecmath.Vector3f;

public final class Quaternion{
  private float w;
  private float x;
  private float y;
  private float z;

  public Quaternion(float w, float x, float y, float z) {
    this.w = w;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Quaternion conjugate(){
    this.x = -this.x;
    this.y = -this.y;
    this.z = -this.z;
    return this;
  }

  public Quaternion identity(){
    this.x = this.y = this.z = 0;
    this.w = 1;
    return this;
  }

  public Quaternion normalize(){
    double factor = Math.sqrt(this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z);
    this.x /= factor;
    this.y /= factor;
    this.z /= factor;
    this.w /= factor;
    return this;
  }

  public void apply(){
    GlStateManager.rotate(this.w, this.x, this.y, this.z);
  }

  public static Quaternion of(Vector3f vec, float angle){
    float factor = ((float) Math.sin(angle / 2.0F));
    float w = ((float) Math.cos(angle / 2.0F));
    return new Quaternion(vec.getX() * factor, vec.getY() * factor, vec.getZ() * factor, w).normalize();
  }
}