package backend;
import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel{
    private JLabel label;
    private JTextField textField;

    public OptionPanel(String Option) {
        label = new JLabel("Enter Option "+Option+" :");
        textField = new JTextField(20);
        setLayout(new GridLayout(0, 2));
        add(label);
        add(textField);
    }

    public String getTextFieldContent() {
        return textField.getText();
    }
}