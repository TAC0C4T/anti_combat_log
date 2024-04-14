package dev.tac0c4t.spigot.anticl;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Entity;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.entity.Player;;

public class CombatListener implements Listener{
    private final App plugin;

    public CombatListener(App plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity defender = event.getEntity();

        plugin.startCombat(attacker, defender);

    }

    @EventHandler
    public void onPlayerDeath(EntityDeathEvent event){
         Entity player = event.getEntity();
         if (plugin.isInCombat(player)) {
            plugin.endCombat(player);
            Bukkit.broadcastMessage("has died in combat!");
         }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        if (plugin.isInCombat(player)) {
            Bukkit.broadcastMessage(player.getName() + " has left the game in combat!");
            plugin.sentence(player);
        }

        plugin.endCombat(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.isSentenced(player)) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "kill" + " " + player.getName());
            Bukkit.broadcastMessage(player.getName() + "has rejoined after combat logging");
            plugin.pardon(player);
        }
    }
}
