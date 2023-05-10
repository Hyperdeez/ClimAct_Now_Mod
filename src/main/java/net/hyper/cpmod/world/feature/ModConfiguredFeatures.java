package net.hyper.cpmod.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.hyper.cpmod.CulMod;
import net.hyper.cpmod.block.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

import java.util.List;

import static net.minecraft.data.worldgen.features.FeatureUtils.register;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PILE_OF_TRASH_KEY = registerKey("pile_of_trash");

    public static final Supplier<List<OreConfiguration.TargetBlockState>> PILE_OF_TRASH = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.SAND), ModBlocks.PIlE_OF_TRASH.get().defaultBlockState())));

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        register(context, PILE_OF_TRASH_KEY, Feature.ORE, new OreConfiguration(PILE_OF_TRASH.get(),10));



    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey (String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(CulMod.MOD_ID, name));
    }
}