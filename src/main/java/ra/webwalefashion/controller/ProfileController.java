package ra.webwalefashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.webwalefashion.DTO.response.UserLoginRes;
import ra.webwalefashion.service.OrderService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpSession session;
    @GetMapping("/theOrder")
    public String theOrder(Model model){
        UserLoginRes userLoginRes = (UserLoginRes) session.getAttribute("UserLoginSession");
        model.addAttribute("orders",orderService.allToUserLogin(userLoginRes.getUserId()));
        return "theOrder";
    }
    @GetMapping("/purchaseHistory")
    public String historyOrder(Model model){
        UserLoginRes userLoginRes = (UserLoginRes) session.getAttribute("UserLoginSession");
        model.addAttribute("orders",orderService.allToUserLoginHistory(userLoginRes.getUserId()));
        return "historyOrder";
    }
    @GetMapping("/theOrder/cancel/{id}")
    public String cancelOrder(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        orderService.delete(id);
        redirectAttributes.addFlashAttribute("asd","Thao tác thành công!");
        return "redirect:/profile/theOrder";
    }
}
