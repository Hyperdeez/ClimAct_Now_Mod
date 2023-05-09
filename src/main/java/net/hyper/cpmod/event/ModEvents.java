package net.hyper.cpmod.event;

import net.hyper.cpmod.CulMod;
import net.hyper.cpmod.entity.client.MagicProjectileModel;
import net.hyper.cpmod.entity.layers.ModModelLayers;
import net.hyper.cpmod.particles.ModParticles;
import net.hyper.cpmod.particles.custom.WindMagicParticles;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CulMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MAGIC_PROJECTILE_LAYER, MagicProjectileModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        

        event.registerSpriteSet(ModParticles.WIND_MAGIC_PARTICLES.get(), WindMagicParticles.Provider::new);

    }
}