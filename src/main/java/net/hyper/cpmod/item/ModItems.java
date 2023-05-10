package net.hyper.cpmod.item;

import net.hyper.cpmod.CulMod;
import net.hyper.cpmod.item.custom.WaterArmorItem;
import net.hyper.cpmod.item.custom.LuckSwordItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CulMod.MOD_ID);

    public static final RegistryObject<Item> WORLD_THERMOMETER = ITEMS.register("world_thermometer",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLASTIC_BAG = ITEMS.register("plastic_bag",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_SCRAP = ITEMS.register("metal_scrap",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LIGHT_MAGIC = ITEMS.register("light_magic",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WATER_MAGIC = ITEMS.register("water_magic",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> WIND_MAGIC = ITEMS.register("wind_magic",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WATER_HELMET = ITEMS.register("water_helmet",
            () -> new WaterArmorItem(ModArmorMaterials.WATER, ArmorItem.Type.HELMET,
                    new Item.Properties()));
    public static final RegistryObject<Item> WATER_CHESTPLATE = ITEMS.register("water_chestplate",
            () -> new ArmorItem(ModArmorMaterials.WATER, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));
    public static final RegistryObject<Item> WATER_LEGGING = ITEMS.register("water_legging",
            () -> new ArmorItem(ModArmorMaterials.WATER, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));
    public static final RegistryObject<Item> WATER_BOOTS = ITEMS.register("water_boots",
            () -> new ArmorItem(ModArmorMaterials.WATER, ArmorItem.Type.BOOTS,
                    new Item.Properties()));


    public static final RegistryObject<Item> WIND_PICKAXE = ITEMS.register("wind_pickaxe",
            () -> new PickaxeItem(ModTiers.WIND, 5, 4f,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> LIGHT_SWORD = ITEMS.register("light_sword",
            () -> new LuckSwordItem(ModTiers.LIGHT, 12, 3f,
                    new Item.Properties().stacksTo(1)));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
