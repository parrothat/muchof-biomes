package net.mcreator.muchofbiomes.mixin;

import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.core.Holder;

import net.mcreator.muchofbiomes.init.MuchofBiomesModBiomes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin implements MuchofBiomesModBiomes.MuchofBiomesModNoiseGeneratorSettings {
	@Unique
	private Holder<DimensionType> muchof_biomes_dimensionTypeReference;

	@WrapMethod(method = "surfaceRule")
	public SurfaceRules.RuleSource surfaceRule(Operation<SurfaceRules.RuleSource> original) {
		SurfaceRules.RuleSource retval = original.call();
		if (this.muchof_biomes_dimensionTypeReference != null) {
			retval = MuchofBiomesModBiomes.adaptSurfaceRule(retval, this.muchof_biomes_dimensionTypeReference);
		}
		return retval;
	}

	@Override
	public void setmuchof_biomesDimensionTypeReference(Holder<DimensionType> dimensionType) {
		this.muchof_biomes_dimensionTypeReference = dimensionType;
	}
}