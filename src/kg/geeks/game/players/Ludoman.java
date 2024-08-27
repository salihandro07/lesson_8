package kg.geeks.game.players;

import kg.geeks.game.logic.RPG_Game;

public class Ludoman extends Hero {
    public Ludoman(String name, int health, int damage) {
        super(name, health, damage, SuperAbility.ROLL_DICE);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        int dice1 = RPG_Game.random.nextInt(6) + 1;
        int dice2 = RPG_Game.random.nextInt(6) + 1;

        System.out.println(getName() + " бросает кости: " + dice1 + " и " + dice2);

        if (dice1 == dice2) {
            int damage = dice1 * dice2;
            boss.setHealth(boss.getHealth() - damage);
            System.out.println(getName() + " отнимает: " + damage + " из здоровья босса");
        } else {
            int damage = dice1 + dice2;
            Hero randomHero = heroes[RPG_Game.random.nextInt(heroes.length)];

            while (randomHero == this || randomHero.getHealth() <= 0) {
                randomHero = heroes[RPG_Game.random.nextInt(heroes.length)];
            }

            randomHero.setHealth(randomHero.getHealth() - damage);
            System.out.println(getName() + " случайно отнимает: " + damage + " из здоровья " + randomHero.getName());
        }
    }
}
