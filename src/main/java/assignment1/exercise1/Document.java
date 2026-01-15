package assignment1.exercise1;

public interface Document {
    void open();
    void save(String content);
    void display();
    String getContent();
    String getFormatName();
}