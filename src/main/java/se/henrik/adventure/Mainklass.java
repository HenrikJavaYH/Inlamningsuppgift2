package se.henrik.adventure;

import se.henrik.adventure.model.Burglar;
import se.henrik.adventure.model.Resident;

public class Mainklass {
    public static void main(String[] args) {

        Resident resident = new Resident("Resident", 12, 3);
        Burglar burglar = new Burglar("Burglar", 12, 4);
        Game g1 = new Game(resident, burglar);
        g1.start();
    }
}

