package backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class database {
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3333/test", "root", "123456");
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void insertQuestion(Connection conn, String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        try {
            String query = "INSERT INTO questions (question, optionA, optionB, optionC, optionD,correctanswer) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, question);
            preparedStatement.setString(2, optionA);
            preparedStatement.setString(3, optionB);
            preparedStatement.setString(4, optionC);
            preparedStatement.setString(5, optionD);
            preparedStatement.setString(6, correctAnswer);

            preparedStatement.executeUpdate();
            System.out.println("Question inserted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //fetch question from database
    public static List<Question> fetchQuestions(Connection conn, int limit) {
    List<Question> questions = new ArrayList<>();
    try {
        String query = "SELECT * FROM questions LIMIT ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, limit);
        ResultSet RS = preparedStatement.executeQuery();
        while (RS.next()) {
            Question question = new Question(RS.getString("question"), RS.getString("optionA"), RS.getString("optionB"), RS.getString("optionC"), RS.getString("optionD"), RS.getString("correctanswer"));
            questions.add(question);
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return questions;
}
}