package shoes.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

public final class ShoesTessellator
extends Tessellator {
  protected final Map<String, Integer> shaders = new HashMap<>();

  public ShoesTessellator() {
    super(0x20000);
  }

  public ShaderBuilder createShader(String name) {
    return new ShaderBuilder(this, name);
  }

  public ShoesTessellator createBaseShader(String mod, String name) {
    return this.createShader(name)
               .setVertex(mod, name)
               .setFragment(mod, name)
               .compile();
  }

  public ShoesTessellator applyShader(String name) {
    if (!this.shaders.containsKey(name)) return this;
    ARBShaderObjects.glUseProgramObjectARB(this.shaders.get(name));
    return this;
  }

  public ShoesTessellator drawCubeAt(double x, double y, double z, int color, int alpha){
    for(EnumFacing face : EnumFacing.values()){
      this.drawQuadOnFace(x, y, z, face, color, alpha);
    }
    return this;
  }

  public ShoesTessellator drawQuadOnFace(double x, double y, double z, EnumFacing face, int color, int alpha) {
    int r = (color >> 16 & 0xFF);
    int g = (color >> 8 & 0xFF);
    int b = (color & 0xFF);

    VertexBuffer vb = this.getBuffer();
    vb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
    switch (face) {
      case UP: {
        vb.pos(x, y + 1, z)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x, y + 1, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x + 1, y + 1, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x + 1, y + 1, z)
          .color(r, g, b, alpha)
          .endVertex();
        break;
      }
      case DOWN: {
        vb.pos(x + 1, y, z)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x + 1, y, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x, y, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x, y, z)
          .color(r, g, b, alpha)
          .endVertex();
        break;
      }
      case EAST: {
        vb.pos(x + 1, y, z)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x + 1, y + 1, z)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x + 1, y + 1, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x + 1, y, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        break;
      }
      case NORTH: {
        vb.pos(x, y, z)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x, y + 1, z)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x + 1, y + 1, z)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x + 1, y, z)
          .color(r, g, b, alpha)
          .endVertex();
        break;
      }
      case SOUTH: {
        vb.pos(x + 1, y, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x + 1, y + 1, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x, y + 1, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x, y, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        break;
      }
      case WEST: {
        vb.pos(x, y, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x, y + 1, z + 1)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x, y + 1, z)
          .color(r, g, b, alpha)
          .endVertex();
        vb.pos(x, y, z)
          .color(r, g, b, alpha)
          .endVertex();
        break;
      }
      default: break;
    }

    this.draw();
    return this;
  }
}