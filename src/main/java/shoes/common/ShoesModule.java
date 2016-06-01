package shoes.common;

import net.minecraft.client.renderer.Tessellator;
import shoes.client.ShoesTessellator;
import shoes.common.injector.Module;

final class ShoesModule
extends Module {
  @Override
  protected void configure() {
    this.bind(Tessellator.class)
        .toInstance(new ShoesTessellator());
  }
}