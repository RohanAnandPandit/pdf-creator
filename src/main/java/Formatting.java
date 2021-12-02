import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.IOException;

/**
 * Keeps track of the font and formatting for the PDF document
 */
public class Formatting {
    private final static int TAB_SIZE = 4;
    private final static int NORMAL_FONT_SIZE = 11, LARGE_FONT_SIZE = 22;
    private final Fonts fonts;
    private boolean bold, italic;
    private int fontSize;
    private boolean fill;
    private int indent;

    public Formatting() throws IOException {
        fonts = new Fonts();
        setNormalFontSize();
    }

    public void setLargeFontSize() {
        fontSize = LARGE_FONT_SIZE;
    }

    public void setNormalFontSize() {
        fontSize = NORMAL_FONT_SIZE;
    }

    public void setBold() {
        bold = true;
    }

    public void setItalic() {
        italic = true;
    }

    public void setRegular() {
        bold = italic = false;
    }

    public void updateIndent(int units) {
        // Increments current indentation by given units
        indent += units;
    }

    public PdfFont getFont() {
        // Returns appropriate font based on bold and italic
        if (bold && italic) {
            return fonts.BOLD_ITALIC;
        } else if (bold) {
            return fonts.BOLD;
        } else if (italic) {
            return fonts.ITALIC;
        }

        return fonts.REGULAR;
    }

    public int getIndent() {
        return indent;
    }

    public void setFill() {
        fill = true;
    }
    
    public void setNoFill() {
        fill = false;
    }

    public void format(Text text) {
        // Applies font properties to the text
        text.setFont(getFont());
        text.setFontSize(fontSize);
    }

    public void format(Paragraph paragraph) {
        paragraph.setMarginLeft(indentWidth());
    }

    public String format(String line) {
        if (!fill) {
            // only indent line if not in fill region
            line = getIndentation() + line;
        }
        return line;
    }
    
    public String getIndentation() {
        // Uses spaces to indent string
        return " ".repeat(TAB_SIZE * indent);
    }
    
    public float getIndentationUnit() {
        // Calculates the width of "WWWW" for a single indentation
        return TAB_SIZE * getFont().getWidth('W', NORMAL_FONT_SIZE);
    }
    
    public float indentWidth() {
        // Calculates width for total indentation
        return getIndent() * getIndentationUnit();
    }
}
