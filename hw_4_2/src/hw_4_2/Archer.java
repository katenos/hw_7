/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw_4_2;

/**
 *
 * @author kate_
 */
public class Archer implements Warrior, Cloneable {

    protected int health; //почему protected?
    protected int damage;
    protected String squadName;
    protected String warriorName;
//    private Random rand = new Random();

    public Archer(String name) {
        this.health = 70;
        this.damage = 40;
        this.warriorName = name;
    }

    public Archer() {
        this.health = 70;
        this.damage = 40;
        this.warriorName = "Archer";
    }

    @Override
    public int attack() {
        return damage;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void setSquadName(String name) {
        this.squadName = name;
    }

    @Override
    public String toString() {
        String str = "Лучник: имя " + this.warriorName + ", " + this.squadName + ", "
                + "здоровье " + this.health + ", урон " + this.damage;
        return str;
    }

    @Override
    public String getTypeWarrior() {
        return "Archer";
    }

    @Override
    public Archer clone() throws CloneNotSupportedException {
        Archer clone = (Archer) super.clone();
        return clone;
    }

    @Override
    public void setName(String name) {
        this.warriorName = name;
    }
}
