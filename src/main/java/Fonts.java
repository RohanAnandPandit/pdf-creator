import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.IOException;

/**
 * Fonts for regular, bold and italic text
 * */
public class Fonts {
    public final PdfFont REGULAR 
            = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
    public final PdfFont BOLD 
            = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
    public final PdfFont ITALIC 
            = PdfFontFactory.createFont(StandardFonts.TIMES_ITALIC);
    public final PdfFont BOLD_ITALIC
            = PdfFontFactory.createFont(StandardFonts.TIMES_BOLDITALIC);

    public Fonts() throws IOException {

    }
}
