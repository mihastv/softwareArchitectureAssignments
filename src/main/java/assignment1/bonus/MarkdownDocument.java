package assignment1.bonus;

class MarkdownDocument extends CarDocument {

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
        System.out.println(exportContent());
        System.out.println("```");
    }

    @Override
    public String getFormatName() {
        return "Markdown";
    }

    @Override
    public String exportContent() {
        StringBuilder sb = new StringBuilder();

        if (car != null) {
            sb.append("# ").append(car.getYear()).append(" ").append(car.getModel()).append("\n\n");
            sb.append("**VIN:** `").append(car.getVin()).append("`\n\n");
            sb.append("**Generated:** ").append(getTimestamp()).append("\n\n");
        } else {
            sb.append("# Car Document\n\n");
        }

        sb.append(content);
        return sb.toString();
    }
}