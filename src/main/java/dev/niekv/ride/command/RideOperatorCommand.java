package dev.niekv.ride.command;

import dev.niekv.ride.menu.Menu;
import dev.niekv.ride.menu.element.Button;
import dev.niekv.ride.menu.element.Switch;
import dev.niekv.ride.menu.util.BukkitItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class RideOperatorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {

        Player player = (Player) sender;

        Menu menu = new Menu(player, 36, ChatColor.GREEN + "Menu");
        menu.addElement(1, new Button(menu, "test1", new BukkitItem(Material.ORANGE_CONCRETE),
                (aVoid) -> player.sendMessage("Click"))
                .setPressedItem(new BukkitItem(Material.LIGHT_BLUE_CONCRETE), 3000));
        menu.addElement(2, new Switch(menu, "test2", new BukkitItem(Material.ARROW),
                (switchState) -> player.sendMessage(switchState.name()))
                .setPressedItem(new BukkitItem(Material.STICK))
                .setDisplayName(ChatColor.GREEN + "Door Switch")
                .addLore(ChatColor.GRAY + "Click to switch the door on or off"));
        player.openInventory(menu.getInventory());

        return false;
    }
}
