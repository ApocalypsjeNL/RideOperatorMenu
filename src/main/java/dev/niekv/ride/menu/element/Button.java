package dev.niekv.ride.menu.element;

import dev.niekv.ride.menu.Menu;
import dev.niekv.ride.menu.util.BukkitItem;

import java.util.TimerTask;
import java.util.function.Consumer;

public class Button extends Element {

    private Consumer<Void> onClickHandler;
    private BukkitItem pressedItem;
    private int pressedItemDelay;

    private boolean beingPressed = false;

    public Button(Menu menu, String elementId, BukkitItem item) {
        super(menu, elementId, item);
    }

    public Button(Menu menu, String elementId, BukkitItem item, Consumer<Void> onClickHandler) {
        super(menu, elementId, item);
        this.onClickHandler = onClickHandler;
    }

    public void setOnClickHandler(Consumer<Void> onClickHandler) {
        this.onClickHandler = onClickHandler;
    }

    public BukkitItem getPressedItem() {
        return this.pressedItem;
    }

    public Button setPressedItem(BukkitItem pressedItem, int delay) {
        this.pressedItem = pressedItem;
        this.pressedItemDelay = delay;
        return this;
    }

    public void handleClick() {
        if (this.pressedItem != null && !this.beingPressed) {
            this.beingPressed = true;
            final BukkitItem currentItem = this.item;

            this.item = this.pressedItem;
            this.menu.update(this);

            this.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Button.this.item = currentItem;
                    Button.this.beingPressed = false;
                    Button.this.menu.update(Button.this);
                }
            }, this.pressedItemDelay);
        }

        if (this.pressedItem == null || !this.beingPressed) {
            this.onClickHandler.accept(null);
        }
    }
}
