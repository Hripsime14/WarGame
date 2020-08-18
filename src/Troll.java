import java.util.ArrayList;
import java.util.List;

public class Troll extends Mystic {
    private static final String TYPE = "Troll";

    private static final int HEALTH = 2000;
    private static final int STRENGTH = 30;
    private static final int SPEED = 2;

    private final List<Weapon> weapons = new ArrayList<>();

    Troll() {
        super(HEALTH, STRENGTH, SPEED, TYPE);
        weapons.add(weapon);
    }

    void addWeapon(HumanWeapon weapon) {
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
