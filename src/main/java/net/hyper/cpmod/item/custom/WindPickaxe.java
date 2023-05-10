package net.hyper.cpmod.item.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class WindPickaxe extends PickaxeItem {


    public WindPickaxe(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42961_, p_42962_, p_42963_, p_42964_);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pBlockState, BlockPos pBlockPos, LivingEntity p_41002_) {
        p_41002_.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200), p_41002_);
        return super.mineBlock(pStack, pLevel, pBlockState, pBlockPos, p_41002_);
    }
}


