package pdfend;
//import pdfbox
//import java.io
//import java.awt
//import java.awt.event
//import javax.swing
//import java.sql
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import backend.database;
import java.io.IOException;
import java.sql.Connection;
import backend.Question;
import java.util.List;



public class SaveToPdf {
    public void exportToPDF(List<Question> Questions) throws IOException{
        //create a new document
        PDDocument document = new PDDocument();
        //add a page to the document
        Connection conn = database.connect();
        
        PDPage pdp=new PDPage();
        float leading = 14.5f;  // line height
        float position = 700;   // initial y-position

// Create the content stream before the loop
PDPageContentStream contentStream = new PDPageContentStream(document, pdp, PDPageContentStream.AppendMode.APPEND, true);
contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 12f);

for (int j = 0; j < Questions.size(); j++){
    Question question= Questions.get(j);
    // Move to the next line
    // Move to the next line
    position -= leading;
    contentStream.beginText();
    contentStream.newLineAtOffset(100, position);
    contentStream.showText(question.QuestionBody);
    contentStream.endText();

    // Option A
    position -= leading;
    contentStream.beginText();
    contentStream.newLineAtOffset(100, position);
    contentStream.showText("A. "+question.OptionA);
    contentStream.endText();

    // Option B
    position -= leading;
    contentStream.beginText();
    contentStream.newLineAtOffset(100, position);
    contentStream.showText("B. "+question.OptionB);
    contentStream.endText();

    // Option C
    position -= leading;
    contentStream.beginText();
    contentStream.newLineAtOffset(100, position);
    contentStream.showText("C. "+question.OptionC);
    contentStream.endText();

    // Option D
    position -= leading;
    contentStream.beginText();
    contentStream.newLineAtOffset(100, position);
    contentStream.showText("D. "+question.OptionD);
    contentStream.endText();

    //add a blanck line
    position -= leading;
    contentStream.beginText();
    contentStream.newLineAtOffset(100, position);
    contentStream.showText("");
    contentStream.endText();
    
}

// Close the content stream after the loop
contentStream.close();

// Save the document
document.addPage(pdp);
document.save("sample.pdf");

// Close the document
document.close();
    }
}
