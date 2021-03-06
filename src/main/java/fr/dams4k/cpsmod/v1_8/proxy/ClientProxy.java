package fr.dams4k.cpsmod.v1_8.proxy;

import org.lwjgl.input.Keyboard;

import fr.dams4k.cpsmod.v1_8.commands.ConfigCommand;
import fr.dams4k.cpsmod.v1_8.config.ModConfig;
import fr.dams4k.cpsmod.v1_8.events.EventHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	public static final KeyBinding CPS_OVERLAY_CONFIG = new KeyBinding("key.opengui", Keyboard.KEY_P, "category.cpsmod");
	
	@Override
	public void preInit() {
		ModConfig.preInit();
	}

	@Override
	public void init() {
		ClientRegistry.registerKeyBinding(CPS_OVERLAY_CONFIG);
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		MinecraftForge.EVENT_BUS.register(new ModConfig());
		ClientCommandHandler.instance.registerCommand(new ConfigCommand());
	}
}
