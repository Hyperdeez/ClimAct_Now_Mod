package net.hyper.cpmod.item.custom;


import net.hyper.cpmod.entity.custom.BasicMagicProjectileEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WindWand extends Item {
    public WindWand(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.getCooldowns().addCooldown(this, 30);
        if (!level.isClientSide) {
            BasicMagicProjectileEntity magicProjectile = new BasicMagicProjectileEntity(level, player);
            magicProjectile.setType(BasicMagicProjectileEntity.MagicProjectileType.WIND);
            magicProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.5F);
            level.addFreshEntity(magicProjectile);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
