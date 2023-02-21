package dev.sefiraat.cultivation.api.slimefun.items.bushes;

import dev.sefiraat.cultivation.Cultivation;
import dev.sefiraat.cultivation.api.interfaces.CultivationHarvestable;
import dev.sefiraat.cultivation.api.slimefun.plant.Growth;
import io.github.bakedlibs.dough.collections.RandomizedSet;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This bush can be harvested by right-clicking with an open hand
 * The bush then reverts to its first stage.
 */
public class HarvestableBush extends CultivationBush implements CultivationHarvestable {

    private final RandomizedSet<ItemStack> harvestItems = new RandomizedSet<>();

    @ParametersAreNonnullByDefault
    public HarvestableBush(SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Growth growth) {
        super(item, recipeType, recipe, growth);
    }

    @Nonnull
    public HarvestableBush addHarvestingResult(@Nonnull ItemStack harvestStack) {
        return addHarvestingResult(harvestStack, 1);
    }

    @Nonnull
    public HarvestableBush addHarvestingResult(@Nonnull ItemStack harvestStack, int weight) {
        this.harvestItems.add(harvestStack, weight);
        return this;
    }

    @Override
    public RandomizedSet<ItemStack> getHarvestingResults() {
        return this.harvestItems;
    }

    @Override
    protected void updateGrowthStage(@NotNull Block block, int growthStage) {
        // TODO to be added as part of bush development
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    protected boolean validateFlora() {
        if (this.harvestItems.size() == 0) {
            Cultivation.logWarning(this.getId() + " has no ItemStack for harvesting, it will not be registered.");
            return false;
        }
        return true;
    }
}
