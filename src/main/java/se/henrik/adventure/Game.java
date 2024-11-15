package se.henrik.adventure;
import se.henrik.adventure.model.Burglar;
import se.henrik.adventure.model.Entity;
import se.henrik.adventure.model.Resident;

import java.util.*;

public class Game {
    Resident resident;
    Burglar burglar;

    Game (Resident resident, Burglar burglar){
        this.resident = resident;
        this.burglar = burglar;
    }


    Scanner sc = new Scanner(System.in);

    private static final String VARDAGSRUM = "vardagsrummet";
    private static final String KÖK = "köket";
    private static final String SOVRUM = "sovrummet";
    private static final String HALL = "hallen";
    private static final String KONTOR = "kontoret";
    private static final String START = "start";
    private static final String SLÅSS = "slåss";
    private static final String STEKPANNA = "stekpanna";
    private static final String POLISEN = "ring";
    private static String currentlocation = START;
    private static boolean stekpannafunnen  = false;
    private static boolean isRunning  = true;
    private static boolean sover = true;

    public static void gåTillVardagsRummet() {

            if (sover) {
                System.out.println("Du ligger och sover i vardagsrummet, " +
                        "\nhär finns soffa och tv, du vaknar av ett ljud i hallen, bäst att du går o kollar vad det var för något");
            } else {
                System.out.println("Du är i vardagsrummet, här finns en soffa samt tv och tv-bord");
            }
            sover = false;

        currentlocation = VARDAGSRUM;
    }

    public void start () {
        printWelcomeMenu();
        gåTillVardagsRummet();
        while (isRunning && resident.isConscious()) {
            String input = getUserinput();
            processInput(input);
        }
    }

    public void printWelcomeMenu () {
        System.out.println("Vart vill du gå?");
        System.out.println("Skriv ett av följande ord för att gå till respektive plats:");
        System.out.println("vardagsrummet\nköket\nsovrummet\nhallen\nkontoret");
        System.out.println("Ditt namn är Resident och din motståndares namn är Burglar");
    }

    public String getUserinput () {
        String userinput = sc.nextLine();
        return userinput;
    }

    public boolean processInput (String input) {
        switch (input) {
            case VARDAGSRUM -> gåTillVardagsRummet();
            case KÖK -> gåTillKöket();
            case KONTOR -> gåTillKontor();
            case HALL -> gåTillHallen();
            case SOVRUM -> gåTillSovrummet();
            case SLÅSS -> slåss();
            case STEKPANNA -> taStekpanna();
            case POLISEN -> ringPolisen();
            default -> System.out.println("bad input");
        };

        return true;
    }

    public void taStekpanna(){
        if (currentlocation.equals(KÖK) && !stekpannafunnen) {
            resident.setDamage(3);
            stekpannafunnen = true;
            System.out.println("Stekpannan upplockad, nu kan du göra mer skada på tjuven!");
        } else {
            System.out.println("Stekpannan kan hämtas i köket");
        }
    }

    public void gåTillKöket () {
        if (currentlocation.equals(VARDAGSRUM)) {
            if (!stekpannafunnen) {
                System.out.println("Du är i köket, här finns det en stekpanna som går att använda som vapen! Skriv stekpanna för att plocka upp den!");
                // +  3;
            }
            else {
                System.out.println("Du är i köket");
            }
            currentlocation = KÖK;
        } else {
            System.out.println("Du kan bara gå till köket från vardagsrummet");
        }
    }

    public void gåTillSovrummet () {
        if (currentlocation.equals(VARDAGSRUM)) {
            System.out.println("Du är i sovrummet, här finns det en säng och nattduksbord");
            currentlocation = SOVRUM;
        } else {
            System.out.println("Du kan bara gå till sovrummet från vardagsrummet");
        }
    }

    public void slåss(){
        Entity.fightOneRound(resident, burglar);
        if(!resident.isConscious()){
            System.out.println("Du blev medvetslös och ditt guld är borta. Bättre lycka nästa gång.");
            isRunning = false;
    } else if(!burglar.isConscious()){
            System.out.println("Vad vill du göra nu? på kontoret kan du gå och ringa polisen!");
        }
    }

    public void gåTillHallen () {
        if (currentlocation.equals(VARDAGSRUM)) {
            System.out.println("Du är i hallen, en inbrottstjuv är här!");
            if (!stekpannafunnen) {
                System.out.println("Du måste slåss mot inrottstjuven, skriv slåss för att slåss, " +
                        "\ngå först till köket och hämta stekpannan för att göra mer skada på tjuven");
            }
            currentlocation = HALL;
        } else {
            System.out.println("Du kan bara gå till hallen från vardagsrummet");
        }
    }

    public void ringPolisen () {
        if (currentlocation.equals(HALL) && !burglar.isConscious()) {
            System.out.println("Polisen är underrättad och är på väg!");
            isRunning = false;
        }
    }

    public void gåTillKontor () {
        if (currentlocation.equals(VARDAGSRUM) && burglar.isConscious()) {
            System.out.println("Du är på kontoret, här ser tomt ut förutom dator och skrivbord");
            currentlocation = HALL;
        } else if (currentlocation.equals(VARDAGSRUM) && !burglar.isConscious()) {
            System.out.println("Här finns en telefon! skriv ring för att ringa polisen");
            currentlocation = HALL;
        } else {
            System.out.println("Du kan bara gå till kontoret från vardasgrummet");
        }
    }
}
