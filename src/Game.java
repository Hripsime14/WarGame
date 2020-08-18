import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//init, update, finish

public class Game {
    List<Human> humanList = new ArrayList<>();
    List<Mystic> mysticList = new ArrayList<>();
    int distance;
    int soldierCount;
    Human activeHuman;
    Mystic activeMystic;
    int millis = 500;
    Timer timer = null;

    Game(int a, int b) {
        soldierCount = a;
        distance = b;
        createSoldiers(a);
    }

    void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, 500);
    }


    void doWork(Human human, Mystic mystic) {
        double hx, hy, mx, my;
        hx = activeHuman.coordinate.x;
        hy = activeHuman.coordinate.y;
        mx = activeMystic.coordinate.x;
        my = activeMystic.coordinate.y;
        if (activeMystic.coordinate.x - activeHuman.coordinate.x > 1) {
            activeHuman.coordinate = new Coordinates(hx + (double) activeHuman.speed*millis / 1000, hy);
            activeMystic.coordinate = new Coordinates(mx - (double) activeMystic.speed*millis / 1000, my);
            System.out.println(activeHuman.type + " coordinates are -> x = " + activeHuman.coordinate.x + ", y = " + activeHuman.coordinate.y);
            System.out.println(activeMystic.type + " coordinates are -> x = " + activeMystic.coordinate.x + ", y = " + activeMystic.coordinate.y);
            System.out.println();
        } else {
            if (human.health < 0 || mystic.health < 0) return;
           attack(human, mystic);
        }
    }

    int count20percent(Soldier soldier) {
        if (soldier instanceof Infantry) return ((Infantry) soldier).getInitialHealth() / 5;
        if (soldier instanceof Commando) return ((Commando) soldier).getInitialHealth() / 5;
        if (soldier instanceof General) return ((General) soldier).getInitialHealth() / 5;
        if (soldier instanceof Elf) return ((Elf) soldier).getInitialHealth() / 5;
        if (soldier instanceof Orc) return ((Orc) soldier).getInitialHealth() / 5;
        if (soldier instanceof Troll) return ((Troll) soldier).getInitialHealth() / 5;
        else return 0;
    }

    boolean isWeak(Soldier soldier) { // es method-@ el piti chkanchver che? erb parzvec vor soldier@ week a
        System.out.println(soldier.type + " is week");
        return soldier.health < count20percent(soldier);
    }

    void attack(Human human, Mystic mystic) {
        double humanMultipliedDamage = 1; //es firleder@ jisht a estex haytararely te global-i depqum aveli shat resurs kxnayenq?
        double mysticMultipliedDamage = 1;
        if (human.coordinate.x < (double) distance / 2) {
            humanMultipliedDamage = 1.2;
        } else mysticMultipliedDamage = 1.2;

        if (human instanceof General) {
            if (isWeak(mystic)) {
                if (mystic.weapon != null)
                ((General) human).addWeapon((MysticWeapon) mystic.weapon);
            }
        }

        if (mystic instanceof Troll) {
            if (isWeak(human)) {
                if (human.weapon != null) {
                    ((Troll) mystic).addWeapon((HumanWeapon) human.weapon);
                }
            }
        }

        human.health -= mystic.getOverallDamage() * humanMultipliedDamage;
                System.out.println(human.type + ".health after attack is = " + human.health);
        mystic.health -= human.getOverallDamage() * mysticMultipliedDamage;
                System.out.println(mystic.type + ".health after attack is = " + mystic.health);

        if (human.health < 0) {
            System.out.println("human is dead");
        }
        if (mystic.health < 0) {
            System.out.println("mystic is dead");
        }
    }


    void createSoldiers(int soldiersCount) {
        int y = 0;
        int i = 0;
        while (soldiersCount - i++> 0) {
            Human humanSoldier = (Human) selectRandomSoldier(SoldierType.HUMAN_TYPE);
            humanSoldier.setCoordinate(0, y);
            humanSoldier.setWeapon((HumanWeapon) selectRandomWeapon(SoldierType.HUMAN_TYPE));
            humanList.add(humanSoldier);

            Mystic mysticSoldier = (Mystic) selectRandomSoldier(SoldierType.MYSTIC_TYPE);
            mysticSoldier.setCoordinate(distance, y--);
            mysticSoldier.setWeapon((MysticWeapon)selectRandomWeapon(SoldierType.MYSTIC_TYPE));
            mysticList.add(mysticSoldier);
        }
    }

    Weapon selectRandomWeapon(int type) {
        Weapon weapon;

        int randomNumber = new Random().nextInt(4);
        if (type == SoldierType.HUMAN_TYPE) {
            switch (randomNumber) {
                case 0 -> weapon = new Gun();
                case 1 -> weapon = new Rifle();
                case 2 -> weapon = new Shotgun();
                default -> weapon = new Gun();
            }
        } else {
            switch (randomNumber) {
                case 0 -> weapon = new Knife();
                case 1 -> weapon = new Sword();
                case 2 -> weapon = new Axe();
                default -> weapon = new Knife();
            }
        }
        return weapon;
    }

    Soldier selectRandomSoldier(int type) {
        Soldier soldier;
        int randomNumber = new Random().nextInt(3);
        if (type == SoldierType.HUMAN_TYPE) {
            switch (randomNumber) {
                case 0 -> soldier =  new Infantry(); //jisht a che toxnel, vor readible lini?
                case 1 -> soldier =  new Commando();
                case 2 -> soldier =  new General();
                default -> soldier =  new Infantry();
            }
        } else {
            switch (randomNumber) {
                case 0 -> soldier = new Troll();
                case 1 -> soldier =  new Orc();
                case 2 -> soldier =  new Elf();
                default -> soldier =  new Troll();
            }
        }
        return soldier;
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            while (!isTeamLost(humanList) && !isTeamLost(mysticList)) {
                for (int i = 0; i < soldierCount ; i++) {
                    activeHuman = humanList.get(i);
                    activeMystic = mysticList.get(i);
                    doWork(activeHuman, activeMystic);
                }
                System.out.println("________________________________________");
            }
            timer.cancel();
        }

        <T extends Soldier> boolean isTeamLost(List<T> soldiers) {
            int deadSoldiers = 0;
            for (T soldier : soldiers) {
                if (soldier.health <= 0) deadSoldiers++;
            }
            boolean returnRes;
            if (soldiers.size() %2 > 0) { //estex if else-@ lav e grac? te variable pahem u 0/1 veragrem u miangamic return anem ardyunqy
                returnRes = deadSoldiers >= soldierCount / 2 + 1;
            }
            else {
                returnRes = deadSoldiers >= soldierCount / 2;
            }
            System.out.println("half or more of the soldiers are dead");
            return returnRes;

        }
    }
}