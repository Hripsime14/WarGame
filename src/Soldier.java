public class Soldier {
    String Race; // human or mystic
    String type; // General, Elf, Orc etc...
    String name;
    int health;
    int strength;
    int  speed;
    Coordinates coordinate;
    Weapon weapon;

    Soldier(int health, int strength, int speed, String type) {
        this.health = health;
        this.strength = strength;
        this.speed = speed;
        this.type = type;
    }

    public int getOverallDamage() {
        if (weapon != null)
        return strength + weapon.DAMAGE;
        else return strength;
    }

    public void setCoordinate(double x, double y) {
        coordinate = new Coordinates(x, y);
    }

    public void setName(String name) {
        this.name = name;
    }
}
