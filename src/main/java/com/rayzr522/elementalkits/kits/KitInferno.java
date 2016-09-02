
package com.rayzr522.elementalkits.kits;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.rayzr522.elementalkits.ElementalKits;
import com.rayzr522.elementalkits.utils.item.ItemUtils;

public class KitInferno extends Kit {

	private HashMap<UUID, Long>		cooldowns	= new HashMap<>();
	private static final ItemStack	bone		= ItemUtils.makeItem("bone, named &6Fire &cWand");
	public static final long		cooldown	= 6000;

	public KitInferno(ElementalKits plugin) {
		super(plugin);
	}

	@Override
	public void init() {
	}

	@Override
	public List<ItemStack> getItems() {
		return Arrays.asList(bone);
	}

	private long lastTime(Player p) {
		return cooldowns.containsKey(p.getUniqueId()) ? cooldowns.get(p.getUniqueId()) : -1;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {

		System.out.println("STuff1");
		if (!e.getAction().toString().startsWith("LEFT_CLICK")) { return; }
		System.out.println("STuff2");

		long now = System.currentTimeMillis();
		Player p = e.getPlayer();
		long last = lastTime(p) == -1 ? now - cooldown : lastTime(p);

		if (now - last < cooldown) {
			p.playSound(p.getLocation(), Sound.ENCHANT_THORNS_HIT, 1.0f, 1.0f);
			return;
		}

		cooldowns.put(p.getUniqueId(), now);
		p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.FIREBALL).setVelocity(p.getLocation().getDirection());

	}

}
