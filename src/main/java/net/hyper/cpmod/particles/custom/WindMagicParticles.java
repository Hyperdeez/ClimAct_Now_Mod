package net.hyper.cpmod.particles.custom;


import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hyper.cpmod.entity.custom.BasicMagicProjectileEntity;
import net.hyper.cpmod.util.MagicColorUtils;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector4f;

public abstract class WindMagicParticles extends Particle {
    protected WindMagicParticles(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet  spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord);

        Vector4f colorVector = MagicColorUtils.PROJECTILE_VECTOR.get(BasicMagicProjectileEntity.MagicProjectileType.WIND);
        this.rCol = colorVector.x;
        this.gCol = colorVector.y;
        this.bCol = colorVector.z;
        this.alpha = colorVector.w;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return null;
    }
    @Override
    public void render(VertexConsumer p_107261_, Camera p_107262_, float p_107263_) {

    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {

            return new WindMagicParticles(level, x, y, z, this.sprites, dx, dy, dz) {


            };
        }
    }
}
