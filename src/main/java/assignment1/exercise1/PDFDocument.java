package assignment1.exercise1;

public class PDFDocument implements Document {
    private String content = "";

    @Override
    public void open() {
        System.out.println("[PDF] Opening PDF document...");
        System.out.println("[PDF] Initializing PDF renderer and parser.");
    }

    @Override
    public void save(String content) {
        this.content = content;
        System.out.println("[PDF] Saving content to PDF format...");
        System.out.println("[PDF] Compressing and encoding content.");
        System.out.println("[PDF] Adding PDF metadata and structure.");
        System.out.println("[PDF] Document saved successfully!");
    }

    @Override
    public void display() {
        System.out.println("[PDF] Displaying PDF document:");
        System.out.println(" PDF DOCUMENT VIEWER ");
        System.out.println(formatContent(content, 36));
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getFormatName() {
        return "PDF";
    }

    private String formatContent(String text, int width) {
        if (text.length() > width) {
            return text.substring(0, width - 3) + "...";
        }
        return String.format("%-" + width + "s", text);
    }
}