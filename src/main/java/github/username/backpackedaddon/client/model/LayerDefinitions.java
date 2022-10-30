package github.username.backpackedaddon.client.model;

import github.username.backpackedaddon.Constants;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LayerDefinitions
{
    // Create your layer definitions here for each backpack
    public static final ModelLayerLocation CUSTOM = register("custom");

    private static ModelLayerLocation register(String name)
    {
        return new ModelLayerLocation(new ResourceLocation(Constants.ID, name), "main");
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        // Register them here
        event.registerLayerDefinition(CUSTOM, CustomBackpackModel::createLayer);
    }
}
