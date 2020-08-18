public class Human extends Soldier {

    public Human(int health, int strength, int speed, String type) {
        super(health, strength, speed, type);
    }

    public void setWeapon(HumanWeapon weapon) {
        this.weapon = weapon;
    }

}
