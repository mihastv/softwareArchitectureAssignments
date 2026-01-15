package assignment1.exercise1;

import java.util.ArrayList;
import java.util.List;

public class DocumentEditor {
    private Document currentDocument;
    private final List<Document> openDocuments;

    public DocumentEditor() {
        this.openDocuments = new ArrayList<>();
        System.out.println("  DOCUMENT EDITOR INITIALIZED");
        System.out.println("  Supported formats: " + DocumentFactory.getSupportedTypes());
    }

    public void newDocument(String type) {
        System.out.println("\nCreating new " + type.toUpperCase() + " document ...");
        try {
            currentDocument = DocumentFactory.createDocument(type);
            currentDocument.open();
            openDocuments.add(currentDocument);
            System.out.println("New " + currentDocument.getFormatName() + " document created.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void saveContent(String content) {
        if (currentDocument == null) {
            System.out.println("Error: No document is currently open.");
            return;
        }
        System.out.println("\nSaving content ...");
        currentDocument.save(content);
    }

    public void displayDocument() {
        if (currentDocument == null) {
            System.out.println("Error: No document is currently open.");
            return;
        }
        System.out.println("\nDisplaying document ...");
        currentDocument.display();
    }

    public void listOpenDocuments() {
        System.out.println("\nOpen Documents ...");
        if (openDocuments.isEmpty()) {
            System.out.println("No documents are currently open.");
            return;
        }
        for (int i = 0; i < openDocuments.size(); i++) {
            Document doc = openDocuments.get(i);
            String marker = (doc == currentDocument) ? " (current)" : "";
            System.out.println((i + 1) + ". " + doc.getFormatName() + " Document" + marker);
        }
    }

    public String getCurrentFormat() {
        return currentDocument != null ? currentDocument.getFormatName() : "None";
    }
}