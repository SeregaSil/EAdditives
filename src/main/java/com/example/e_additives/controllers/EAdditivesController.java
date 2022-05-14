package com.example.e_additives.controllers;

import com.example.e_additives.services.EAdditivesService;
import com.example.e_additives.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EAdditivesController {

    @Autowired
    private EAdditivesService eAdditivesService;

    @Autowired
    private EmailSenderService senderService;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("additives", eAdditivesService.findAll());
        return "home/index";
    }

    @PostMapping("/decoding")
    public String decoding(@RequestParam(value = "index", required = false) String[] indexes, Model model){
        if (indexes == null){
            return "home/error";
        }
        model.addAttribute("selectedAdditives", eAdditivesService.findSelectedAdditives(indexes));
        return "home/decoding";
    }

    @PostMapping("/sent")
    public String sendSimpleEmail(@RequestParam(value = "message") String message) {
        senderService.sendEmail("lsomoslagaga@gmail.com","E-Additives",message);
        return "home/success";
    }

}
