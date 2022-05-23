package com.example.e_additives.view;

import com.example.e_additives.entity.EAdditive;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExportDataToPdf {
    private final List<EAdditive> eAdditiveList;

    public ExportDataToPdf(List<EAdditive> eAdditiveList) {
        this.eAdditiveList = eAdditiveList;
    }

    private Font getFont(){
        return FontFactory.getFont("timesnewromanpsmt.ttf", "cp1251", BaseFont.EMBEDDED);
    }


    private void writeTableHeader(PdfPTable table) {

        BaseColor color = new BaseColor(130,255,222,255);
        Font font = getFont();
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(color);
        font.setColor(BaseColor.BLACK);
        font.setSize(16);
        cell.setPaddingBottom(6);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell.setPhrase(new Phrase("Группа", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Индекс", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Имя", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Information", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        BaseColor color = new BaseColor(211,255,233,255);
        Font font = getFont();
        PdfPCell cell = new PdfPCell();
        font.setSize(12);
        cell.setBackgroundColor(color);
        cell.setPaddingBottom(6);
        cell.setPaddingLeft(4);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        for (EAdditive eAdditive : eAdditiveList) {
            cell.setPhrase(new Phrase(eAdditive.getType(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(eAdditive.getIndex(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(eAdditive.getName(), font));
            table.addCell(cell);

            cell.setPhrase(new Phrase(eAdditive.getInformation(), font));
            table.addCell(cell);
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());


        document.open();
        Font font = getFont();
        font.setSize(18);
        font.setColor(BaseColor.BLACK);

        Paragraph p = new Paragraph("List of EAdditives", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.55f, 1.0f, 1.5f, 5.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
        response.getOutputStream().close();
    }
}
