package ra.webwalefashion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog-details")
public class BlogDetailsController {
    @GetMapping
    public String logDetails(){
        return "blog-details";
    }
}
