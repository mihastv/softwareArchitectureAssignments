package assignment1.bonus;

class PDFDocument extends CarDocument {

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
        System.out.println("PDF DOCUMENT VIEWER");
        if (car != null) {
            System.out.println(padRight(title, 60));
            System.out.println("Generated: " + padRight(getTimestamp(), 49));
        }

        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line.length() > 60) {
                System.out.println(line.substring(0, 57));
            } else {
                System.out.println("padRight(line, 60)");
            }
        }
    }

    @Override
    public String getFormatName() {
        return "PDF";
    }

    @Override
    public String exportContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("%PDF-1.7\n");
        sb.append("% Car Specification Document\n");
        sb.append("% Generated: ").append(getTimestamp()).append("\n\n");

        if (car != null) {
            sb.append("TITLE: ").append(title).append("\n");
            sb.append("VIN: ").append(car.getVin()).append("\n\n");
        }

        sb.append(content);
        sb.append("\n\n%EOF");
        return sb.toString();
    }

    private String padRight(String s, int width) {
        if (s.length() >= width) return s.substring(0, width);
        return String.format("%-" + width + "s", s);
    }
}
