package me.lukeforit.spaceofaday.ui.base;

public class Event<T> {
    private T data;
    private boolean delivered;

    public Event(T data) {
        this.data = data;
        delivered = false;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public T deliverData() {
        if (delivered) {
            return null;
        } else {
            delivered = true;
            return data;
        }
    }

    public T peekData() {
        return data;
    }
}
