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

    private Font getTableTextFont(int fontSize){
        Font font = FontFactory.getFont("timesnewromanpsmt.ttf", "cp1251", BaseFont.EMBEDDED);
        font.setColor(BaseColor.BLACK);
        font.setSize(fontSize);
        return font;
    }

    private PdfPCell getTableCell(BaseColor color){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(color);
        cell.setPaddingBottom(6);
        return cell;
    }

    private void writeTableHeader(PdfPTable table) {

        PdfPCell cell = getTableCell(new BaseColor(130,255,222,255));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell.setPhrase(new Phrase("Группа", getTableTextFont(16)));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Индекс", getTableTextFont(16)));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Имя", getTableTextFont(16)));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Information", getTableTextFont(16)));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {

        PdfPCell cell = getTableCell(new BaseColor(211,255,233,255));
        cell.setPaddingLeft(4);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        for (EAdditive eAdditive : eAdditiveList) {
            cell.setPhrase(new Phrase(eAdditive.getType(), getTableTextFont(12)));
            table.addCell(cell);

            cell.setPhrase(new Phrase(eAdditive.getIndex(), getTableTextFont(12)));
            table.addCell(cell);

            cell.setPhrase(new Phrase(eAdditive.getName(), getTableTextFont(12)));
            table.addCell(cell);

            cell.setPhrase(new Phrase(eAdditive.getInformation(), getTableTextFont(12)));
            table.addCell(cell);
        }
    }

    private PdfPTable createTable() throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.55f, 1.0f, 1.5f, 5.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);
        return table;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph p = new Paragraph("List of EAdditives", getTableTextFont(18));
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);
        document.add(createTable());

        document.close();
        response.getOutputStream().close();
    }
}
