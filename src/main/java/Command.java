import java.io.IOException;
import java.util.Scanner;

public class Command {
    
    public static void execute(String command, Formatting formatting,
                               PdfCreator pdfCreator) {
        Scanner scanner = new Scanner(command);
        switch (scanner.next()) {
            case ".large" -> formatting.setLargeFontSize();
            case ".normal" -> formatting.setNormalFontSize();
            case ".bold" -> formatting.setBold();
            case ".italics" -> formatting.setItalic();
            case ".regular" -> formatting.setRegular();
            case ".indent" -> {
                int units = scanner.nextInt();
                pdfCreator.addCurrentParagraph();
                formatting.updateIndent(units);
            }
            case ".fill" -> {
                formatting.setFill();
                pdfCreator.fillParagraphIndentation();
            }
            case ".nofill" -> {
                formatting.setNoFill();
                pdfCreator.addCurrentParagraph();
            }
            case ".paragraph" -> pdfCreator.addCurrentParagraph();
        }
    }
 
    public static boolean isCommand(String line) {
        // checks if first character is '.'
        return line.length() > 0 && line.charAt(0) == '.';
    }
}
