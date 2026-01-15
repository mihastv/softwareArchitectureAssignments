package assignment1.bonus;

class WordDocument extends CarDocument {

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
        System.out.println("WORD DOCUMENT");
        if (car != null) {
            System.out.println("Title: " + padRight(title, 55));
            System.out.println("Date: " + padRight(getTimestamp(), 56));
        }

        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line.length() > 62) {
                System.out.println(line.substring(0, 59));
            } else {
                System.out.println(padRight(line, 62));
            }
        }
    }

    @Override
    public String getFormatName() {
        return "Word";
    }

    @Override
    public String exportContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<w:document>\n");
        sb.append("  <w:title>").append(title).append("</w:title>\n");
        sb.append("  <w:created>").append(getTimestamp()).append("</w:created>\n");

        if (car != null) {
            sb.append("  <w:vin>").append(car.getVin()).append("</w:vin>\n");
        }

        sb.append("  <w:body>\n");
        sb.append("    ").append(content.replace("\n", "\n    ")).append("\n");
        sb.append("  </w:body>\n");
        sb.append("</w:document>");
        return sb.toString();
    }

    private String padRight(String s, int width) {
        if (s.length() >= width) return s.substring(0, width);
        return String.format("%-" + width + "s", s);
    }
}
