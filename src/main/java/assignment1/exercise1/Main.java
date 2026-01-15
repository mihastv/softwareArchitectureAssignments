package assignment1.exercise1;

public class Main {
    public static void main(String[] args) {
        DocumentEditor editor = new DocumentEditor();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("  DEMO: Working with different document formats");
        System.out.println("=".repeat(50));

        editor.newDocument("pdf");
        editor.saveContent("This is my PDF report with important data.");
        editor.displayDocument();

        editor.newDocument("word");
        editor.saveContent("Meeting notes from today's discussion.");
        editor.displayDocument();

        editor.newDocument("html");
        editor.saveContent("Welcome to my website!");
        editor.displayDocument();

        editor.listOpenDocuments();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("  DEMO: Extending with new document type");
        System.out.println("=".repeat(50));

        DocumentFactory.registerDocument("md", MarkdownDocument::new);
        DocumentFactory.registerDocument("markdown", MarkdownDocument::new);

        editor.newDocument("markdown");
        editor.saveContent("This is **bold** and this is *italic*.");
        editor.displayDocument();

        editor.listOpenDocuments();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("  DEMO: Handling unsupported format");
        System.out.println("=".repeat(50));
        editor.newDocument("xyz");

        System.out.println("\n" + "=".repeat(50));
        System.out.println("  DEMO COMPLETE");
        System.out.println("=".repeat(50));
    }
}
