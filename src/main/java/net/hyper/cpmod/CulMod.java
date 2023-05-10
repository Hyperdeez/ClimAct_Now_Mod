package net.hyper.cpmod;

import com.mojang.logging.LogUtils;
import net.hyper.cpmod.block.ModBlocks;
import net.hyper.cpmod.item.ModCreativeModeTab;
import net.hyper.cpmod.item.ModItems;

import net.hyper.cpmod.loot.ModLootModifiers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CulMod.MOD_ID)
public class CulMod {
    public static final String MOD_ID = "cpmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CulMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);


        ModLootModifiers.register(modEventBus);


        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {


    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ModCreativeModeTab.CP_TAB) {

            event.accept(ModItems.LIGHT_MAGIC);
            event.accept(ModItems.WATER_MAGIC);
            event.accept(ModItems.WIND_MAGIC);
            event.accept(ModItems.WATER_LEGGING);
            event.accept(ModItems.WATER_BOOTS);
            event.accept(ModItems.WATER_CHESTPLATE);
            event.accept(ModItems.WATER_HELMET);
            event.accept(ModItems.PLASTIC_BAG);
            event.accept(ModItems.METAL_SCRAP);
            event.accept(ModItems.WORLD_THERMOMETER);
            event.accept(ModItems.LIGHT_SWORD);
            event.accept(ModItems.WIND_PICKAXE);
            event.accept(ModItems.STEEL_INGOT);
            event.accept(ModBlocks.PIlE_OF_TRASH);
            event.accept(ModBlocks.STEEL_BLOCK);



        }
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {




        }
    }
}
