package assignment1.exercise1;

public class MarkdownDocument implements Document {
    private String content = "";

    @Override
    public void open() {
        System.out.println("[MARKDOWN] Opening Markdown document...");
        System.out.println("[MARKDOWN] Loading Markdown parser.");
    }

    @Override
    public void save(String content) {
        this.content = content;
        System.out.println("[MARKDOWN] Saving content to Markdown format...");
        System.out.println("[MARKDOWN] Converting to .md structure.");
        System.out.println("[MARKDOWN] Document saved successfully!");
    }

    @Override
    public void display() {
        System.out.println("[MARKDOWN] Displaying Markdown document:");
        System.out.println("```markdown");
        System.out.println("# Document");
        System.out.println(content);
        System.out.println("```");
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getFormatName() {
        return "Markdown";
    }
}

