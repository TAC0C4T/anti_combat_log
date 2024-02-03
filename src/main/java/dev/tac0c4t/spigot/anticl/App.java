package dev.tac0c4t.spigot.anticl;

import java.net.http.WebSocket.Listener;
import java.util.EventListener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Hello world!
 *
 */
public class App extends JavaPlugin
{
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new CombatListener(), this);   
    }
}
