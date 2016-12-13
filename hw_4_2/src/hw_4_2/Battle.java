/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw_4_2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kate_
 */
public class Battle {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    private static List<Warrior> typesWarrior = new ArrayList<Warrior>();

    public static void main(String[] args) throws Exception {
        addClass();
        new MainFrame(typesWarrior).setVisible(true);
    }

    private static void addClass() {
        typesWarrior.add(new Archer());
        typesWarrior.add(new Viking());
    }

    public StringBuilder outputCreatbleInfo(Squad sq1, Squad sq2) {
        StringBuilder strBResult = new StringBuilder();
        DateHelper d = new DateHelper();
        strBResult.append("Список бойцов\n");
        sq1.getSquad().forEach(sq -> strBResult.append(sq.toString() + "\n"));
        strBResult.append("\n");
        sq2.getSquad().forEach(sq -> strBResult.append(sq.toString() + "\n"));
        strBResult.append("\nСражение началось!\n");
        strBResult.append(d.getFormattedStartDate());
        strBResult.append(battle(sq1, sq2, d));
        return strBResult;
    }

    private StringBuilder battle(Squad ot1, Squad ot2, DateHelper d) {
        StringBuilder strBResult = new StringBuilder();
        Warrior w1;
        Warrior w2;
        int i = 0;
        String nameWinner = "";
        while (nameWinner.equals("")) {
            strBResult.append("\nРаунд " + (++i));
            w1 = ot1.getRandomWarrior();
            w2 = ot2.getRandomWarrior();
            strBResult.append(attackRaund(w1, w2));
            d.skipTime();
            if (!ot2.hasAliveWarriors()) {
                nameWinner = ot1.toString();
                break;
            }
            w1 = ot1.getRandomWarrior();
            w2 = ot2.getRandomWarrior();
            strBResult.append(attackRaund(w2, w1));
            d.skipTime();
            if (!ot1.hasAliveWarriors()) {
                nameWinner = ot2.toString();
                break;
            }
        }
        strBResult.append("\nПобедил " + nameWinner);
        strBResult.append("\nОбщее время поединка " + d.getFormattedDiff());
        return strBResult;
    }

    private StringBuilder attackRaund(Warrior w1, Warrior w2) {
        StringBuilder strBResult = new StringBuilder();
        strBResult.append("\nБоец - " + w1.toString() + " атакует бойца\n       " + w2.toString());
        w2.takeDamage(w1.attack());
        return strBResult;
    }
}
