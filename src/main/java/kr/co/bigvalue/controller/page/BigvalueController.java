package kr.co.bigvalue.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BigvalueController {

    @RequestMapping(value = {
            "/",
            "/dataMap",
            "/solution",
            "/about",
            "/resources",
            "/search1",
            "/search2",
            "/search3",


            "/m"
    })
    public String index(Model model) {
        return "index.html";
    }

    @RequestMapping(value = {
            "/solution/aiDeveloper",
            "/solution/vadvisor",
            "/solution/villasise",
            "/about/careers",
            "/about/leadShip",
            "/about/contact",
            "/resources/{id}",


            "/m/mainMenu",
            "/m/dataMap",
            "/m/solution",
            "/m/solution/aiDeveloper",
            "/m/solution/vadvisor",
            "/m/solution/villasise",
            "/m/about",
            "/m/about/careers",
            "/m/about/leadShip",
            "/m/about/contact",
            "/m/resources",
            "/m/resources/{id}",
            "/m/search1",
            "/m/search2",
            "/m/search3"
    })
    public String index2(Model model) {
        return "forward:/index.html";
    }
}
