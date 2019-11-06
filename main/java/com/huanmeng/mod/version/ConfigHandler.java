package com.huanmeng.mod.version;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huanmeng.mod.Xijun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class ConfigHandler {
	private final static List<String> changes = new ArrayList<>();
	public static class adaptor {

		

		public static void tellChanges(EntityPlayer player) {
			if(changes.size() == 0)
				return;

			player.sendMessage(new TextComponentTranslation("botaniamisc.adaptativeConfigChanges").setStyle(new Style().setColor(TextFormatting.GOLD)));
			for(String change : changes)
				player.sendMessage(new TextComponentString(change).setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
		}

	}

	public static class ConfigAdaptor {

		private boolean enabled;
		private int lastBuild;
		private int currentBuild;

		private final Map<String, List<AdaptableValue>> adaptableValues = new HashMap<>();
		private final List<String> changes = new ArrayList<>();

		public ConfigAdaptor(boolean enabled) {
			this.enabled = enabled;

			String lastVersion = "2.0";
			try {
				lastBuild = Integer.parseInt(lastVersion);
				currentBuild = Integer.parseInt("GRADLE:BUILD");
			} catch(NumberFormatException e) {
				this.enabled = false;
			}
		}

		public <T> void addMapping(int version, String key, T val) {
			if(!enabled)
				return;

			AdaptableValue<T> adapt = new AdaptableValue<>(version, val);
			if(!adaptableValues.containsKey(key)) {
				adaptableValues.put(key, new ArrayList<>());
			}

			List<AdaptableValue> list = adaptableValues.get(key);
			list.add(adapt);
		}

		public boolean areEqualNumbers(Object v1, Object v2) {
			double epsilon = 1.0E-6;
			float v1f = ((Number) v1).floatValue();
			float v2f;

			if(v2 instanceof String)
				v2f = Float.parseFloat((String) v2);
			else v2f = ((Number) v2).floatValue();

			return Math.abs(v1f - v2f) < epsilon;
		}

		public void tellChanges(EntityPlayer player) {
			if(changes.size() == 0)
				return;

			player.sendMessage(new TextComponentTranslation("botaniamisc.adaptativeConfigChanges").setStyle(new Style().setColor(TextFormatting.GOLD)));
			for(String change : changes)
				player.sendMessage(new TextComponentString(change).setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
		}

		public void addMappingInt(int version, String key, int val) {
			this.addMapping(version, key, val);
		}

		public void addMappingDouble(int version, String key, double val) {
			this.addMapping(version, key, val);
		}

		public void addMappingBool(int version, String key, boolean val) {
			this.addMapping(version, key, val);
		}
		public static class AdaptableValue<T> {

			public final int version;
			public final T value;
			public final Class<? extends T> valueType;

			public AdaptableValue(int version, T value) {
				this.version = version;
				this.value = value;
				valueType = (Class<? extends T>) value.getClass();
			}

		}

	}
}