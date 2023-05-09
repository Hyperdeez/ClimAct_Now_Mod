package net.hyper.cpmod.fluid;

import net.hyper.cpmod.CulMod;
import net.hyper.cpmod.block.ModBlocks;
import net.hyper.cpmod.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluid {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, CulMod.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_BLACK_WATER = FLUIDS.register("black_water_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluid.BLACK_WATER_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_BLACK_WATER = FLUIDS.register("flowing_black_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluid.BLACK_WATER_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties BLACK_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.BLACK_WATER_FLUID_TYPE, SOURCE_BLACK_WATER, FLOWING_BLACK_WATER)
            .slopeFindDistance(2).levelDecreasePerBlock(1).block(ModBlocks.BLACK_WATER_BLOCK).bucket(ModItems.BLACK_WATER_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
