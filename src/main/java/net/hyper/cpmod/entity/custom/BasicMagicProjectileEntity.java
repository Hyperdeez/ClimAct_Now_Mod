package net.hyper.cpmod.entity.custom;

import net.hyper.cpmod.entity.ModEntities;
import net.hyper.cpmod.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class BasicMagicProjectileEntity extends Projectile {
    public enum MagicProjectileType {
       WIND
    }

    private static final EntityDataAccessor<Integer> TYPE =
            SynchedEntityData.defineId(BasicMagicProjectileEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HIT =
            SynchedEntityData.defineId(BasicMagicProjectileEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> COUNT =
            SynchedEntityData.defineId(BasicMagicProjectileEntity.class, EntityDataSerializers.INT);

    public BasicMagicProjectileEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    public BasicMagicProjectileEntity(Level level, Player player) {
        this(ModEntities.MAGIC_PROJECTILE.get(), level);
        setOwner(player);
        BlockPos blockpos = player.blockPosition();
        double d0 = (double)blockpos.getX() + 0.5D;
        double d1 = (double)blockpos.getY() + 1.75D;
        double d2 = (double)blockpos.getZ() + 0.5D;
        this.moveTo(d0, d1, d2, this.getYRot(), this.getXRot());
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TYPE, 0);
        this.entityData.define(COUNT, 0);
        this.entityData.define(HIT, false);
    }

    public void setType(MagicProjectileType type) {
        this.entityData.set(TYPE, type.ordinal());
    }

    public int getProjectileType() {
        return this.entityData.get(TYPE);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.entityData.get(HIT)) {
            this.entityData.set(COUNT, this.entityData.get(COUNT) + 1);
            if(this.entityData.get(COUNT) >= 5) {
                this.destroy();
            }
        }
        if (this.tickCount >= 300) {
            this.remove(RemovalReason.DISCARDED);
        }
        Vec3 vec3 = this.getDeltaMovement();
        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult))
            this.onHit(hitresult);
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();

        double d5 = vec3.x;
        double d6 = vec3.y;
        double d7 = vec3.z;

        for(int i = 1; i < 5; ++i) {
            this.level.addParticle(getParticleType(), d0-(d5*2), d1-(d6*2), d2-(d7*2),
                    -d5, -d6 - 0.1D, -d7);
        }

        if (this.level.getBlockStates(this.getBoundingBox()).noneMatch(BlockBehaviour.BlockStateBase::isAir)) {
            this.discard();
        } else if (this.isInWaterOrBubble()) {
            this.discard();
        } else {
            this.setDeltaMovement(vec3.scale(0.99F));
            this.setPos(d0, d1, d2);
        }
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public SimpleParticleType getParticleType() {
        return switch (this.entityData.get(TYPE)) {
            default -> ModParticles.WIND_MAGIC_PARTICLES.get();
        };
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity hitEntity = hitResult.getEntity();
        Entity owner = this.getOwner();

        if(hitEntity == owner && this.level.isClientSide()) {
            return;
        }



        LivingEntity livingentity = owner instanceof LivingEntity ? (LivingEntity)owner : null;
        float damage = switch (this.entityData.get(TYPE)) {
            default -> 3f;
            case 1 -> 9f;

        };
        boolean hurt = hitEntity.hurt(this.damageSources().mobProjectile(this, livingentity), damage);

        if (hurt) {
            if(this.entityData.get(TYPE) == 5) {
                if(hitEntity instanceof LivingEntity livingHitEntity) {
                    livingHitEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 60), owner);
                }
            }

            if(this.entityData.get(TYPE) == 6) {
                if(hitEntity instanceof LivingEntity livingHitEntity) {
                    livingHitEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1), owner);
                }
            }
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        for(int x = 0; x < 18; ++x) {
            for(int y = 0; y < 18; ++y) {
                this.level.addParticle(getParticleType(), this.getX(), this.getY(), this.getZ(),
                        Math.cos(x*20) * 0.15d, Math.cos(y*20) * 0.15d, Math.sin(x*20) * 0.15d);
            }
        }

        if(this.level.isClientSide()) {
            return;
        }

        if(hitResult.getType() == HitResult.Type.ENTITY && hitResult instanceof EntityHitResult entityHitResult) {
            Entity hit = entityHitResult.getEntity();
            Entity owner = this.getOwner();
            if(owner != hit) {
                this.entityData.set(HIT, true);
            }
        } else {
            this.entityData.set(HIT, true);
        }

        //this.destroy();
    }

    private void destroy() {
        this.discard();
        this.level.gameEvent(GameEvent.ENTITY_DAMAGE, this.position(), GameEvent.Context.of(this));
    }
}
