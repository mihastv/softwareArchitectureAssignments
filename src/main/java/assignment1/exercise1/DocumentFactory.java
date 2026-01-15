package assignment1.exercise1;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DocumentFactory {
    private static final Map<String, Supplier<Document>> registry = new HashMap<>();

    static {
        registerDocument("pdf", PDFDocument::new);
        registerDocument("word", WordDocument::new);
        registerDocument("docx", WordDocument::new);
        registerDocument("html", HTMLDocument::new);
        registerDocument("htm", HTMLDocument::new);
    }

    public static void registerDocument(String type, Supplier<Document> supplier) {
        registry.put(type.toLowerCase(), supplier);
        System.out.println("[Factory] Registered document type: " + type.toUpperCase());
    }

    public static Document createDocument(String type) {
        Supplier<Document> supplier = registry.get(type.toLowerCase());

        if (supplier == null) {
            throw new IllegalArgumentException(
                    "Unsupported document type: " + type +
                            ". Supported types: " + getSupportedTypes()
            );
        }

        return supplier.get();
    }

    public static boolean isTypeSupported(String type) {
        return registry.containsKey(type.toLowerCase());
    }

    public static String getSupportedTypes() {
        return String.join(", ", registry.keySet());
    }
}