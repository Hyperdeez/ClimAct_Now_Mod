package net.hyper.cpmod.util;

import net.hyper.cpmod.entity.custom.BasicMagicProjectileEntity;
import org.joml.Vector4f;

import java.util.Map;

public class MagicColorUtils {
    public static Map<BasicMagicProjectileEntity.MagicProjectileType, Vector4f> PROJECTILE_VECTOR = Map.of(
            BasicMagicProjectileEntity.MagicProjectileType.WIND, new Vector4f(0.25f, 0.65f, 0.1f, 0.8f));


    public static Map<Integer, Vector4f> PROJECTILE_VECTOR_BY_ORDINAL = Map.of(
            0, new Vector4f(0.25f, 0.65f, 0.1f, 0.8f));

}
