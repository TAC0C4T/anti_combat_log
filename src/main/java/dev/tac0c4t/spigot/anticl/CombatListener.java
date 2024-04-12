package dev.tac0c4t.spigot.anticl;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Entity;
import org.bukkit.Bukkit;

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

}
