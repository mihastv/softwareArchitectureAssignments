package assignment1.exercise1;

public class WordDocument implements Document {
    private String content = "";

    @Override
    public void open() {
        System.out.println("[WORD] Opening Word document...");
        System.out.println("[WORD] Loading DOCX parser and styles.");
    }

    @Override
    public void save(String content) {
        this.content = content;
        System.out.println("[WORD] Saving content to Word format...");
        System.out.println("[WORD] Applying styles and formatting.");
        System.out.println("[WORD] Creating DOCX package structure.");
        System.out.println("[WORD] Document saved successfully!");
    }

    @Override
    public void display() {
        System.out.println("[WORD] Displaying Word document:");
        System.out.println(formatContent(content, 36));
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getFormatName() {
        return "Word";
    }

    private String formatContent(String text, int width) {
        if (text.length() > width) {
            return text.substring(0, width - 3) + "...";
        }
        return String.format("%-" + width + "s", text);
    }
}
