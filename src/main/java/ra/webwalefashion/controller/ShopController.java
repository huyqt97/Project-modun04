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
import ra.webwalefashion.service.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ProductService productService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartService cartService;
    @GetMapping
    public String shop(Model model){
        model.addAttribute("productList", productService.findAllViewAdmin());
        model.addAttribute("sizes",sizeService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("brands",brandService.findAll());
        return "shop";
    }
    @GetMapping("/category/{id}")
    public String searchCategory(@PathVariable("id") int id, Model model){
        model.addAttribute("productList",productService.findAllViewUserCategory(id));
        model.addAttribute("sizes",sizeService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("brands",brandService.findAll());
        return "/shop";
    }
    @GetMapping("/brand/{id}")
    public String searchBrand(@PathVariable("id") int id, Model model){
        model.addAttribute("productList",productService.findAllViewUserBrand(id));
        model.addAttribute("sizes",sizeService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("brands",brandService.findAll());
        return "/shop";
    }
    @GetMapping("/productSize/{id}")
    public String searchSize(@PathVariable("id") int id, Model model){
        model.addAttribute("productList",productService.findAllViewUserSize(id));
        model.addAttribute("sizes",sizeService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("brands",brandService.findAll());
        return "/shop";
    }
    @GetMapping("/productPrice")
    public String searchProductPrice(@RequestParam("min") int min, @RequestParam("max") int max, Model model){
        model.addAttribute("productList",productService.findAllViewUserPrice(min,max));
        model.addAttribute("sizes",sizeService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("brands",brandService.findAll());
        return "shop";
    }
    @GetMapping("/add-cart/{id}")
    public String addCart(@PathVariable("id") Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        UserLoginRes userLoginRes = (UserLoginRes) session.getAttribute("UserLoginSession");
        cartService.addToCart(id,userLoginRes.getUserId());
        redirectAttributes.addFlashAttribute("addToCart", "Đã thêm sản phẩm vào giỏ hàng!");
        return "redirect:/shop";
    }
    @GetMapping("/searchProduct")
    public String searchProduct(@RequestParam("search") String search, Model model){
        model.addAttribute("sizes",sizeService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("brands",brandService.findAll());
        model.addAttribute("productList",productService.searchByName(search));
        return "shop";
    }
}
