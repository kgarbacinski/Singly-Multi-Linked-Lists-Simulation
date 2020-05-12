package pl.javasolution.implementation;

public class Pociag {
    private WagonyLista wagonyList;
    private String name;
    private Pociag next;

    public Pociag(WagonyLista newWagonyList, String name) {
        wagonyList = newWagonyList;
        this.name = name;
        this.next = null;
    }

    public void addPociag(Pociag toAddPociag) { // Metoda wykorzystywana do Union
        Wagon curr = toAddPociag.wagonyList.getFirst(); // Ustaw na pierwszym elmenecie
        while (curr != null) { // Szukaj do konca
            this.wagonyList.addLast(curr); // Dodaj do konca pociagu dany element
            curr = curr.getNext();
        }

    }

    /* Settery i gettery dla atrybutow */
    public void setName(String newName) {
        this.name = newName;
    }

    public void setNext(Pociag newNext) {
        this.next = newNext;
    }

    public Pociag getNext() {
        return this.next;
    }

    public String getName() {
        return this.name;
    }

    public WagonyLista getWagonyList() {
        return this.wagonyList;
    }
}
