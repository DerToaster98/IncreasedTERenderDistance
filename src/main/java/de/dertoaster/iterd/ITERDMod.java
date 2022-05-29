package de.dertoaster.iterd;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dertoaster.iterd.config.ITERDConfig;
import me.lortseam.completeconfig.gui.ConfigScreenBuilder;
import me.lortseam.completeconfig.gui.cloth.ClothConfigScreenBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class ITERDMod implements ModInitializer, ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("iterd");
	
	public static final ITERDConfig CONFIG = new ITERDConfig();
	
	public static Map<String, Integer> CONFIG_MAP = null;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onInitializeClient() {
		CONFIG.load();
		
		CONFIG.getEntries().forEach((entry) -> {
			if(entry.getValue() instanceof Map) {
				try {
					CONFIG_MAP = (Map<String, Integer>)entry.getValue();
				} catch(ClassCastException ex) {
					//Ignore
				}
			}
		});
		
		ConfigScreenBuilder.setMain("iterd", new ClothConfigScreenBuilder());
	}
}
