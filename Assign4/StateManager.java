package Assign4;

public class StateManager<T> {
    private class Node {
        T state;
        Node prev;
        Node next;

        Node(T state) {
            this.state = state;
        }
    }

    private Node current;

    public void addState(T state) {
        Node newNode = new Node(state);

        if (current != null) {
            current.next = null;
            newNode.prev = current;
            current.next = newNode;
        }

        current = newNode;
    }

    public boolean undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            return true;
        }
        return false;
    }

    public boolean redo() {
        if (current != null && current.next != null) {
            current = current.next;
            return true;
        }
        return false;
    }

    public T getCurrentState() {
        return current != null ? current.state : null;
    }

    public boolean canUndo() {
        return current != null && current.prev != null;
    }

    public boolean canRedo() {
        return current != null && current.next != null;
    }
}
