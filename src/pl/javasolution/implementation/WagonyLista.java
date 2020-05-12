package pl.javasolution.implementation;

/* Lista dwukierunkowa */
public class WagonyLista {
    private Wagon first;
    private Wagon last;
    public boolean isEmpty;

    public WagonyLista(){
        first = null;
        last = null;
        isEmpty = true;
    }

    public Wagon getFirst(){
        return this.first;
    }
    public Wagon getLast() {
        return this.last;
    } // Getter

    public void addLast(Wagon newWagon){
        if(this.isEmpty){ // Jesli dodajemy pierwszy Wagon do listy Wagonow
            this.isEmpty = false;
            first = newWagon;
            last = first;
            last.setNext(null);
            last.setPrev(null);
        }
        else {
           last.setNext(newWagon);
           newWagon.setPrev(last);
           last = newWagon;
        }
    }

    public void addFirst(Wagon newWagon){
        if(this.isEmpty){
            this.isEmpty = false;
            this.first = newWagon;
            this.last = this.first;
            this.last.setNext(null);
            this.last.setPrev(null);
        }
        else{
            this.first.setPrev(newWagon);
            newWagon.setNext(this.first);
            this.first = newWagon;
        }

    }

    public int delFirst(){
        Wagon p = this.first;
        try {
            this.first = p.getNext();
            this.first.setNext(p.getNext().getNext());
            this.first.setPrev(null);
        }
        catch (NullPointerException e) { return 0; }

        return 1;
    }

    public int delLast(){
        Wagon p = this.last;
        try{
            this.last = p.getPrev();
            this.last.setPrev(p.getPrev().getPrev());
            this.last.setNext(null);
        }
        catch (NullPointerException e) { return 0; }
        return 1;
    }

    public void reverse(){
        Wagon firstB = first;
        Wagon curr = first;
        while(curr != null){ // Przestaw oprocz pierwszego i ostatniego elementu
            Wagon buff = curr;
            curr = curr.getNext();
            Wagon prev = buff.getPrev(); //null
            buff.setPrev(buff.getNext()); //null
            buff.setNext(prev); // null
        }
        /* Wykonaj dla first i last */
        this.first = this.last;
        this.last = firstB;

        System.out.print("");
    }

    public void displayList(){
        Wagon wagon = first;
        while(wagon != null){
            System.out.print(wagon.getName() + " ");
            wagon = wagon.getNext();
        }
    }

}
