package dev.niekv.ride.menu;

import com.google.common.base.Preconditions;
import dev.niekv.ride.menu.element.Element;
import dev.niekv.ride.menu.element.Light;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Menu implements InventoryHolder {

    private final Map<Integer, Element> elements = new HashMap<>();

    private final Player player;
    private final Inventory inventory;

    private final int menuSize;

    public Menu(Player player, int menuSize, String menuTitle) {
        this.player = player;
        this.menuSize = menuSize;
        this.inventory = Bukkit.getServer().createInventory(this, menuSize, menuTitle);
    }

    @Override
    @Nonnull
    public Inventory getInventory() {
        this.elements.forEach((slotId, element) -> this.inventory.setItem(slotId, element.getItemStack()));
        return this.inventory;
    }

    public Menu addElement(int slotId, Element element) {
        Preconditions.checkElementIndex(slotId, this.menuSize, "Slot id is larger than the menu size!");
        this.elements.put(slotId, element);
        return this;
    }

    public Element getElement(String elementId) {
        for (Map.Entry<Integer, Element> entrySet : this.elements.entrySet()) {
            if(entrySet.getValue().getElementId().equals(elementId)) {
                return entrySet.getValue();
            }
        }

        return null;
    }

    public void update(Element element) {
        int slotId;
        if ((slotId = this.containsElement(element)) != -1) {
            if(!(element instanceof Light)) {
                Optional<Light> correspondingLightOptional = this.findCorrespondingLight(element);
                correspondingLightOptional.ifPresent(this::update);
            }

            this.elements.put(slotId, element);
            this.inventory.setItem(slotId, element.getItemStack());

            this.player.updateInventory();
        }
    }

    private Optional<Light> findCorrespondingLight(Element element) {
        for (Map.Entry<Integer, Element> entrytSet : this.elements.entrySet()) {
            if(entrytSet.getValue() instanceof Light) {
                Light light = (Light) entrytSet.getValue();
                if(light.getLightingElement().getElementId().equals(element.getElementId())) {
                    return Optional.of(light);
                }
            }
        }

        return Optional.empty();
    }

    private int containsElement(Element element) {
        for (Map.Entry<Integer, Element> entrySet : this.elements.entrySet()) {
            if (entrySet.getValue().getElementId().equals(element.getElementId())) {
                return entrySet.getKey();
            }
        }

        return -1;
    }

    public void handleClick(int slotId) {
        if (this.elements.containsKey(slotId)) {
            this.elements.get(slotId).handleClick();
        }
    }
}
