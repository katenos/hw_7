/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw_4_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author kate_
 */
public class MainFrame extends JFrame {

    private MainFrame.MyTextField nameSquard1;
    private MainFrame.MyTextField nameSquard2;
//    private JPanel panel;
    private JComboBox<String> selectSquad;
    private JComboBox<String> selectClassWarrior;
    private MyTextField nameWarrior;
    private JButton start;
    private JButton addWarrior;
    private JButton save;
    private JTextArea resultArea;
    private JLabel error;
    private JLabel message;

    private List<Warrior> typeWarrior;
    private Squad sq1;
    private Squad sq2;
    private StringBuilder strBResult = new StringBuilder();

    public MainFrame(List<Warrior> typeWarrior) throws Exception {
        super("Битва");
        this.typeWarrior = typeWarrior;
        setDefaultCloseOperation(3);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(620, 600));
        initComponents();
    }

    private void initComponents() {
        nameSquard1 = new MyTextField();
        nameSquard1.setText("Отряд 1");
        nameSquard2 = new MyTextField();
        nameSquard2.setText("Отряд 2");
        sq1 = new Squad("Отряд 1");
        sq2 = new Squad("Отряд 2");
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        selectSquad = new JComboBox(new String[]{"№1", "№2"});
        nameWarrior = new MyTextField();
        selectClassWarrior = new JComboBox(typesWarriorToString(typeWarrior).toArray());
        addWarrior = new JButton("Добавить");
        resultArea = new JTextArea(10, 10);
        resultArea.setFont(new Font(null, Font.PLAIN, 14));
        start = new JButton("Старт");
        save = new JButton("Сохранить в файл");
        save.setEnabled(false);
        createListeners();
        message = new JLabel();
        message.setForeground(Color.green);
        error = new JLabel();
        error.setForeground(Color.red);

        GridBagConstraints gridConstraints = new GridBagConstraints();
        GridBagConstraints gridConstraintsPanel = new GridBagConstraints();
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridheight = 1;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.weightx = 10;
        gridConstraints.insets = new Insets(10, 10, 0, 0);
        gridConstraintsPanel.fill = GridBagConstraints.BOTH;
        gridConstraintsPanel.gridheight = 1;
        gridConstraintsPanel.gridwidth = 1;
        gridConstraintsPanel.weightx = 10;
        gridConstraintsPanel.weighty = 10;
        gridConstraintsPanel.gridx = 0;
        gridConstraintsPanel.gridy = 0;
        gridConstraintsPanel.insets = new Insets(10, 10, 0, 0);
        add(new JLabel("Имя первого отряда"), gridConstraints);
        gridConstraints.gridx = 1;
        add(nameSquard1, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        add(new JLabel("Имя второго отряда"), gridConstraints);
        gridConstraints.gridx = 1;
        add(nameSquard2, gridConstraints);
        panel.add(new JLabel("Добавить бойца в отряд "), gridConstraintsPanel);
        gridConstraintsPanel.gridy = 1;
        panel.add(selectSquad, gridConstraintsPanel);
        gridConstraintsPanel.gridy = 2;
        panel.add(new JLabel("Имя: "), gridConstraintsPanel);
        gridConstraintsPanel.gridy = 3;
        panel.add(nameWarrior, gridConstraintsPanel);
        gridConstraintsPanel.gridy = 4;
        panel.add(selectClassWarrior, gridConstraintsPanel);
        gridConstraintsPanel.gridy = 5;
        panel.add(addWarrior, gridConstraintsPanel);
        gridConstraints.gridy = 2;
        gridConstraints.gridx = 0;
        add(panel, gridConstraints);
        gridConstraints.gridy = 3;
        add(new JLabel("Результаты битвы:"), gridConstraints);
        gridConstraints.gridy = 4;
        gridConstraints.gridwidth = 2;
        gridConstraints.gridheight = 10;
        gridConstraints.weighty = 1;
        gridConstraints.weightx = 1;
        add(resultArea, gridConstraints);
        JScrollPane txtAreaScroll = new JScrollPane(resultArea);
        txtAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(txtAreaScroll, gridConstraints);
        gridConstraints.gridheight = 1;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridy = 15;
        gridConstraints.gridx = 1;
        add(start, gridConstraints);
        gridConstraints.gridy = 16;
        add(save, gridConstraints);
        gridConstraints.gridy = 15;
        gridConstraints.gridx = 0;
        add(message, gridConstraints);
        add(error, gridConstraints);
        pack();
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter("result.txt")) {
            writer.write(resultArea.getText());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void createListeners() {
        addWarrior.addActionListener(e -> {
            error.setText("");
            message.setText("");
            if (!nameWarrior.getText().equals("")) {
                try {
                    getSelectedSquad().addWarrior(getSelectedWarrior().clone());
                    getSelectedSquad().getSquad().get(getSelectedSquad().getSquad().size() - 1).setName(nameWarrior.getText());
                    message.setText("Боец добавлен");
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    error.setText("Произошла непредвиденная ошибка");
                }
            } else {
                error.setText("Введите имя бойца");
            }
        });
        start.addActionListener(e -> {
            error.setText("");
            message.setText("");
            sq1.rename(nameSquard1.getText());
            sq2.rename(nameSquard2.getText());
            //изменение имени отряда у бойца, т.к. изначально при клонировании имя отряда не задано
            //можно было обойтись и без этого, но не хочется переписывать toString и вывод на экран результатов битвы            
            sq1.changeSquadNameOfWarrior();
            sq2.changeSquadNameOfWarrior();
            if (fieldsFill()) {
                outputCreatbleInfo();
                resultArea.setText(strBResult.toString());               
                start.setEnabled(false);
                save.setEnabled(true);
            }
        });
        save.addActionListener(e -> {
            saveToFile();
            message.setText("Сохранено");
            save.setEnabled(false);
        });
    }

    private class MyTextField extends JTextField {

        public MyTextField() {
            super(10);
            setFont(new Font(null, Font.PLAIN, 14));
        }
    }

    private ArrayList typesWarriorToString(List<Warrior> typeWarrior) {
        ArrayList types = new ArrayList();
        typeWarrior.forEach(name -> {
            types.add(name.getTypeWarrior());
        });
        return types;
    }

    private Squad getSelectedSquad() {
        switch (selectSquad.getSelectedIndex()) { //для двух вариантов использовать switch излишне. просто if-else же
            case 0:
                return sq1;
            case 1:
                return sq2;
        }
        return null;
    }

    private Warrior getSelectedWarrior() {
        return typeWarrior.get(selectClassWarrior.getSelectedIndex());
    }

    private boolean fieldsFill() {
        boolean result = true;
        if (nameSquard1.getText().equals("")) {
            result = false;
            error.setText("Введите имя первого отряда");
        }
        if (nameSquard2.getText().equals("")) {
            result = false;
            error.setText("Введите имя второго отряда");
        }
        if (sq1.getSquad().size() == 0) {
            result = false;
            error.setText("В первом отряде нет бойцов!");
        }
        if (sq2.getSquad().size() == 0) {
            result = false;
            error.setText("Во втором отряде нет бойцов!");
        }
        return result;
    }

    private void outputCreatbleInfo() {
        DateHelper d = new DateHelper();
        strBResult.append("Список бойцов\n");
        sq1.getSquad().forEach(sq -> strBResult.append(sq.toString() + "\n")); //в этом месте и далее одна конкатенация заменяется stringBuilder'om, зато другие все так же порождают лишние объекты. все плюсы заменить на append.
        strBResult.append("\n");
        sq2.getSquad().forEach(sq -> strBResult.append(sq.toString() + "\n"));
        strBResult.append("\nСражение началось!\n");
        strBResult.append(d.getFormattedStartDate());
        String nameWinner = battle(sq1, sq2, d);
        strBResult.append("\nПобедил " + nameWinner);
        strBResult.append("\nОбщее время поединка " + d.getFormattedDiff());
    }

    public String battle(Squad ot1, Squad ot2, DateHelper d) { //для реализации логики сражения есть класс Battle
        int i = 0;
        String nameWinner = "";
        while (nameWinner.equals("")) {
            strBResult.append("\nРаунд " + (++i));
            Warrior w1 = ot1.getRandomWarrior();
            Warrior w2 = ot2.getRandomWarrior();
            strBResult.append("\nБоец - " + w1.toString() + " атакует бойца\n       " + w2.toString());
            w2.takeDamage(w1.attack());
            d.skipTime();
            if (!ot2.hasAliveWarriors()) {
                nameWinner = ot1.toString();
                break;
            }
            w1 = ot1.getRandomWarrior(); //2 раза одно и тоже. нужно вынести в отдельный метод
            w2 = ot2.getRandomWarrior();
            strBResult.append("\nБоец - " + w2.toString() + " атакует бойца\n       " + w1.toString());
            w1.takeDamage(w2.attack());
            d.skipTime();
            if (!ot1.hasAliveWarriors()) {
                nameWinner = ot2.toString();
                break;
            }
        }
        return nameWinner;
    }
}
