package com.codigo.cowork_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/info")
public class InfoController {

    @GetMapping
    public Map<String, String> obtenerInformacion() {
        Map<String, String> info = new HashMap<>();

        info.put("nombreApp", "cowork-api");
        info.put("version", "1.0.0");
        info.put("autor", "Alex Piero Samaniego Inga");

        return info;
    }
}
