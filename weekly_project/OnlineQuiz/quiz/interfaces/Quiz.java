package quiz.interfaces;

public interface Quiz {
    void start();
    void submitAnswer(int questionIndex, String answer);
    void showResults();
}