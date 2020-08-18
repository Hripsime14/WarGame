public class Elf extends Mystic {
    private static final String TYPE = "Elf";

    private static final int HEALTH = 800;
    private static final int STRENGTH = 10;
    private static final int SPEED = 5;

    Elf() {
        super(HEALTH, STRENGTH, SPEED, TYPE);
    }

    int getInitialHealth() {
        return HEALTH;
    }
}