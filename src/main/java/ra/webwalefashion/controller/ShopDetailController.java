package ra.webwalefashion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop-details")
public class ShopDetailController {
    @GetMapping
    public String shopDetails(){
        return "shop-details";
    }
}
