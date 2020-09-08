package com.jaerry.BlockRace;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class BlockRace extends JavaPlugin {
	public static ScoreboardManager manager;
	public static Objective objective;
	public static Scoreboard board;
	public static Random rand;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		getLogger().info("BlockRace has been enabled");
		PluginManager pm = getServer().getPluginManager();
		BlockRaceListener listener = new BlockRaceListener(this);
		pm.registerEvents(listener,this);
		manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
		objective = board.registerNewObjective("test", "dummy");
		objective.setDisplayName("test");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		rand = new Random();
	}
	
	
	@Override
	public void onDisable() {
		getLogger().info("BlockRace has been disabled");
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("BlockRace")) {
			Player player;		
			if (args.length == 0) {
				if ( sender instanceof Player) {
					player = (Player) sender;
					BlockRaceListener.players.add(player);
					player.setScoreboard(board);
					return true;
				}
			} else {
				player = Bukkit.getPlayer(args[0]);
				player.sendMessage("You have been added to BlockSwap");
				BlockRaceListener.players.add(player);
				player.setScoreboard(board);
				return true;
			}
		} else if (label.equalsIgnoreCase("BRStart")) {
			int c = rand.nextInt(752);
			BlockRaceListener.target = BlockList.list[c];
			Bukkit.getServer().broadcastMessage("First to find " + BlockRaceListener.target.toString() + " wins");
			objective.setDisplayName("Find");
		}
		return false;
	}
}
