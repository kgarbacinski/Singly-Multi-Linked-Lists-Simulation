package pl.javasolution.implementation;

public class Wagon {
    private String name;

    private Wagon next;
    private Wagon prev;

    public Wagon(String name) {
        this.name = name;
        this.next = null;
        this.prev = null;
    }

    /* Gettery i settery do atrybutow */
    public String getName() {
        return name;
    }

    public Wagon getNext(){
        return next;
    }

    public void setNext(Wagon next) {
        this.next = next;
    }

    public Wagon getPrev() {
        return prev;
    }

    public void setPrev(Wagon prev) {
        this.prev = prev;
    }
}
