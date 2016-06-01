package shoes.client;

import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import shoes.common.Shoes;

import java.io.InputStream;

public final class ShaderBuilder{
  private final ShoesTessellator parent;
  private final String name;

  private ResourceLocation vertex;
  private ResourceLocation fragment;

  public ShaderBuilder(ShoesTessellator parent, String name){
    this.parent = parent;
    this.name = name;
  }

  public ShaderBuilder setVertex(String mod, String name){
    this.vertex = new ResourceLocation(mod, "shaders/" + name + ".vsh");
    return this;
  }

  public ShaderBuilder setFragment(String mod, String name){
    this.fragment = new ResourceLocation(mod, "shaders/" + name + ".fsh");
    return this;
  }

  public ShoesTessellator compile(){
    this.parent.shaders.put(this.name, compileShader(this.fragment, this.vertex));
    return this.parent;
  }

  private static int compileShader(ResourceLocation fsh, ResourceLocation vsh){
    int prog = 0;
    try{
      int vertex = createShader(vsh, ARBVertexShader.GL_VERTEX_SHADER_ARB);
      int frag = createShader(fsh, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
      prog = ARBShaderObjects.glCreateProgramObjectARB();
      ARBShaderObjects.glAttachObjectARB(prog, vertex);
      ARBShaderObjects.glAttachObjectARB(prog, frag);
      ARBShaderObjects.glLinkProgramARB(prog);
      ARBShaderObjects.glValidateProgramARB(prog);
    } catch(Exception e){
      throw new RuntimeException(e);
    }
    return prog;
  }

  private static int createShader(ResourceLocation code, int type){
    int shader = 0;

    try(InputStream in = Shoes.proxy.client().getResourceManager().getResource(code).getInputStream()){
      shader = ARBShaderObjects.glCreateShaderObjectARB(type);
      if(shader == 0) throw new IllegalStateException("Couldn't create shader");
      ARBShaderObjects.glShaderSourceARB(shader, IOUtils.toString(in));
      ARBShaderObjects.glCompileShaderARB(shader);

      if(ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE){
        throw new IllegalStateException("Cannot compile shader: " + code.toString());
      }

      return shader;
    } catch(Exception e){
      throw new RuntimeException(e);
    }
  }
}