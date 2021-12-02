# PDF Creator

For this challenge I have used the Maven build tool and iText for Java.

The input for the PDF creator is given in the input.txt file, 
and the output is given in the output.pdf file.

I have assumed that lines only begin with '.' if it is a command.
I have also assumed that the large font size is 22.

The main method creates a PdfCreator object, repeats the input 10 times to 
render 3 pages of content and calls the finish method on the PdfCreator.

When the program is run, it may show the message

> SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder"

however, this is not an issue for producing the PDF.