/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [18/12/2015, 02:19:53 (GMT)]
 */
package vazkii.botania.client.render.world;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.world.WorldTypeSkyblock;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.stream.IntStream;

public final class SkyblockRenderEvents {

	@SubscribeEvent
	public void onRender(RenderWorldLastEvent event) {
		World world = Minecraft.getMinecraft().theWorld;

		boolean renderFancySkybox = ConfigHandler.enableFancySkybox && (
				(ConfigHandler.enableFancySkyboxInNormalWorlds && (world.provider.dimensionId == 0) ) ||
				(ConfigHandler.enableFancySkyboxInDimension && ConfigHandler.fancySkyboxDimensions.contains(world.provider.dimensionId)) ||
				(WorldTypeSkyblock.isWorldSkyblock(Minecraft.getMinecraft().theWorld))
		);

		if(renderFancySkybox) {
			if(!(world.provider.getSkyRenderer() instanceof SkyblockSkyRenderer))
				world.provider.setSkyRenderer(new SkyblockSkyRenderer());
		}
	}
	
}
