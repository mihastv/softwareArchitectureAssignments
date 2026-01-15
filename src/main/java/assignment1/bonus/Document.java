package assignment1.bonus;

public interface Document {
    void open();
    void save(String content);
    void display();
    String getContent();
    String getFormatName();
    String exportContent();
}
