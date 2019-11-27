package dev.niekv.ride.command;

import dev.niekv.ride.menu.Menu;
import dev.niekv.ride.menu.element.Button;
import dev.niekv.ride.menu.element.Light;
import dev.niekv.ride.menu.element.TimedButton;
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
        menu.addElement(1, new TimedButton(menu, "test1", new BukkitItem(Material.ORANGE_CONCRETE),
                aVoid -> player.sendMessage("Click"))
                .setPressedItem(new BukkitItem(Material.LIGHT_BLUE_CONCRETE), 3000));
        menu.addElement(2, new Switch(menu, "test2", new BukkitItem(Material.ARROW),
                switchState -> player.sendMessage(switchState.name()))
                .setPressedItem(new BukkitItem(Material.STICK))
                .setDisplayName(ChatColor.GREEN + "Door Switch")
                .addLore(ChatColor.GRAY + "Click to switch the door on or off"));
        menu.addElement(3, new Button(menu, "test3", new BukkitItem(Material.BONE_MEAL),
                buttonState -> player.sendMessage(buttonState.name()))
                .setPressedItem(new BukkitItem(Material.STONE))
                .setDisplayName(ChatColor.GREEN + "Power Switch")
                .addLore(ChatColor.GRAY + "Click to switch the main circuit breaker on or off"));
        menu.addElement(4, new Light(menu, "test4", new BukkitItem(Material.DIAMOND_AXE), menu.getElement("test3"))
                .setLightUpItem(new BukkitItem(Material.GOLDEN_AXE))
                .setDisplayName(ChatColor.GREEN + "Power indicator")
                .addLore(ChatColor.GRAY + "Showing if the power is on or off"));
        player.openInventory(menu.getInventory());

        return false;
    }
}
