package br.com.dcm.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Routes {

    @RequestMapping({"/"})
    public String index(){
        return "/index.html";
    }
}
