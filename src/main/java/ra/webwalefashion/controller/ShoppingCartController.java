package ra.webwalefashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.webwalefashion.DTO.request.OrderReq;
import ra.webwalefashion.DTO.response.CartItemViewUserRes;
import ra.webwalefashion.DTO.response.UserLoginRes;
import ra.webwalefashion.model.entity.Cart;
import ra.webwalefashion.service.CartItemService;
import ra.webwalefashion.service.CartService;
import ra.webwalefashion.service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartService cartService;
    @Autowired
    private HttpSession session;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String shoppingCart(HttpSession session, Model model) {
        UserLoginRes userLoginRes = (UserLoginRes) session.getAttribute("UserLoginSession");
        List<CartItemViewUserRes> ca = cartItemService.findAllToUser(userLoginRes.getUserId());
        Cart cart = cartService.findByUser(userLoginRes.getUserId());
        model.addAttribute("cartC", cart);
        model.addAttribute("ca", ca);
        return "shopping-cart";
    }

    @GetMapping("/sell/{id}")
    public String sellCartItem(@PathVariable("id") Integer id, HttpSession httpSession) {
        UserLoginRes userLoginRes = (UserLoginRes) httpSession.getAttribute("UserLoginSession");
        cartItemService.sellSearchToProductAndUser(id);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/bus/{id}")
    public String busCartItem(@PathVariable("id") Integer id, HttpSession httpSession) {
        UserLoginRes userLoginRes = (UserLoginRes) httpSession.getAttribute("UserLoginSession");
        cartItemService.busSearchToProductAndUser(id);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/delete-cart/{id}")
    public String deleteCart(@PathVariable("id") Integer id) {
        cartItemService.delete(id);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/pay/{id}")
    public String payCart(@PathVariable("id") Integer id, Model model, HttpSession session,RedirectAttributes redirectAttributes) {
        UserLoginRes userLoginRes = (UserLoginRes) session.getAttribute("UserLoginSession");
        List<CartItemViewUserRes> cartItems = cartItemService.findAllToUser(userLoginRes.getUserId());
        if (!cartItems.isEmpty()) {
            model.addAttribute("orderReq", new OrderReq());
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("cart", cartService.findById(id));
            return "checkout";
        }
        redirectAttributes.addFlashAttribute("qwe","giỏ hàng trống!");
        return "redirect:/shopping-cart";
    }

    @PostMapping("pay")
    public String postOrder(@RequestParam("cartId") Integer cartId, @ModelAttribute("orderReq") OrderReq orderReq, @RequestParam("add1") String add1, @RequestParam("add2") String add2, @RequestParam("add3") String add3, @RequestParam("add4") String add4, RedirectAttributes redirectAttributes) {
        String address = add1 + ", " + add2 + ", " + add3 + ", " + add4;
        UserLoginRes userLoginRes = (UserLoginRes) session.getAttribute("UserLoginSession");
        orderReq.setAddress(address);
        orderReq.setUser_id(userLoginRes.getUserId());
        orderService.create(orderReq, cartId);
        redirectAttributes.addFlashAttribute("payCart", "Thanh toán thành công!");
        return "redirect:/shopping-cart";
    }
}
