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
public class Viking implements Warrior, Cloneable {

    protected int health;
    protected int damage;
    protected String squadName;
    protected String warriorName;

    public Viking(String name) {        
        this.health = 100;
        this.damage = 30;
        this.warriorName = name;
    }
    
    public Viking() {        
        this.health = 100;
        this.damage = 30;
        this.warriorName = "Viking";
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
        String str = "Викинг: имя " + this.warriorName + ", " + this.squadName + ", "
                + "здоровье " + this.health + ", урон " + this.damage;
        return str;
    }

    @Override
    public Viking clone() throws CloneNotSupportedException {       
        return (Viking) super.clone();
    }

    @Override
    public String getTypeWarrior() {
        return "Viking";
    }
    
    @Override
    public void setName(String name) {
        this.warriorName = name;
    }
}
