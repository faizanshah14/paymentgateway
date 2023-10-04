package com.example.middlewareapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/view")
    public String serveHtmlPage() {
        return "index";
    }
}
