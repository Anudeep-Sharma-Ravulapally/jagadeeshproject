package backend;

public class Question {
    //fetch question from database
    public String QuestionBody;
    public String OptionA;
    public String OptionB;
    public String OptionC;
    public String OptionD;
    public String CorrectAnswer;
    public Question(String QuestionBody, String OptionA, String OptionB, String OptionC, String OptionD, String CorrectAnswer){
        this.QuestionBody = QuestionBody;
        this.OptionA = OptionA;
        this.OptionB = OptionB;
        this.OptionC = OptionC;
        this.OptionD = OptionD;
        this.CorrectAnswer = CorrectAnswer;
    }
}
