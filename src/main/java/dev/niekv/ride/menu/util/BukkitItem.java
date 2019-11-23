package dev.niekv.ride.menu.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BukkitItem {

    private Material material;
    private int damage;

    public BukkitItem(Material material) {
        this.material = material;
    }

    public BukkitItem(Material material, int damage) {
        this.material = material;
        this.damage = damage;
    }

    public ItemStack getItemStack(String displayName, List<String> lore) {
        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null) {
            itemMeta.setDisplayName(displayName);
            itemMeta.setLore(lore);

            Damageable damageable = (Damageable) itemMeta;
            damageable.setDamage(this.damage);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
