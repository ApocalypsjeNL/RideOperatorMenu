package dev.niekv.ride.menu.element;

import dev.niekv.ride.menu.Menu;
import dev.niekv.ride.menu.util.BukkitItem;

import java.util.TimerTask;
import java.util.function.Consumer;

public class TimedButton extends Element {

    private Consumer<Void> onClickHandler;
    private BukkitItem pressedItem;
    private int pressedItemDelay;

    private boolean beingPressed = false;

    public TimedButton(Menu menu, String elementId, BukkitItem item) {
        super(menu, elementId, item);
    }

    public TimedButton(Menu menu, String elementId, BukkitItem item, Consumer<Void> onClickHandler) {
        super(menu, elementId, item);
        this.onClickHandler = onClickHandler;
    }

    public void setOnClickHandler(Consumer<Void> onClickHandler) {
        this.onClickHandler = onClickHandler;
    }

    public BukkitItem getPressedItem() {
        return this.pressedItem;
    }

    public TimedButton setPressedItem(BukkitItem pressedItem, int delay) {
        this.pressedItem = pressedItem;
        this.pressedItemDelay = delay;
        return this;
    }

    @Override
    public void handleClick() {
        if (this.pressedItem != null && !this.beingPressed) {
            this.beingPressed = true;
            final BukkitItem currentItem = this.item;

            this.item = this.pressedItem;
            this.menu.update(this);

            this.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    TimedButton.this.item = currentItem;
                    TimedButton.this.beingPressed = false;
                    TimedButton.this.menu.update(TimedButton.this);
                }
            }, this.pressedItemDelay);
        }

        if (this.pressedItem == null || !this.beingPressed) {
            this.onClickHandler.accept(null);
        }
    }

    @Override
    public boolean isClear() {
        return true;
    }
}
