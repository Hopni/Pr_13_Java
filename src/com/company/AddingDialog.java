package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.zip.DataFormatException;

public class AddingDialog extends JDialog {
    private JTextField surnameField;
    private JTextField studentRecordBookField;
    private JTextField ratingField;

    public AddingDialog(MainFrame owner){
        setTitle("Add student");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setLayout(new GridLayout(4, 2));
        setBounds(550, 200, 400, 250);

        JLabel surnameLabel = new JLabel("Surname");
        JLabel studentRecordBookLabel = new JLabel("Student Record Book");
        JLabel ratingLabel = new JLabel("Rating (Format: Subject Rating)");
        surnameField = new JTextField();
        studentRecordBookField = new JTextField();
        ratingField = new JTextField();
        JButton add = new JButton("Add");
        JButton cancel = new JButton("Cancel");

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!surnameField.getText().isEmpty() && !studentRecordBookField.getText().isEmpty() && !ratingField.getText().isEmpty()){
                    try {
                        Student student = new Student(studentRecordBookField.getText() + " " + surnameField.getText() + " " + ratingField.getText());
                        owner.getModelLeft().addElement(student);
                        if(student.isHighAchiever()){
                            owner.getModelRight().addElement(student);
                            owner.getModelRight().sort();
                        }
                        owner.getStudentsCount().setText("Students " + owner.getModelLeft().getSize());
                        owner.getHighAchieversCount().setText("High achiever " + owner.getModelRight().getSize());
                        surnameField.setText("");
                        studentRecordBookField.setText("");
                        ratingField.setText("");
                    } catch (DataFormatException | NumberFormatException fe) {
                        JOptionPane.showMessageDialog(null, "Incorrect data");
                    }
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.add(studentRecordBookLabel);
        this.add(studentRecordBookField);
        this.add(surnameLabel);
        this.add(surnameField);
        this.add(ratingLabel);
        this.add(ratingField);
        this.add(add);
        this.add(cancel);
    }
}
