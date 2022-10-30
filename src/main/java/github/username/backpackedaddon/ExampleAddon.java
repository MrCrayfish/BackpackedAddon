package github.username.backpackedaddon;

import com.mrcrayfish.backpacked.common.BackpackManager;
import github.username.backpackedaddon.backpack.CustomBackpack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Author: MrCrayfish
 */
@Mod(Constants.ID)
public class ExampleAddon
{
    public ExampleAddon()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            // Register you backpacks here
            BackpackManager.instance().register(new CustomBackpack());
        });
    }
}
