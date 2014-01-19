package net.minekingdom.voidtp;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class VoidTP extends JavaPlugin {
	
	private BukkitTask task;
	
	public void onEnable() {
		
		this.saveDefaultConfig();
		
		final String upperName = this.getConfig().getString("upper");
		final String lowerName = this.getConfig().getString("lower");
		
		this.task = new BukkitRunnable() {

			@Override
			public void run() {
				World upper = VoidTP.this.getServer().getWorld(upperName);
				World lower = VoidTP.this.getServer().getWorld(lowerName);
				
				if (upper == null || lower == null) {
					return;
				}
				
				for (Player player : upper.getPlayers()) {
					if (player.getLocation().getY() < 0) {
						player.teleport(lower.getSpawnLocation());
					}
				}
			}
			
		}.runTaskTimer(this, 0L, 20L);
		
	}
	
	public void onDisable() {
		this.task.cancel();
	}
}
