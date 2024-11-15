package se.henrik.adventure.model;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @org.junit.jupiter.api.Test
    void punch() {
        // Setup test
        Resident resident = new Resident("Resident", 10, 2);
        Burglar burglar = new Burglar("Burglar", 10, 3);
        resident.punch(burglar);
        Assertions.assertEquals(8, burglar.health);
        Assertions.assertEquals(10, resident.health);
    }

   /* @org.junit.jupiter.api.Test
    void anotherTest(){
        Resident resident = new Resident("Resident", 10, 2);
        Burglar burglar = new Burglar("Burglar", 10, 3);

        burglar.takeHit(9);

        Assertions.assertEquals(1, burglar.health);
    }*/

    @org.junit.jupiter.api.Test
    void takehitTest() {
        Resident resident = new Resident("Resident", 10, 3);
        Burglar burglar = new Burglar("Burglar", 10, 3);
        burglar.takeHit(3);
        Assertions.assertEquals(7, burglar.getHealth());
    }


    @org.junit.jupiter.api.Test
    void isConsciousTest() {
        Resident resident = new Resident("Resident", 10, 3);
        resident.takeHit(3);
        Assertions.assertTrue(resident.isConscious());
        resident.takeHit(10);
        Assertions.assertFalse(resident.isConscious());
    }

    @org.junit.jupiter.api.Test
    void isConsciousTest2() {
        Burglar burglar = new Burglar("Burglar", 10, 3);
        burglar.takeHit(3);
        Assertions.assertTrue(burglar.isConscious());
        burglar.takeHit(11);
        Assertions.assertFalse(burglar.isConscious());
    }
}