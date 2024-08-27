package kg.geeks.game.players;

import kg.geeks.game.logic.RPG_Game;

public class Boss extends GameEntity {
    private SuperAbility defence;

    public Boss(String name, int health, int damage) {
        super(name, health, damage);
    }

    public SuperAbility getDefence() {
        return defence;
    }

    public void attack(Hero[] heroes) {
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) {
                if (hero instanceof Berserk &&
                        this.defence != SuperAbility.BLOCK_DAMAGE_AND_REVERT) {
                    int blocked = (RPG_Game.random.nextInt(2) + 1) * 5; // 1, 2
                    hero.setHealth(hero.getHealth()
                            - (this.getDamage() - blocked));
                    ((Berserk) hero).setBlockedDamage(blocked);
                } else {
                    hero.setHealth(hero.getHealth() - this.getDamage());
                }
            }
        }
    }

    public void chooseDefence() {
        SuperAbility[] allEnumElements = SuperAbility.values();
        int randomIndex = RPG_Game.random.nextInt(allEnumElements.length); // 0, 1, 2, 3
        this.defence = allEnumElements[randomIndex];
    }

    @Override
    public String toString() {
        return "BOSS " + super.toString() + " defence: " + this.defence;
    }
}
