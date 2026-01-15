package assignment1.bonus;

class HTMLDocument extends CarDocument {

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
        System.out.println(exportContent());
    }

    @Override
    public String getFormatName() {
        return "HTML";
    }

    @Override
    public String exportContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html lang=\"en\">\n");
        sb.append("<head>\n");
        sb.append("    <meta charset=\"UTF-8\">\n");
        sb.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        sb.append("    <title>").append(title != null ? title : "Car Document").append("</title>\n");
        sb.append("    <style>\n");
        sb.append("        body { font-family: Arial, sans-serif; margin: 40px; }\n");
        sb.append("        .header { background: #333; color: white; padding: 20px; }\n");
        sb.append("        .section { margin: 20px 0; padding: 15px; border: 1px solid #ddd; }\n");
        sb.append("        .price { font-size: 1.5em; color: #2e7d32; font-weight: bold; }\n");
        sb.append("        table { width: 100%; border-collapse: collapse; }\n");
        sb.append("        th, td { padding: 10px; text-align: left; border-bottom: 1px solid #ddd; }\n");
        sb.append("    </style>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");

        if (car != null) {
            sb.append("    <div class=\"header\">\n");
            sb.append("        <h1>").append(car.getYear()).append(" ").append(car.getModel()).append("</h1>\n");
            sb.append("        <p>VIN: ").append(car.getVin()).append("</p>\n");
            sb.append("        <p>Generated: ").append(getTimestamp()).append("</p>\n");
            sb.append("    </div>\n");
        }

        sb.append("    <div class=\"content\">\n");
        sb.append("        ").append(content.replace("\n", "<br>\n        ")).append("\n");
        sb.append("    </div>\n");
        sb.append("</body>\n");
        sb.append("</html>");
        return sb.toString();
    }
}
