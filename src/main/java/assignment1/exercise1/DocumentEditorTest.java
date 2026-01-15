package assignment1.exercise1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentEditorTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    private String getOutput() {
        return outputStream.toString();
    }

    private void clearOutput() {
        outputStream.reset();
    }

    @Test
    @DisplayName("PDF: Should return correct format name")
    public void pdfDocument_getFormatName_ShouldReturnPDF() {
        PDFDocument pdf = new PDFDocument();
        assertEquals("PDF", pdf.getFormatName());
    }

    @Test
    @DisplayName("PDF: Should return empty content initially")
    public void pdfDocument_getContent_ShouldReturnEmptyInitially() {
        PDFDocument pdf = new PDFDocument();
        assertEquals("", pdf.getContent());
    }

    @Test
    @DisplayName("PDF: Should open document successfully")
    public void pdfDocument_open_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        pdf.open();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("PDF: Should save and store content")
    public void pdfDocument_save_ShouldStoreContent() {
        PDFDocument pdf = new PDFDocument();
        String content = "Test PDF content";
        pdf.save(content);
        assertEquals(content, pdf.getContent());
    }

    @Test
    @DisplayName("PDF: Should save empty content")
    public void pdfDocument_save_EmptyContent_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        pdf.save("");
        assertEquals("", pdf.getContent());
    }

    @Test
    @DisplayName("PDF: Should save content with special characters")
    public void pdfDocument_save_SpecialCharacters_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        String specialContent = "Special chars: @#$%^&*()_+{}|:<>?~`";
        pdf.save(specialContent);
        assertEquals(specialContent, pdf.getContent());
    }

    @Test
    @DisplayName("PDF: Should save content with unicode characters")
    public void pdfDocument_save_UnicodeCharacters_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        String unicodeContent = "Unicode: 日本語 中文 한국어 émojis: 🎉🚀";
        pdf.save(unicodeContent);
        assertEquals(unicodeContent, pdf.getContent());
    }

    @Test
    @DisplayName("PDF: Should save multiline content")
    public void pdfDocument_save_MultilineContent_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        String multilineContent = "Line 1\nLine 2\nLine 3";
        pdf.save(multilineContent);
        assertEquals(multilineContent, pdf.getContent());
    }

    @Test
    @DisplayName("PDF: Should display document")
    public void pdfDocument_display_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        pdf.save("Test content");
        clearOutput();
        pdf.display();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("PDF: Should display empty content without error")
    public void pdfDocument_display_EmptyContent_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        pdf.save("");
        clearOutput();
        pdf.display();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("PDF: Should overwrite previous content on save")
    public void pdfDocument_save_Overwrite_ShouldReplaceContent() {
        PDFDocument pdf = new PDFDocument();
        pdf.save("First content");
        pdf.save("Second content");
        assertEquals("Second content", pdf.getContent());
    }

    @Test
    @DisplayName("PDF: Full lifecycle test")
    public void pdfDocument_fullLifecycle_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        pdf.open();
        pdf.save("Lifecycle content");
        pdf.display();
        assertEquals("Lifecycle content", pdf.getContent());
        assertEquals("PDF", pdf.getFormatName());
    }

    @Test
    @DisplayName("Word: Should return correct format name")
    public void wordDocument_getFormatName_ShouldReturnWord() {
        WordDocument word = new WordDocument();
        assertEquals("Word", word.getFormatName());
    }

    @Test
    @DisplayName("Word: Should return empty content initially")
    public void wordDocument_getContent_ShouldReturnEmptyInitially() {
        WordDocument word = new WordDocument();
        assertEquals("", word.getContent());
    }

    @Test
    @DisplayName("Word: Should open document successfully")
    public void wordDocument_open_ShouldWork() {
        WordDocument word = new WordDocument();
        word.open();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Word: Should save and store content")
    public void wordDocument_save_ShouldStoreContent() {
        WordDocument word = new WordDocument();
        String content = "Test Word content";
        word.save(content);
        assertEquals(content, word.getContent());
    }

    @Test
    @DisplayName("Word: Should save empty content")
    public void wordDocument_save_EmptyContent_ShouldWork() {
        WordDocument word = new WordDocument();
        word.save("");
        assertEquals("", word.getContent());
    }

    @Test
    @DisplayName("Word: Should save content with special characters")
    public void wordDocument_save_SpecialCharacters_ShouldWork() {
        WordDocument word = new WordDocument();
        String specialContent = "Special: !@#$%^&*()_+-=[]{}|;':\",./<>?";
        word.save(specialContent);
        assertEquals(specialContent, word.getContent());
    }

    @Test
    @DisplayName("Word: Should save content with unicode characters")
    public void wordDocument_save_UnicodeCharacters_ShouldWork() {
        WordDocument word = new WordDocument();
        String unicodeContent = "Greek: αβγδ, Cyrillic: абвг, Arabic: ابجد";
        word.save(unicodeContent);
        assertEquals(unicodeContent, word.getContent());
    }

    @Test
    @DisplayName("Word: Should display document")
    public void wordDocument_display_ShouldWork() {
        WordDocument word = new WordDocument();
        word.save("Brief");
        clearOutput();
        word.display();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Word: Should display empty content without error")
    public void wordDocument_display_EmptyContent_ShouldWork() {
        WordDocument word = new WordDocument();
        word.save("");
        clearOutput();
        word.display();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Word: Should overwrite previous content on save")
    public void wordDocument_save_Overwrite_ShouldReplaceContent() {
        WordDocument word = new WordDocument();
        word.save("Original");
        word.save("Replaced");
        assertEquals("Replaced", word.getContent());
    }

    @Test
    @DisplayName("Word: Full lifecycle test")
    public void wordDocument_fullLifecycle_ShouldWork() {
        WordDocument word = new WordDocument();
        word.open();
        word.save("Word lifecycle");
        word.display();
        assertEquals("Word lifecycle", word.getContent());
        assertEquals("Word", word.getFormatName());
    }

    @Test
    @DisplayName("HTML: Should return correct format name")
    public void htmlDocument_getFormatName_ShouldReturnHTML() {
        HTMLDocument html = new HTMLDocument();
        assertEquals("HTML", html.getFormatName());
    }

    @Test
    @DisplayName("HTML: Should return empty content initially")
    public void htmlDocument_getContent_ShouldReturnEmptyInitially() {
        HTMLDocument html = new HTMLDocument();
        assertEquals("", html.getContent());
    }

    @Test
    @DisplayName("HTML: Should open document successfully")
    public void htmlDocument_open_ShouldWork() {
        HTMLDocument html = new HTMLDocument();
        html.open();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("HTML: Should save and store content")
    public void htmlDocument_save_ShouldStoreContent() {
        HTMLDocument html = new HTMLDocument();
        String content = "Test HTML content";
        html.save(content);
        assertEquals(content, html.getContent());
    }

    @Test
    @DisplayName("HTML: Should save empty content")
    public void htmlDocument_save_EmptyContent_ShouldWork() {
        HTMLDocument html = new HTMLDocument();
        html.save("");
        assertEquals("", html.getContent());
    }

    @Test
    @DisplayName("HTML: Should save content with HTML entities")
    public void htmlDocument_save_HTMLEntities_ShouldWork() {
        HTMLDocument html = new HTMLDocument();
        String htmlEntities = "<script>alert('test')</script>&amp;&lt;&gt;";
        html.save(htmlEntities);
        assertEquals(htmlEntities, html.getContent());
    }

    @Test
    @DisplayName("HTML: Should display document")
    public void htmlDocument_display_ShouldWork() {
        HTMLDocument html = new HTMLDocument();
        html.save("Hello HTML World");
        clearOutput();
        html.display();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("HTML: Should display empty content without error")
    public void htmlDocument_display_EmptyContent_ShouldWork() {
        HTMLDocument html = new HTMLDocument();
        html.save("");
        clearOutput();
        html.display();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("HTML: Should overwrite previous content on save")
    public void htmlDocument_save_Overwrite_ShouldReplaceContent() {
        HTMLDocument html = new HTMLDocument();
        html.save("First HTML");
        html.save("Second HTML");
        assertEquals("Second HTML", html.getContent());
    }

    @Test
    @DisplayName("HTML: Full lifecycle test")
    public void htmlDocument_fullLifecycle_ShouldWork() {
        HTMLDocument html = new HTMLDocument();
        html.open();
        html.save("HTML lifecycle");
        html.display();
        assertEquals("HTML lifecycle", html.getContent());
        assertEquals("HTML", html.getFormatName());
    }

    @Test
    @DisplayName("Markdown: Should return correct format name")
    public void markdownDocument_getFormatName_ShouldReturnMarkdown() {
        MarkdownDocument md = new MarkdownDocument();
        assertEquals("Markdown", md.getFormatName());
    }

    @Test
    @DisplayName("Markdown: Should return empty content initially")
    public void markdownDocument_getContent_ShouldReturnEmptyInitially() {
        MarkdownDocument md = new MarkdownDocument();
        assertEquals("", md.getContent());
    }

    @Test
    @DisplayName("Markdown: Should open document successfully")
    public void markdownDocument_open_ShouldWork() {
        MarkdownDocument md = new MarkdownDocument();
        md.open();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Markdown: Should save and store content")
    public void markdownDocument_save_ShouldStoreContent() {
        MarkdownDocument md = new MarkdownDocument();
        String content = "# Test Markdown";
        md.save(content);
        assertEquals(content, md.getContent());
    }

    @Test
    @DisplayName("Markdown: Should save empty content")
    public void markdownDocument_save_EmptyContent_ShouldWork() {
        MarkdownDocument md = new MarkdownDocument();
        md.save("");
        assertEquals("", md.getContent());
    }

    @Test
    @DisplayName("Markdown: Should save markdown syntax")
    public void markdownDocument_save_MarkdownSyntax_ShouldWork() {
        MarkdownDocument md = new MarkdownDocument();
        String markdownSyntax = "# H1\n## H2\n**bold** *italic* `code`\n- list item";
        md.save(markdownSyntax);
        assertEquals(markdownSyntax, md.getContent());
    }

    @Test
    @DisplayName("Markdown: Should display document")
    public void markdownDocument_display_ShouldWork() {
        MarkdownDocument md = new MarkdownDocument();
        md.save("**Bold text**");
        clearOutput();
        md.display();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Markdown: Should display empty content")
    public void markdownDocument_display_EmptyContent_ShouldWork() {
        MarkdownDocument md = new MarkdownDocument();
        md.save("");
        clearOutput();
        md.display();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Markdown: Should overwrite previous content on save")
    public void markdownDocument_save_Overwrite_ShouldReplaceContent() {
        MarkdownDocument md = new MarkdownDocument();
        md.save("# First");
        md.save("# Second");
        assertEquals("# Second", md.getContent());
    }

    @Test
    @DisplayName("Markdown: Full lifecycle test")
    public void markdownDocument_fullLifecycle_ShouldWork() {
        MarkdownDocument md = new MarkdownDocument();
        md.open();
        md.save("# Markdown lifecycle");
        md.display();
        assertEquals("# Markdown lifecycle", md.getContent());
        assertEquals("Markdown", md.getFormatName());
    }

    @Test
    @DisplayName("Factory: Should create PDF document")
    public void factory_createDocument_PDF_ShouldReturnPDFDocument() {
        Document doc = DocumentFactory.createDocument("pdf");
        assertNotNull(doc);
        assertInstanceOf(PDFDocument.class, doc);
        assertEquals("PDF", doc.getFormatName());
    }

    @Test
    @DisplayName("Factory: Should create Word document")
    public void factory_createDocument_Word_ShouldReturnWordDocument() {
        Document doc = DocumentFactory.createDocument("word");
        assertNotNull(doc);
        assertInstanceOf(WordDocument.class, doc);
        assertEquals("Word", doc.getFormatName());
    }

    @Test
    @DisplayName("Factory: Should create Word document with docx alias")
    public void factory_createDocument_Docx_ShouldReturnWordDocument() {
        Document doc = DocumentFactory.createDocument("docx");
        assertNotNull(doc);
        assertInstanceOf(WordDocument.class, doc);
    }

    @Test
    @DisplayName("Factory: Should create HTML document")
    public void factory_createDocument_HTML_ShouldReturnHTMLDocument() {
        Document doc = DocumentFactory.createDocument("html");
        assertNotNull(doc);
        assertInstanceOf(HTMLDocument.class, doc);
        assertEquals("HTML", doc.getFormatName());
    }

    @Test
    @DisplayName("Factory: Should create HTML document with htm alias")
    public void factory_createDocument_Htm_ShouldReturnHTMLDocument() {
        Document doc = DocumentFactory.createDocument("htm");
        assertNotNull(doc);
        assertInstanceOf(HTMLDocument.class, doc);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PDF", "Pdf", "pDf", "pdf", "pDF"})
    @DisplayName("Factory: Should handle case-insensitive PDF type names")
    public void factory_createDocument_PDF_CaseInsensitive(String type) {
        Document doc = DocumentFactory.createDocument(type);
        assertNotNull(doc);
        assertInstanceOf(PDFDocument.class, doc);
    }

    @ParameterizedTest
    @ValueSource(strings = {"WORD", "Word", "wOrD", "word"})
    @DisplayName("Factory: Should handle case-insensitive Word type names")
    public void factory_createDocument_Word_CaseInsensitive(String type) {
        Document doc = DocumentFactory.createDocument(type);
        assertNotNull(doc);
        assertInstanceOf(WordDocument.class, doc);
    }

    @ParameterizedTest
    @ValueSource(strings = {"HTML", "Html", "html", "hTmL"})
    @DisplayName("Factory: Should handle case-insensitive HTML type names")
    public void factory_createDocument_HTML_CaseInsensitive(String type) {
        Document doc = DocumentFactory.createDocument(type);
        assertNotNull(doc);
        assertInstanceOf(HTMLDocument.class, doc);
    }

    @Test
    @DisplayName("Factory: Should throw exception for unsupported type")
    public void factory_createDocument_UnsupportedType_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> DocumentFactory.createDocument("xyz"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"unknown", "doc", "txt", "rtf", "odt", "pages"})
    @DisplayName("Factory: Should throw exception for various unsupported types")
    public void factory_createDocument_VariousUnsupportedTypes_ShouldThrowException(String type) {
        assertThrows(IllegalArgumentException.class, () -> DocumentFactory.createDocument(type));
    }

    @Test
    @DisplayName("Factory: Should register new document type")
    public void factory_registerDocument_ShouldAddNewType() {
        String customType = "customtype" + System.currentTimeMillis();
        DocumentFactory.registerDocument(customType, MarkdownDocument::new);
        assertTrue(DocumentFactory.isTypeSupported(customType));
        Document doc = DocumentFactory.createDocument(customType);
        assertNotNull(doc);
    }

    @Test
    @DisplayName("Factory: Should override existing type on re-registration")
    public void factory_registerDocument_Override_ShouldWork() {
        String type = "overridetype" + System.currentTimeMillis();
        DocumentFactory.registerDocument(type, PDFDocument::new);
        Document first = DocumentFactory.createDocument(type);
        assertInstanceOf(PDFDocument.class, first);

        DocumentFactory.registerDocument(type, HTMLDocument::new);
        Document second = DocumentFactory.createDocument(type);
        assertInstanceOf(HTMLDocument.class, second);
    }

    @Test
    @DisplayName("Factory: Should check if type is supported - positive cases")
    public void factory_isTypeSupported_SupportedTypes_ShouldReturnTrue() {
        assertTrue(DocumentFactory.isTypeSupported("pdf"));
        assertTrue(DocumentFactory.isTypeSupported("word"));
        assertTrue(DocumentFactory.isTypeSupported("docx"));
        assertTrue(DocumentFactory.isTypeSupported("html"));
        assertTrue(DocumentFactory.isTypeSupported("htm"));
    }

    @Test
    @DisplayName("Factory: Should check if type is supported - negative cases")
    public void factory_isTypeSupported_UnsupportedTypes_ShouldReturnFalse() {
        assertFalse(DocumentFactory.isTypeSupported("unknown"));
        assertFalse(DocumentFactory.isTypeSupported("xyz"));
        assertFalse(DocumentFactory.isTypeSupported("doc"));
        assertFalse(DocumentFactory.isTypeSupported("txt"));
    }

    @Test
    @DisplayName("Factory: isTypeSupported should be case-insensitive")
    public void factory_isTypeSupported_CaseInsensitive_ShouldWork() {
        assertTrue(DocumentFactory.isTypeSupported("PDF"));
        assertTrue(DocumentFactory.isTypeSupported("Pdf"));
        assertTrue(DocumentFactory.isTypeSupported("WORD"));
        assertTrue(DocumentFactory.isTypeSupported("HTML"));
    }

    @Test
    @DisplayName("Factory: Should return supported types string")
    public void factory_getSupportedTypes_ShouldReturnNonEmptyString() {
        String supportedTypes = DocumentFactory.getSupportedTypes();
        assertNotNull(supportedTypes);
        assertFalse(supportedTypes.isEmpty());
    }

    @Test
    @DisplayName("Factory: Should create independent document instances")
    public void factory_createDocument_ShouldCreateIndependentInstances() {
        Document doc1 = DocumentFactory.createDocument("pdf");
        Document doc2 = DocumentFactory.createDocument("pdf");

        assertNotSame(doc1, doc2);

        doc1.save("Content 1");
        doc2.save("Content 2");

        assertEquals("Content 1", doc1.getContent());
        assertEquals("Content 2", doc2.getContent());
    }

    @Test
    @DisplayName("Factory: Multiple registrations should all work")
    public void factory_registerDocument_MultipleRegistrations_ShouldWork() {
        long timestamp = System.currentTimeMillis();
        DocumentFactory.registerDocument("type1" + timestamp, PDFDocument::new);
        DocumentFactory.registerDocument("type2" + timestamp, WordDocument::new);
        DocumentFactory.registerDocument("type3" + timestamp, HTMLDocument::new);

        assertTrue(DocumentFactory.isTypeSupported("type1" + timestamp));
        assertTrue(DocumentFactory.isTypeSupported("type2" + timestamp));
        assertTrue(DocumentFactory.isTypeSupported("type3" + timestamp));
    }

    @Test
    @DisplayName("Editor: Should initialize successfully")
    public void editor_constructor_ShouldWork() {
        DocumentEditor editor = new DocumentEditor();
        assertNotNull(editor);
    }

    @Test
    @DisplayName("Editor: Should create new PDF document")
    public void editor_newDocument_PDF_ShouldCreateDocument() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("pdf");
        assertEquals("PDF", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Editor: Should create new Word document")
    public void editor_newDocument_Word_ShouldCreateDocument() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("word");
        assertEquals("Word", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Editor: Should create new HTML document")
    public void editor_newDocument_HTML_ShouldCreateDocument() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("html");
        assertEquals("HTML", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Editor: Should handle unsupported document type gracefully")
    public void editor_newDocument_UnsupportedType_ShouldHandleGracefully() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("unsupported");
        assertEquals("None", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Editor: Should handle multiple unsupported types")
    public void editor_newDocument_MultipleUnsupportedTypes_ShouldHandleGracefully() {
        DocumentEditor editor = new DocumentEditor();

        editor.newDocument("xyz");
        assertEquals("None", editor.getCurrentFormat());

        editor.newDocument("abc");
        assertEquals("None", editor.getCurrentFormat());

        editor.newDocument("123");
        assertEquals("None", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Editor: Should save content to current document")
    public void editor_saveContent_ShouldSaveToCurrentDocument() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("pdf");
        clearOutput();
        editor.saveContent("Test content");
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Editor: Should save empty content")
    public void editor_saveContent_EmptyContent_ShouldWork() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("html");
        editor.saveContent("");
    }

    @Test
    @DisplayName("Editor: Should save content with special characters")
    public void editor_saveContent_SpecialCharacters_ShouldWork() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("word");
        editor.saveContent("Special: @#$%^&*()");
    }

    @Test
    @DisplayName("Editor: Should handle saving with no document open")
    public void editor_saveContent_NoDocument_ShouldHandleGracefully() {
        DocumentEditor editor = new DocumentEditor();
        editor.saveContent("Test content");
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Editor: Should display current document")
    public void editor_displayDocument_ShouldDisplayContent() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("html");
        editor.saveContent("Hello World");
        clearOutput();
        editor.displayDocument();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Editor: Should display PDF document correctly")
    public void editor_displayDocument_PDF_ShouldWork() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("pdf");
        editor.saveContent("PDF display test");
        clearOutput();
        editor.displayDocument();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Editor: Should display Word document correctly")
    public void editor_displayDocument_Word_ShouldWork() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("word");
        editor.saveContent("Word display test");
        clearOutput();
        editor.displayDocument();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Editor: Should handle displaying with no document open")
    public void editor_displayDocument_NoDocument_ShouldHandleGracefully() {
        DocumentEditor editor = new DocumentEditor();
        editor.displayDocument();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Editor: Should list open documents with single document")
    public void editor_listOpenDocuments_SingleDocument_ShouldList() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("pdf");
        clearOutput();
        editor.listOpenDocuments();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Editor: Should list all open documents")
    public void editor_listOpenDocuments_MultipleDocuments_ShouldListAll() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("pdf");
        editor.newDocument("word");
        editor.newDocument("html");
        clearOutput();
        editor.listOpenDocuments();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Editor: Should handle empty document list")
    public void editor_listOpenDocuments_Empty_ShouldHandleGracefully() {
        DocumentEditor editor = new DocumentEditor();
        clearOutput();
        editor.listOpenDocuments();
        String output = getOutput();
        assertFalse(output.isEmpty());
    }

    @Test
    @DisplayName("Editor: Should return None for format when no document open")
    public void editor_getCurrentFormat_NoDocument_ShouldReturnNone() {
        DocumentEditor editor = new DocumentEditor();
        assertEquals("None", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Editor: Should return correct format for current document")
    public void editor_getCurrentFormat_WithDocument_ShouldReturnFormat() {
        DocumentEditor editor = new DocumentEditor();

        editor.newDocument("pdf");
        assertEquals("PDF", editor.getCurrentFormat());

        editor.newDocument("word");
        assertEquals("Word", editor.getCurrentFormat());

        editor.newDocument("html");
        assertEquals("HTML", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Editor: Should switch current document when creating new one")
    public void editor_newDocument_ShouldSwitchCurrentDocument() {
        DocumentEditor editor = new DocumentEditor();

        editor.newDocument("pdf");
        assertEquals("PDF", editor.getCurrentFormat());

        editor.newDocument("word");
        assertEquals("Word", editor.getCurrentFormat());

        editor.newDocument("html");
        assertEquals("HTML", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Editor: Failed document creation should not change current")
    public void editor_newDocument_Failed_ShouldNotChangeCurrentDocument() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("pdf");
        assertEquals("PDF", editor.getCurrentFormat());

        editor.newDocument("invalid");
        assertEquals("PDF", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Editor: Should handle rapid document creation")
    public void editor_newDocument_RapidCreation_ShouldWork() {
        DocumentEditor editor = new DocumentEditor();

        for (int i = 0; i < 10; i++) {
            editor.newDocument("pdf");
            editor.newDocument("word");
            editor.newDocument("html");
        }

        assertEquals("HTML", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Integration: Complete workflow with PDF document")
    public void integration_completeWorkflow_PDF() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("pdf");
        editor.saveContent("Important PDF content");
        editor.displayDocument();
        assertEquals("PDF", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Integration: Complete workflow with Word document")
    public void integration_completeWorkflow_Word() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("word");
        editor.saveContent("Important Word content");
        editor.displayDocument();
        assertEquals("Word", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Integration: Complete workflow with HTML document")
    public void integration_completeWorkflow_HTML() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("html");
        editor.saveContent("Web content");
        editor.displayDocument();
        assertEquals("HTML", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Integration: Complete workflow with multiple documents")
    public void integration_multipleDocuments_Workflow() {
        DocumentEditor editor = new DocumentEditor();

        editor.newDocument("pdf");
        editor.saveContent("PDF content");

        editor.newDocument("word");
        editor.saveContent("Word content");

        editor.newDocument("html");
        editor.saveContent("HTML content");

        editor.listOpenDocuments();
        assertEquals("HTML", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Integration: Register and use custom document type")
    public void integration_registerCustomType_AndUse() {
        String customType = "custommd" + System.currentTimeMillis();
        DocumentFactory.registerDocument(customType, MarkdownDocument::new);

        DocumentEditor editor = new DocumentEditor();
        editor.newDocument(customType);
        editor.saveContent("# Custom Heading");
        editor.displayDocument();

        assertEquals("Markdown", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Integration: Factory creates independent document instances")
    public void integration_factory_CreatesIndependentInstances() {
        Document doc1 = DocumentFactory.createDocument("pdf");
        Document doc2 = DocumentFactory.createDocument("pdf");

        doc1.save("Content 1");
        doc2.save("Content 2");

        assertNotSame(doc1, doc2);
        assertEquals("Content 1", doc1.getContent());
        assertEquals("Content 2", doc2.getContent());
    }

    @Test
    @DisplayName("Integration: Editor should work with all document types")
    public void integration_editor_AllDocumentTypes() {
        DocumentEditor editor = new DocumentEditor();
        String[] types = {"pdf", "word", "docx", "html", "htm"};

        for (String type : types) {
            editor.newDocument(type);
            assertNotEquals("None", editor.getCurrentFormat());
        }
    }

    @Test
    @DisplayName("Integration: Save and display cycle multiple times")
    public void integration_saveDisplayCycle_MultipleTimes() {
        DocumentEditor editor = new DocumentEditor();
        editor.newDocument("pdf");

        for (int i = 1; i <= 5; i++) {
            editor.saveContent("Content version " + i);
            editor.displayDocument();
        }
        assertEquals("PDF", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Integration: Mixed operations workflow")
    public void integration_mixedOperations_Workflow() {
        DocumentEditor editor = new DocumentEditor();

        editor.saveContent("Should fail");

        editor.newDocument("html");
        editor.saveContent("Valid content");
        editor.displayDocument();

        editor.newDocument("pdf");
        assertEquals("PDF", editor.getCurrentFormat());

        editor.listOpenDocuments();
    }

    @Test
    @DisplayName("Integration: Document polymorphism test")
    public void integration_documentPolymorphism_ShouldWork() {
        Document[] documents = {
                DocumentFactory.createDocument("pdf"),
                DocumentFactory.createDocument("word"),
                DocumentFactory.createDocument("html")
        };

        String[] expectedFormats = {"PDF", "Word", "HTML"};

        for (int i = 0; i < documents.length; i++) {
            assertEquals(expectedFormats[i], documents[i].getFormatName());
            documents[i].open();
            documents[i].save("Test " + i);
            assertEquals("Test " + i, documents[i].getContent());
            documents[i].display();
        }
    }

    static Stream<Arguments> documentTypeProvider() {
        return Stream.of(
                Arguments.of("pdf", "PDF", PDFDocument.class),
                Arguments.of("word", "Word", WordDocument.class),
                Arguments.of("docx", "Word", WordDocument.class),
                Arguments.of("html", "HTML", HTMLDocument.class),
                Arguments.of("htm", "HTML", HTMLDocument.class)
        );
    }

    @ParameterizedTest
    @MethodSource("documentTypeProvider")
    @DisplayName("Parameterized: Should create correct document type")
    public void parameterized_createDocument_ShouldReturnCorrectType(
            String type, String expectedFormat, Class<?> expectedClass) {
        Document doc = DocumentFactory.createDocument(type);

        assertNotNull(doc);
        assertEquals(expectedFormat, doc.getFormatName());
        assertTrue(expectedClass.isInstance(doc));
    }

    static Stream<Arguments> contentProvider() {
        return Stream.of(
                Arguments.of("Simple text"),
                Arguments.of(""),
                Arguments.of("Special chars: @#$%^&*()"),
                Arguments.of("Unicode: 日本語 🎉"),
                Arguments.of("Multiline\nContent\nHere"),
                Arguments.of("   Whitespace   ")
        );
    }

    @ParameterizedTest
    @MethodSource("contentProvider")
    @DisplayName("Parameterized: All documents should handle various content types")
    public void parameterized_allDocuments_ShouldHandleVariousContent(String content) {
        String[] types = {"pdf", "word", "html"};

        for (String type : types) {
            Document doc = DocumentFactory.createDocument(type);
            doc.save(content);
            assertEquals(content, doc.getContent());
        }
    }

    @Test
    @DisplayName("Edge Case: Very long content in all document types")
    public void edgeCase_veryLongContent_AllTypes() {
        String veryLongContent = "X".repeat(10000);
        String[] types = {"pdf", "word", "html"};

        for (String type : types) {
            Document doc = DocumentFactory.createDocument(type);
            doc.save(veryLongContent);
            assertEquals(veryLongContent, doc.getContent());
        }
    }

    @Test
    @DisplayName("Edge Case: Content with only whitespace")
    public void edgeCase_whitespaceContent_AllTypes() {
        String whitespaceContent = "   \t\n   ";
        String[] types = {"pdf", "word", "html"};

        for (String type : types) {
            Document doc = DocumentFactory.createDocument(type);
            doc.save(whitespaceContent);
            assertEquals(whitespaceContent, doc.getContent());
        }
    }

    @Test
    @DisplayName("Edge Case: Content with newlines")
    public void edgeCase_newlineContent_AllTypes() {
        String newlineContent = "Line1\nLine2\r\nLine3\rLine4";
        String[] types = {"pdf", "word", "html"};

        for (String type : types) {
            Document doc = DocumentFactory.createDocument(type);
            doc.save(newlineContent);
            assertEquals(newlineContent, doc.getContent());
        }
    }

    @Test
    @DisplayName("Edge Case: Rapid save operations")
    public void edgeCase_rapidSaveOperations() {
        Document doc = DocumentFactory.createDocument("pdf");

        for (int i = 0; i < 100; i++) {
            doc.save("Content " + i);
        }

        assertEquals("Content 99", doc.getContent());
    }

    @Test
    @DisplayName("Edge Case: Create many documents")
    public void edgeCase_createManyDocuments() {
        DocumentEditor editor = new DocumentEditor();

        for (int i = 0; i < 50; i++) {
            editor.newDocument("pdf");
            editor.saveContent("Content " + i);
        }

        assertEquals("PDF", editor.getCurrentFormat());
    }

    @Test
    @DisplayName("Edge Case: Alternating document types")
    public void edgeCase_alternatingDocumentTypes() {
        DocumentEditor editor = new DocumentEditor();
        String[] types = {"pdf", "word", "html"};

        for (int i = 0; i < 15; i++) {
            editor.newDocument(types[i % 3]);
        }

        editor.listOpenDocuments();
    }

    @Test
    @DisplayName("Edge Case: Null-like content handling")
    public void edgeCase_emptyStringContent() {
        Document doc = DocumentFactory.createDocument("pdf");
        doc.save("");
        assertEquals("", doc.getContent());
        doc.display();
    }

    @Test
    @DisplayName("Edge Case: Document interface contract")
    public void edgeCase_documentInterfaceContract() {
        String[] types = {"pdf", "word", "html"};

        for (String type : types) {
            Document doc = DocumentFactory.createDocument(type);

            assertNotNull(doc.getFormatName());
            assertFalse(doc.getFormatName().isEmpty());

            assertEquals("", doc.getContent());

            doc.open();
            doc.save("test");
            assertEquals("test", doc.getContent());
            doc.display();
        }
    }

    @Test
    @DisplayName("Edge Case: Factory registry persistence")
    public void edgeCase_factoryRegistryPersistence() {
        String customType = "persistent" + System.currentTimeMillis();

        assertFalse(DocumentFactory.isTypeSupported(customType));

        DocumentFactory.registerDocument(customType, PDFDocument::new);

        assertTrue(DocumentFactory.isTypeSupported(customType));

        Document doc = DocumentFactory.createDocument(customType);
        assertNotNull(doc);
    }
}