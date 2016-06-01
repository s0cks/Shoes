package shoes.client;

import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.ARBShaderObjects;

import java.util.HashMap;
import java.util.Map;

public final class ShoesTessellator
extends Tessellator{
  protected final Map<String, Integer> shaders = new HashMap<>();

  public ShoesTessellator(){
    super(0x20000);
  }

  public ShaderBuilder createShader(String name){
    return new ShaderBuilder(this, name);
  }

  public ShoesTessellator createBaseShader(String mod, String name){
    return this.createShader(name).setVertex(mod, name).setFragment(mod, name).compile();
  }

  public ShoesTessellator applyShader(String name){
    if(!this.shaders.containsKey(name)) return this;
    ARBShaderObjects.glUseProgramObjectARB(this.shaders.get(name));
    return this;
  }
}