package ra.webwalefashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.webwalefashion.DTO.response.UserLoginRes;
import ra.webwalefashion.service.CartService;
import ra.webwalefashion.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeShopController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @GetMapping({"/home", "/"})
    public String homeShow(Model model) {
        model.addAttribute("products", productService.findAllViewAdmin());
        return "index";
    }

    @GetMapping("/shop-details/{id}")
    public String shopDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findByIdViewShop(id));
        model.addAttribute("products", productService.findAllViewAdmin());
        return "shop-details";
    }

    @GetMapping("/add-cart/{id}")
    public String addCart(@PathVariable("id") Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        UserLoginRes userLoginRes = (UserLoginRes) session.getAttribute("UserLoginSession");
        if (productService.findById(id).getStock() >= 1) {
            cartService.addToCart(id, userLoginRes.getUserId());
            redirectAttributes.addFlashAttribute("addToCart", "Đã thêm sản phẩm vào giỏ hàng!");
            return "redirect:/home";
        }else {
            redirectAttributes.addFlashAttribute("tb","Sản phẩm đang hết hàng!");
            return "redirect:/home";
        }
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("UserLoginSession");
        return "redirect:/home";
    }
}
