package dev.niekv.ride;

import dev.niekv.ride.command.RideOperatorCommand;
import dev.niekv.ride.menu.listener.MenuClickListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class RideOperatorMenuPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new MenuClickListener(), this);

        PluginCommand rideControlCommand;
        if((rideControlCommand = this.getCommand("rideoperator")) != null) {
            rideControlCommand.setExecutor(new RideOperatorCommand());
        }
    }
}
