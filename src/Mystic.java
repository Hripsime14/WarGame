public class Mystic extends Soldier {

    Mystic(int health, int strength, int speed, String type) {
        super(health, strength, speed, type);
    }

    public void setWeapon(MysticWeapon weapon) {
        this.weapon = weapon;
    }
}
