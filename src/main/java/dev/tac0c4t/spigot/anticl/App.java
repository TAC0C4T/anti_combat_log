package dev.tac0c4t.spigot.anticl;

import java.net.http.WebSocket.Listener;
import java.util.EventListener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Entity;

import java.util.Map;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App extends JavaPlugin
{
    private final Map<Entity, Entity> battles = new HashMap<>();
   
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new CombatListener(this), this);   
    }

    public void startCombat(Entity attacker, Entity defender) {
        if (battles.containsKey(attacker) || battles.containsKey(defender)) {
            return;
        }
        Bukkit.broadcastMessage("combat begin");
        battles.put(attacker, defender);
        battles.put(defender, attacker);
        Bukkit.getScheduler().runTaskLater(this, () -> {
            checkCombatParticipants();
        }, 5 * 20);
    }

    public void checkCombatParticipants() {
        for (Entity entity : battles.keySet()) {
            if (!entity.isValid()) {
                Bukkit.broadcastMessage(entity.getName() + "did the cringe");
            }
        }
    }
}
