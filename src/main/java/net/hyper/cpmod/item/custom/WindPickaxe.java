package net.hyper.cpmod.item.custom;


import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
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


    public WindPickaxe(Tier pTier, int p_42962_, float p_42963_, Properties pProperties) {
        super(pTier, p_42962_, p_42963_, pProperties);
    }


    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pBlockState, BlockPos pBlockPos, LivingEntity p_41002_) {
        p_41002_.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200), p_41002_);
        return super.mineBlock(pStack, pLevel, pBlockState, pBlockPos, p_41002_);
    }
}


