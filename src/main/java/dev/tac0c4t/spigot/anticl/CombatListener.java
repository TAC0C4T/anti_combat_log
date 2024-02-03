package dev.tac0c4t.spigot.anticl;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Entity;
import org.bukkit.Bukkit;

public class CombatListener implements Listener{
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        Entity defender = event.getEntity();

        Bukkit.broadcastMessage(attacker.getName() + " " + defender.getName());

    }
}
