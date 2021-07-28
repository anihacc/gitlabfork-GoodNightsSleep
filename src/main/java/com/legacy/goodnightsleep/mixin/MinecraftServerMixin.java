package com.legacy.goodnightsleep.mixin;

//@Mixin(MinecraftServer.class)
public class MinecraftServerMixin
{
	/*@Shadow
	protected RegistryAccess.RegistryHolder registryHolder;
	@Shadow
	protected WorldData worldData;*/

	/*
	 * MinecraftServer#loadLevel
	 */
	/*@Inject(at = @At("HEAD"), method = "loadLevel()V")
	private void initServer(CallbackInfo callback)
	{
		GNSDimensions.init(this.worldData.worldGenSettings().dimensions(), this.registryHolder.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY), this.registryHolder.registryOrThrow(Registry.BIOME_REGISTRY), this.registryHolder.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY), this.worldData.worldGenSettings().seed());
	}*/
}
