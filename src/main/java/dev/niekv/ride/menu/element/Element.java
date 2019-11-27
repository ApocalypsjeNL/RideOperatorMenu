package dev.niekv.ride.menu.element;

import dev.niekv.ride.menu.Menu;
import dev.niekv.ride.menu.util.BukkitItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Element {

    private static final AtomicInteger timerTaskId = new AtomicInteger();
    final Timer timer = new Timer("RideOperatorMenu Element Timer #" + Element.timerTaskId.getAndIncrement());

    Menu menu;
    BukkitItem item;

    private String elementId;
    private String displayName;
    private final List<String> lore = new ArrayList<>();

    Element(Menu menu, String elementId, BukkitItem item) {
        this.menu = menu;
        this.elementId = elementId;
        this.item = item;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public String getElementId() {
        return this.elementId;
    }

    public BukkitItem getItem() {
        return this.item;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Element setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public Element addLore(String lore) {
        this.lore.add(lore);
        return this;
    }

    public ItemStack getItemStack() {
        if(this.item == null) {
            return new ItemStack(Material.AIR);
        }

        return this.item.getItemStack(this.displayName, this.lore);
    }

    public abstract void handleClick();

    public abstract boolean isClear();
}
