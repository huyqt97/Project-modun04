package ra.webwalefashion.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.webwalefashion.DTO.request.UserLoginReq;
import ra.webwalefashion.DTO.request.UserRegisterReq;
import ra.webwalefashion.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;
    @GetMapping()
    public String register(Model model) {
        model.addAttribute("userRegister", new UserRegisterReq());
        return "register";
    }

    @PostMapping
    public String Register(@Valid @ModelAttribute("userRegister") UserRegisterReq userRegisterReq, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            if (userService.findByUserName(userRegisterReq.getUserName())) {
                model.addAttribute("userLogin",new UserLoginReq());
                userService.saveRegister(userRegisterReq);
                redirectAttributes.addFlashAttribute("success","Tạo mới thành công!");
                return "redirect:/login-account";
            } else {
                redirectAttributes.addFlashAttribute("er","Tên tài khoản đã tồn tại!");
                return "redirect:/register";
            }
        }
                return "register";
    }
}
