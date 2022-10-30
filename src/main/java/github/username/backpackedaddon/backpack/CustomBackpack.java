package github.username.backpackedaddon.backpack;

import com.mrcrayfish.backpacked.common.Backpack;
import com.mrcrayfish.backpacked.common.IProgressTracker;
import com.mrcrayfish.backpacked.common.ProgressFormatters;
import com.mrcrayfish.backpacked.common.UnlockTracker;
import com.mrcrayfish.backpacked.common.tracker.CountProgressTracker;
import github.username.backpackedaddon.Constants;
import github.username.backpackedaddon.client.model.ModelInstances;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Constants.ID)
public class CustomBackpack extends Backpack
{
    public static final ResourceLocation ID = new ResourceLocation(Constants.ID, "example");

    public CustomBackpack()
    {
        super(ID);
    }

    @Override
    public Supplier<Object> getModelSupplier()
    {
        return ModelInstances::getCustom; // Use the getter of your model in the form of a supplier
    }

    @Nullable
    @Override
    protected IProgressTracker createProgressTracker()
    {
        // The type of progress tracker, in this case a simple counter. Once reaches 200, backpack will be unlocked.
        return new CountProgressTracker(200, ProgressFormatters.USED_X_TIMES); // Try different formatters or create your own
    }

    // Increments the counter of this backpack when player uses a bow. You should only access progress trackers on servers.
    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent.Stop event)
    {
        if(event.getEntity() instanceof ServerPlayer player)
        {
            if(event.getItem().getItem() == Items.BOW)
            {
                UnlockTracker.get(player).ifPresent(unlockTracker ->
                {
                    unlockTracker.getProgressTracker(ID).ifPresent(progressTracker ->
                    {
                        CountProgressTracker tracker = (CountProgressTracker) progressTracker;
                        tracker.increment(player);
                    });
                });
            }
        }
    }
}
