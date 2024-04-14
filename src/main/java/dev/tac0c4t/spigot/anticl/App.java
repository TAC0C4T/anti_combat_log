package dev.tac0c4t.spigot.anticl;

import java.net.http.WebSocket.Listener;
import java.util.EventListener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App extends JavaPlugin
{
    private final Map<Entity, Entity> battles = new HashMap<>();
    private final Map<Player, Boolean> sentenced = new HashMap<>();
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new CombatListener(this), this);   
    }

    public void startCombat(Entity attacker, Entity defender) {
        /*if(!(attacker instanceof Player) || !(defender instanceof Player)) {
            return;
        }*/
        if (battles.containsKey(attacker) || battles.containsKey(defender)) {
            return;
        }

        Bukkit.broadcastMessage("combat begin");
        battles.put(attacker, defender);
        battles.put(defender, attacker);

        Bukkit.getScheduler().runTaskLater(this, () -> {
            endCombat(attacker);
            endCombat(defender);
        }, 5 * 20);
    }

    public void endCombat(Entity entity) {
        Entity opponent = battles.remove(entity);
        if (opponent != null) {
            if (opponent instanceof Player && ((Player) opponent).isOnline()) {
                ((Player) opponent).sendMessage("combat over");
            }
        }
    }

    public void checkCombatParticipants() {
        for (Entity entity : battles.keySet()) {
            if (!entity.isValid()) {
                Bukkit.broadcastMessage(entity.getName() + "did the cringe");
            }
        }
    }

    public boolean isInCombat(Entity entity) {
        return battles.containsKey(entity);
    }

    public void sentence(Player player) {
        sentenced.put(player, true);
    }

    public boolean isSentenced(Player player) {
        return sentenced.getOrDefault(player, false);
    }

    public void pardon(Player player) {
        sentenced.remove(player);
    }
}
