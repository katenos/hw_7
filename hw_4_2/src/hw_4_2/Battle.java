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
public class Battle { //в этом классе предполагалось описать сражение

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        List<Warrior> typesWarrior = new ArrayList<Warrior>(); //этот список нужно вынести куда-нибудь в другое место и сделать константой. в главном методе оставить только отображение окна.
        typesWarrior.add(new Archer());
        typesWarrior.add(new Viking());
        new MainFrame(typesWarrior).setVisible(true);
    }
}
