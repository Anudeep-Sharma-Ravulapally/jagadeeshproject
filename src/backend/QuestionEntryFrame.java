package backend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;


public class QuestionEntryFrame {
    private JFrame frame;
    public QuestionEntryFrame(){
        frame = new JFrame("Question Entry");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));
        frame.add(new JLabel("Enter the question:"));
        JTextField questionField = new JTextField();
        frame.add(questionField);
        OptionPanel aOption= new OptionPanel("A");
        frame.add(aOption);
        OptionPanel bOption= new OptionPanel("B");
        frame.add(bOption);
        OptionPanel cOption= new OptionPanel("C");
        frame.add(cOption);
        OptionPanel dOption= new OptionPanel("D");
        frame.add(dOption);
        JButton submitButton = new JButton("Submit");
        //add radio buttons for correct answer
        JRadioButton aButton = new JRadioButton("A");
        JRadioButton bButton = new JRadioButton("B");
        JRadioButton cButton = new JRadioButton("C");
        JRadioButton dButton = new JRadioButton("D");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(aButton);
        buttonGroup.add(bButton);
        buttonGroup.add(cButton);
        buttonGroup.add(dButton);
        JPanel radioPanel = new JPanel();
        JLabel radioLabel = new JLabel("Select the correct option:");
        radioPanel.setLayout(new FlowLayout());
        radioPanel.add(radioLabel);
        radioPanel.add(aButton);
        radioPanel.add(bButton);
        radioPanel.add(cButton);
        radioPanel.add(dButton);
        frame.add(radioPanel);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String question = questionField.getText();
                String optionA = aOption.getTextFieldContent();
                String optionB = bOption.getTextFieldContent();
                String optionC = cOption.getTextFieldContent();
                String optionD = dOption.getTextFieldContent();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = database.connect();
                    if(connection == null || optionA.isEmpty() || optionB.isEmpty() || optionC.isEmpty() || optionD.isEmpty() || question.isEmpty()){
                        System.out.println("Invalid input");
                        return;
                    }
                    String correctAnswer = "";
                    if(aButton.isSelected())
                        correctAnswer = optionA;
                    else if(bButton.isSelected())
                        correctAnswer = optionB;
                    else if(cButton.isSelected())
                        correctAnswer = optionC;
                    else if(dButton.isSelected())
                        correctAnswer = optionD;
                    else{
                        System.out.println("Select the correct answer");
                        return;
                    }
                    database.insertQuestion(connection, question, optionA, optionB, optionC, optionD, correctAnswer);
                } catch (Exception ex) {
                    if (ex instanceof ClassNotFoundException)
                    System.out.println("Driver not found");
                    else if(ex instanceof SQLException)
                    System.out.println("SQL Exception");
                    else
                    System.out.println("Some other exception");
                    return;
                }
            }
        });
        frame.add(submitButton);
        frame.setVisible(true);
        
    }
}
