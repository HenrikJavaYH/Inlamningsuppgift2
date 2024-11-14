package se.henrik.adventure.model;

public abstract class Entity {
    String role;
    int health;
    int damage;

    Entity(String role, int health, int damage) {
        this.role = role;
        this.health = health;
        this.damage = damage;
    }

    public String getRole() {
        return role;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int newDamage) {
        damage = damage + newDamage;
    }

    public void punch (Entity toPunch) {
        toPunch.takeHit(damage);
    }

    public void takeHit (int damage) {
        health -= damage;
    }

    public boolean isConscious () {
        return health > 0;
    }

    // Denna kan vara statisk då den inte använder referenser till något i detta objekt
    // Då kan jag kalla på den genom ex. Entity.fightOneRound(arg1, arg2);
    public static void fightOneRound (Entity attacker, Entity defender) {
        excecuteAttack(attacker, defender);
        if (defender.isConscious()) {
            excecuteAttack(defender, attacker);
        }
    }

    // Denna kan vara statisk då den inte använder referenser till något i detta objekt
    // Då kan jag kalla på den genom ex. Entity.executeAttack(arg1, arg2);
    private static void excecuteAttack (Entity attacker, Entity defender) {
        defender.takeHit (attacker.damage);
        System.out.println(attacker.getRole() + " Attackerar " + defender.getRole());
        if (defender.isConscious()) {
            System.out.println(defender.getRole() + " har kvar hälsan: " + defender.getHealth());
        }
        else {
            System.out.println(defender.getRole() + " Är medvetslös");
        }
    }
}
