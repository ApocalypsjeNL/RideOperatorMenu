package dev.niekv.ride.menu.element;

import dev.niekv.ride.menu.Menu;
import dev.niekv.ride.menu.util.BukkitItem;

import java.util.function.Consumer;

public class Button extends Element {

    private Consumer<ButtonState> onClickHandler;
    private BukkitItem originalItem;
    private BukkitItem pressedItem;

    private ButtonState state;

    public Button(Menu menu, String elementId, BukkitItem item) {
        super(menu, elementId, item);
        this.originalItem = item;
    }

    public Button(Menu menu, String elementId, BukkitItem item, Consumer<ButtonState> onClickHandler) {
        super(menu, elementId, item);
        this.originalItem = item;
        this.onClickHandler = onClickHandler;
    }

    public void setOnClickHandler(Consumer<ButtonState> onClickHandler) {
        this.onClickHandler = onClickHandler;
    }

    public BukkitItem getPressedItem() {
        return this.pressedItem;
    }

    public Button setPressedItem(BukkitItem pressedItem) {
        this.pressedItem = pressedItem;
        return this;
    }

    @Override
    public void handleClick() {
        if (this.pressedItem != null && this.state.equals(ButtonState.RELEASED)) {
            this.state = ButtonState.PRESSED;
            this.onClickHandler.accept(this.state);

            this.item = this.pressedItem;
            this.menu.update(this);
        }

        if(this.state.equals(ButtonState.PRESSED)) {
            this.state = ButtonState.RELEASED;
            this.onClickHandler.accept(this.state);

            this.item = this.originalItem;
            this.menu.update(this);
        }
    }

    @Override
    public boolean isClear() {
        return this.state.equals(ButtonState.RELEASED);
    }

    public enum ButtonState {
        RELEASED,
        PRESSED
    }
}