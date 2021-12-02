public class Main {
    public static void main(String[] args) throws Exception {
        String inputFile = "./input.txt";
        String outputFile = "./output.pdf";
        
        PdfCreator pdfCreator = new PdfCreator(outputFile);

        for (int i = 0; i < 10; i++)
            pdfCreator.fromFile(inputFile);

        pdfCreator.finish();
    }
}
