package dev.niekv.ride.menu.listener;

import dev.niekv.ride.menu.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuClickListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if(event.getClickedInventory() != null) {
            if(event.getClickedInventory().getHolder() != null && event.getClickedInventory().getHolder() instanceof Menu) {
                event.setCancelled(true);
                Menu menu = (Menu) event.getClickedInventory().getHolder();
                menu.handleClick(event.getSlot());
            }
        }
    }
}
