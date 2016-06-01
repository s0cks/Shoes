package shoes.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import shoes.common.injector.Injector;
import shoes.common.injector.InjectorFactory;

@Mod(
modid = "shoes",
name = "Shoes",
version = "0.0.0.0"
)
public final class Shoes {
  @Mod.Instance("shoes")
  public static Shoes instance;

  @SidedProxy(
  clientSide = "shoes.client.ClientProxy",
  serverSide = "shoes.common.CommonProxy"
  )
  public static CommonProxy proxy;

  public static final Injector injector = InjectorFactory.create(new ShoesModule());

  @Mod.EventHandler
  public void onPreInit(FMLPreInitializationEvent e) {

  }

  @Mod.EventHandler
  public void onInit(FMLInitializationEvent e) {

  }

  @Mod.EventHandler
  public void onPostInit(FMLPostInitializationEvent e) {

  }

  @Mod.EventHandler
  public void onServerStarting(FMLServerStartingEvent e) {

  }
}