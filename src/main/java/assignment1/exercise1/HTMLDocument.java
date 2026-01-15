package assignment1.exercise1;

public class HTMLDocument implements Document {
    private String content = "";

    @Override
    public void open() {
        System.out.println("[HTML] Opening HTML document...");
        System.out.println("[HTML] Initializing HTML parser and DOM builder.");
    }

    @Override
    public void save(String content) {
        this.content = content;
        System.out.println("[HTML] Saving content to HTML format...");
        System.out.println("[HTML] Wrapping content in HTML tags.");
        System.out.println("[HTML] Validating HTML structure.");
        System.out.println("[HTML] Document saved successfully!");
    }

    @Override
    public void display() {
        System.out.println("[HTML] Displaying HTML document:");
        System.out.println("<html>");
        System.out.println("  <head><title>HTML Document</title></head>");
        System.out.println("  <body>");
        System.out.println("    <p>" + content + "</p>");
        System.out.println("  </body>");
        System.out.println("</html>");
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getFormatName() {
        return "HTML";
    }
}
