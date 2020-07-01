package pl.javasolution.implementation;

/* Implementacja listy pojedynczej bez glowy */
public class PociagiLista {
    Pociag first;

    public PociagiLista() {
        first = null;
    }

    public Pociag findPociag(String name) {
        Pociag p = first;
        try {
            if (p.getName().equals(name)) return p; //Przypadek gdy pierwszy element spelnia warunek
            else {
                while (p.getNext() != null && !p.getNext().getName().equals(name)) { //Wyszukuj do konca listy lub napotkania danej nazwy
                    p = p.getNext();
                }
                if (p.getNext() == null) return null; // Jezeli nie znalazlo nazwy
                else return p.getNext(); // Zwroc obiekt Pociag spelniajacy nazwe
            }
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void addLast(Pociag newPociag) {
        if (first == null) first = newPociag; // Jezeli lista pusta
        else {
            if (this.findPociag(newPociag.getName()) != null)
                System.out.println("Train " + newPociag.getName() + " already exists");
            else {
                newPociag.setNext(first);
                first = newPociag;
            }
        }
    }

    public void addAfter(Pociag newPociag, String beforeName) {
        Pociag beforePociag = this.findPociag(beforeName); // Znajdz pociag poprzedajacy
        newPociag.setNext(beforePociag.getNext()); // Wstaw dany Pociag
        beforePociag.setNext(newPociag);
    }

    public void union(String firstName, String toAddName) {
        Pociag definedPociag = this.findPociag(firstName); // Znajdz pociag nr 1
        Pociag toAddPociag = this.findPociag(toAddName); // Znajdz pociag nr 2

        try {
            definedPociag.addPociag(toAddPociag);
            this.deletePociag(toAddName);
        } catch (NullPointerException e) {
            System.out.println("Train " + firstName + " does not exist"); // Wyjatek gdy nie znajdzie pociagu nr 1
        }

    }

    public void delFirstWagon(String definedName, String newName) {
        Pociag definedPociag = this.findPociag(definedName); // Znajdz pociag z ktorego usunac pierwszy wagon
        if(definedPociag == null){
            System.out.println("Train " + definedName + " does not exist");
            return;
        }

        WagonyLista newWagonyList = new WagonyLista();
        Wagon newWagon = new Wagon(definedPociag.getWagonyList().getFirst().getName());

        if (this.findPociag(newName) != null) {
            System.out.println("Train " + newName + " already exists"); // Wyjatek gdy nie znajdzie pociagu nr 2
            int result = definedPociag.getWagonyList().delFirst();
            if (result == 0) deletePociag(definedName); // 0 jesli usunieto pociag
        }
        else {
            newWagonyList.addLast(newWagon);
            Pociag newPociag = new Pociag(newWagonyList, newName);
            this.addAfter(newPociag, definedName);

            int result = definedPociag.getWagonyList().delFirst();
            if (result == 0) deletePociag(definedName); // 0 jesli usunieto pociag
        }
    }

    /* Patrz wyzej */
    public void delLastWagon(String definedName, String newName) {
        Pociag definedPociag = this.findPociag(definedName);

        if(definedPociag == null){
            System.out.println("Train " + definedName + " does not exist");
            return;
        }

        WagonyLista newWagonyList = new WagonyLista();
        Wagon newWagon = new Wagon(definedPociag.getWagonyList().getLast().getName());
        if (this.findPociag(newName) != null) {
            System.out.println("Train " + newName + " already exists");
            int result = definedPociag.getWagonyList().delLast(); // Sprawdz czy usunieto ostatni element
            if (result == 0) deletePociag(definedName); // Jezeli usunieto ostatni element
        }
        else {
            newWagonyList.addLast(newWagon);
            Pociag newPociag = new Pociag(newWagonyList, newName);
            this.addAfter(newPociag, definedName);

            int result = definedPociag.getWagonyList().delLast(); // Sprawdz czy usunieto ostatni element
            if (result == 0) deletePociag(definedName); // Jezeli usunieto ostatni element
        }

    }

    public void deletePociag(String toDelName) {
        Pociag curr = this.first;
        Pociag prev = null;

        while (curr != null && !curr.getName().equals(toDelName)) {
            prev = curr;
            curr = curr.getNext();
        }

        if (curr != null) {
            if (prev == null) this.first = curr.getNext(); // Jesli szukany element jest pierwszy na liscie
            else prev.setNext(curr.getNext()); // Dla pozostalych przypadkow
        }
    }

    public void displayPociag(String name) { // Wyswietl pociag o danej nazwie
        Pociag definedPociag = this.findPociag(name);
        try {
            System.out.print(definedPociag.getName() + ": ");
            definedPociag.getWagonyList().displayList();
            System.out.println();
        } catch (NullPointerException e) {
            System.out.println("Train " + name + " does not exist");
        }
    }

    public void displayList() { // Wyswietl cala liste pociagow
        Pociag last = first;
        System.out.print("Trains: ");
        while (last != null) {
            System.out.print(last.getName() + ' ');
            last = last.getNext();
        }
    }


}
