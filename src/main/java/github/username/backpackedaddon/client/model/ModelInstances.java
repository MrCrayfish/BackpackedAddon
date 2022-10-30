package github.username.backpackedaddon.client.model;

import com.mrcrayfish.backpacked.client.model.BackpackModel;
import github.username.backpackedaddon.Constants;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelInstances
{
    // Declare backpack models here
    private static BackpackModel custom;

    @SubscribeEvent
    public static void onLoadModels(EntityRenderersEvent.AddLayers event)
    {
        // Initialize them here
        EntityModelSet models = event.getEntityModels();
        custom = new CustomBackpackModel(models.bakeLayer(LayerDefinitions.CUSTOM));
    }

    // Create a getter for each of your models
    public static BackpackModel getCustom()
    {
        return custom;
    }
}
