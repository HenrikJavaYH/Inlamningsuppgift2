package se.henrik.adventure.model;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @org.junit.jupiter.api.Test
    void punch() {
        // Setup test
        Resident resident = new Resident("Resident", 10, 2);
        Burglar burglar = new Burglar("Burglar", 10, 3);

        // Do something
        resident.punch(burglar);
        
        // Assert something
        Assertions.assertEquals(8, burglar.health);
        Assertions.assertEquals(10, resident.health);
    }

    @org.junit.jupiter.api.Test
    void anotherTest(){
        Resident resident = new Resident("Resident", 10, 2);
        Burglar burglar = new Burglar("Burglar", 10, 3);

        burglar.takeHit(9);

        Assertions.assertEquals(1, burglar.health);
    }
}