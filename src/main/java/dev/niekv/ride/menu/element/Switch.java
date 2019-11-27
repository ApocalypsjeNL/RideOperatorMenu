package dev.niekv.ride.menu.element;

import dev.niekv.ride.menu.Menu;
import dev.niekv.ride.menu.util.BukkitItem;

import java.util.function.Consumer;

public class Switch extends Element {

    private Consumer<SwitchState> onClickHandler;
    private BukkitItem originalItem;
    private BukkitItem pressedItem;

    private SwitchState switchState = SwitchState.LEFT;

    public Switch(Menu menu, String elementId, BukkitItem item) {
        super(menu, elementId, item);
        this.originalItem = item;
    }

    public Switch(Menu menu, String elementId, BukkitItem item, Consumer<SwitchState> onClickHandler) {
        super(menu, elementId, item);
        this.onClickHandler = onClickHandler;
        this.originalItem = item;
    }

    public void setSwitchState(SwitchState switchState) {
        this.switchState = switchState;
        this.menu.update(this);
    }

    public void setOnClickHandler(Consumer<SwitchState> onClickHandler) {
        this.onClickHandler = onClickHandler;
    }

    public BukkitItem getPressedItem() {
        return this.pressedItem;
    }

    public Switch setPressedItem(BukkitItem pressedItem) {
        this.pressedItem = pressedItem;
        return this;
    }

    @Override
    public void handleClick() {
        this.switchState = this.switchState.invert();

        this.onClickHandler.accept(this.switchState);

        if(this.pressedItem != null) {
            if(this.item.equals(this.originalItem)) {
                this.item = this.pressedItem;
            } else {
                this.item = this.originalItem;
            }

            this.menu.update(this);
        }
    }

    @Override
    public boolean isClear() {
        return this.switchState.equals(SwitchState.LEFT);
    }

    public enum SwitchState {
        LEFT,
        RIGHT;

        SwitchState invert() {
            return this.equals(SwitchState.LEFT) ? SwitchState.RIGHT : SwitchState.LEFT;
        }
    }
}
