package net.hyper.cpmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.fml.common.Mod;

import static net.hyper.cpmod.item.ModItems.WIND_MAGIC;

public class ModTiers {
    public static final ForgeTier LIGHT = new ForgeTier(3, 1100, 3f, 3f,
            22, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.LIGHT_MAGIC.get()));
    public static final ForgeTier WIND = new ForgeTier(3, 1300, 3f, 2.5f,
            22, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ModItems.WIND_MAGIC.get()));
}

