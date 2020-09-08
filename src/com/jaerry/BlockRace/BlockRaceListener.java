package com.jaerry.BlockRace;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class BlockRaceListener implements Listener {
	public static Material target;
	public static ArrayList<Player> players;
	
	public BlockRaceListener(BlockRace plugin) {
		players = new ArrayList<Player>();
		target = Material.SPONGE;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location below = (player.getLocation());
		below.setY(below.getY()-1);
		if (below.getBlock().getType() == target) {
			Bukkit.getServer().broadcastMessage(player.getDisplayName() + " has found " + below.getBlock().getType().toString());
			below.getBlock().setType(Material.LAVA);
			Firework fw = (Firework) player.getWorld().spawnEntity(below, EntityType.FIREWORK); 
			FireworkMeta fwm = fw.getFireworkMeta();
			FireworkEffect effect = FireworkEffect.builder().with(Type.BALL).withColor(Color.GREEN).build();
			fwm.addEffect(effect);
			fw.setFireworkMeta(fwm);
			Bukkit.getServer().broadcastMessage(player.getDisplayName() + " is the winner");
		}	
	}

}
