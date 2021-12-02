import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

/**
 * Class to create render PDF from input text files
 */
public class PdfCreator {
    private final Document document;
    private Paragraph currentParagraph;
    private Formatting formatting;

    public PdfCreator(String output) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(output));
        document = new Document(pdfDoc);
        resetCurrentParagraph();
        resetFormatting();
    }

    private void resetFormatting() throws IOException {
        formatting = new Formatting();
    }

    private void resetCurrentParagraph() {
        currentParagraph = new Paragraph();
    }

    public void fromFile(String filename) throws IOException {
        // Reads the input from the file line-by-line
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            
            for (String line; (line = br.readLine()) != null; ) {
                // Remove trailing newline character from line
                line = line.replace("\n", "");

                if (Command.isCommand(line)) {
                    // If the line is a command it is used to update the state
                    // of the formatting
                    Command.execute(line, formatting, this);
                } else {
                    line = formatting.format(line);
                    addTextToParagraph(line);
                }
            }
        }

        addCurrentParagraph();

        // The formatting defaults to original after the input file is over
        resetFormatting();

        addNewline();
    }

    private void addTextToParagraph(String line) {
        // Applies the current formatting to the text and adds it to the 
        // current paragraph
        Text text = new Text(line);
        formatting.format(text);
        currentParagraph.add(text);
    }

    public void addNewline() {
        document.add(new Paragraph(new Text("\n")));
    }

    public void addCurrentParagraph() {
        // Formats the current paragraph and adds it to the document
        formatting.format(currentParagraph);
        document.add(currentParagraph);
        resetCurrentParagraph();
    }
    
    public void fillParagraphIndentation() {
        // so that the last character of a line is at the end of the margin
        currentParagraph.setTextAlignment(TextAlignment.JUSTIFIED);
    }

    public void finish() {
        document.close();
    }
}