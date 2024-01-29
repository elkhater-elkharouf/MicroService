package tn.esprit.servicereservation.service;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import tn.esprit.servicereservation.entity.Reservation;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfGenerator {

    public static ByteArrayInputStream generate(List<Reservation> reservationList) {

        Document document= new Document();
        ByteArrayOutputStream out= new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document,out);
            document.open();

            Font font= FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph paragraph= new Paragraph("My Reservations", font);

            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);

            PdfPTable table=new PdfPTable(3);

            Stream.of("ServiceTitle","ServiceDescription","DateReservation").forEach(
                    headerTitle->{
                    PdfPCell head= new PdfPCell();
                    Font headFont=  FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                    head.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    head.setHorizontalAlignment(Element.ALIGN_CENTER);
                    head.setBorderWidth(1);
                    head.setPhrase(new Phrase(headerTitle,headFont));
                    table.addCell(head);
            });

            for (Reservation r:reservationList){
                PdfPCell ServiceTitleCell= new PdfPCell(new Phrase(r.getService().getTitre()));
                ServiceTitleCell.setPadding(1);
                ServiceTitleCell.setVerticalAlignment(Element.ALIGN_CENTER);
                ServiceTitleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(ServiceTitleCell);

                PdfPCell ServiceDescriptionCell= new PdfPCell(new Phrase(r.getService().getDescription()));
                ServiceDescriptionCell.setPadding(1);
                ServiceDescriptionCell.setVerticalAlignment(Element.ALIGN_CENTER);
                ServiceDescriptionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(ServiceDescriptionCell);

                PdfPCell DateReservationCell= new PdfPCell(new Phrase(r.getDateReservation().toString()));
                DateReservationCell.setPadding(1);
                DateReservationCell.setVerticalAlignment(Element.ALIGN_CENTER);
                DateReservationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(DateReservationCell);

            }

            document.add(table);
            document.close();
        }catch (DocumentException e){
            e.printStackTrace();
        }


        return new ByteArrayInputStream(out.toByteArray());
    }

}
