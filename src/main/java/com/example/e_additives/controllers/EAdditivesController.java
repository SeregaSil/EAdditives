package com.example.e_additives.controllers;

import com.example.e_additives.services.EAdditivesService;
import com.example.e_additives.services.EmailSenderService;
import com.example.e_additives.view.ExportDataToPdf;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class EAdditivesController {

    @Autowired
    private EAdditivesService eAdditivesService;

    @Autowired
    private EmailSenderService senderService;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("additives", eAdditivesService.getAllAdditives());
        return "home/index";
    }

    @PostMapping("/decoding")
    public String decoding(@RequestParam(value = "index", required = false) List<String> indexes, Model model){
        if (indexes == null){
            return "home/error";
        }
        model.addAttribute("selectedAdditives", eAdditivesService.getSelectedAdditivesByIndex(indexes));
        return "home/decoding";
    }

    @PostMapping("/sent")
    public String sendSimpleEmail(@RequestParam(value = "message") String message) {
        senderService.sendEmail(message);
        return "home/success";
    }

    @GetMapping("/pdf")
    public void exportToPDF(@RequestParam(value = "index") List<String> indexes, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "inline; filename=EAdditives" + ".pdf";
        response.setHeader(headerKey, headerValue);

        ExportDataToPdf exportDataToPdf = new ExportDataToPdf(eAdditivesService.getSelectedAdditivesByIndex(indexes));
        exportDataToPdf.export(response);
    }

}
