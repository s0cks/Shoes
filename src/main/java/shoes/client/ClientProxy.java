package shoes.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import shoes.common.CommonProxy;

public final class ClientProxy
extends CommonProxy{
  @Override
  public Minecraft client() {
    return FMLClientHandler.instance().getClient();
  }
}