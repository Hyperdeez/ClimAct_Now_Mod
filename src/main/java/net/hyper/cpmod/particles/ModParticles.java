package net.hyper.cpmod.particles;

import net.hyper.cpmod.CulMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, CulMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> MAGIC_PARTICLES =
            PARTICLE_TYPES.register("magic_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> WIND_MAGIC_PARTICLES =
            PARTICLE_TYPES.register("wind_magic_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
