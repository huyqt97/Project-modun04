package ra.webwalefashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.webwalefashion.DTO.request.ProductEditReq;
import ra.webwalefashion.DTO.response.ProductEditRes;
import ra.webwalefashion.DTO.response.ProductUpdateRes;
import ra.webwalefashion.service.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@PropertySource("classpath:upload.properties")
public class AdminController {
    @Value("${upload-path}")
    String pathUpload;
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping()
    public String product(Model model) {
        model.addAttribute("productList", productService.findAllViewAdmin());
        model.addAttribute("sizes", sizeService.findAll());
        return "admin/productManager";
    }
    @GetMapping("/orderManager")
    public String order(Model model) {
        model.addAttribute("orders", orderService.findAllPending());
        return "/admin/orderManager";
    }

    @GetMapping("/orderManager/PendingOrder")
    public String pendingOrder(Model model) {
        model.addAttribute("orders", orderService.findAllPending());
        return "/admin/orderManager";
    }

    @GetMapping("/orderManager/History")
    public String history(Model model) {
        model.addAttribute("orders", orderService.findAllHistory());
        return "/admin/orderManager";
    }

    @GetMapping("/orderManager/detail/{id}")
    public String orderDetail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("orderDetails", orderDetailService.findAllToOrder(id));
        return "/admin/detailOrder";
    }
    @GetMapping("/orderManager/confirm/{id}")
    public String orderConfirm(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        orderService.upStatus(id);
        redirectAttributes.addFlashAttribute("bus","Thao tác thành công!");
        return "redirect:/admin/orderManager";
    }

    @GetMapping("/userManager")
    public String user(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/admin/userManager";
    }

    @GetMapping("/lockUser/{id}")
    public String lockUser(@PathVariable("id") int id) {
        userService.blockUser(id);
        return "redirect:/admin/userManager";
    }

    @GetMapping("/unLockUser/{id}")
    public String unLockUser(@PathVariable("id") int id) {
        userService.unlockUser(id);
        return "redirect:/admin/userManager";
    }

    @GetMapping("/createProduct")
    public String createProduct(Model model) {
        model.addAttribute("products", new ProductUpdateRes());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("sizes", sizeService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "/admin/createProduct";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("products") ProductUpdateRes productUpdateRes) {
        productService.save(productUpdateRes);
        return "redirect:/admin";
    }

    @GetMapping("/searchUser")
    public String search(@RequestParam("search") String text, Model model) {
        model.addAttribute("users", userService.findByFullNameOrUserName(text));
        return "/admin/userManager";
    }

    @GetMapping("/editProduct/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("sizes", sizeService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "/admin/editProduct";
    }

    @GetMapping("/searchProduct")
    public String searchProduct(@RequestParam("search") String text, Model model) {
        model.addAttribute("productList", productService.findByNameProduct(text));
        return "admin/productManager";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute("product") ProductEditReq productEditReq) {
        productService.update(productEditReq);
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("UserLoginSession");
        return "redirect:/home";
    }
}
