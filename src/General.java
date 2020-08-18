import java.util.ArrayList;
import java.util.List;

public class General extends Human {
    private static final String TYPE = "General";

    private static final int HEALTH = 1000;
    private static final int STRENGTH = 10;
    private static final int SPEED = 2;

    private final List<Weapon> weapons = new ArrayList<>();

    General() {
        super(HEALTH, STRENGTH, SPEED, TYPE);
        weapons.add(weapon);
    }

    void addWeapon(MysticWeapon weapon) {
        weapons.add(weapon);
    }

    @Override
    public int getOverallDamage() {
        int damage = 0;
        for (Weapon w : weapons) {
            if (w != null)
            damage += w.DAMAGE;
        }
        return strength + damage;
    }

    int getInitialHealth() {
        return HEALTH;
    }
}
