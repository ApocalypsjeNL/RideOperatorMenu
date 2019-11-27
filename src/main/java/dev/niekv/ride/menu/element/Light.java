package dev.niekv.ride.menu.element;

import dev.niekv.ride.menu.Menu;
import dev.niekv.ride.menu.util.BukkitItem;

public class Light extends Element {

    private Element lightingElement;
    private BukkitItem lightOffItem;
    private BukkitItem lightUpItem;

    public Light(Menu menu, String elementId, BukkitItem item, Element lightingElement) {
        super(menu, elementId, item);
        this.lightingElement = lightingElement;
        this.lightOffItem = item;
    }

    public Element getLightingElement() {
        return this.lightingElement;
    }

    public BukkitItem getLightUpItem() {
        return this.lightUpItem;
    }

    public Light setLightUpItem(BukkitItem lightUpItem) {
        this.lightUpItem = lightUpItem;
        return this;
    }

    public void updateLight() {
        this.item = this.lightingElement.isClear() ? this.lightOffItem : this.lightUpItem;
        this.menu.update(this);
    }

    @Override
    public void handleClick() {
    }

    @Override
    public boolean isClear() {
        return true;
    }
}
