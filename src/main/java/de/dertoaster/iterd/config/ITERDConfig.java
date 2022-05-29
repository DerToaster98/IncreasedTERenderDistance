package de.dertoaster.iterd.config;

import java.util.HashMap;
import java.util.Map;

import me.lortseam.completeconfig.api.ConfigContainer;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.api.ConfigGroup;
import me.lortseam.completeconfig.data.Config;

public class ITERDConfig extends Config implements ConfigGroup{

	public ITERDConfig() {
		super("iterd", new ITERDConfigContainer());
	}
	
	public static class ITERDConfigContainer implements ConfigContainer {
		
		@ConfigEntry
		private Map<String, Integer> renderDistances = new HashMap<>();
		
	}
	

}
