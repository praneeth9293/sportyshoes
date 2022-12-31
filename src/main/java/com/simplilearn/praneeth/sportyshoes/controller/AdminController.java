package com.simplilearn.praneeth.sportyshoes.controller;

import com.simplilearn.praneeth.sportyshoes.model.Purchase;
import com.simplilearn.praneeth.sportyshoes.service.PurchaseService;
import com.simplilearn.praneeth.sportyshoes.configuration.UserAuthentication;
import com.simplilearn.praneeth.sportyshoes.model.Shoe;
import com.simplilearn.praneeth.sportyshoes.model.User;
import com.simplilearn.praneeth.sportyshoes.service.ShoeService;
import com.simplilearn.praneeth.sportyshoes.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ShoeService shoeService;
    private final PurchaseService purchaseService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserService userService, ShoeService shoeService, PurchaseService purchaseService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.shoeService = shoeService;
        this.purchaseService = purchaseService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/manage/users")
    public String getUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/manage/shoes")
    public String getShoes(Model model) {
        List<Shoe> shoes = shoeService.findAll();
        model.addAttribute("shoes", shoes);
        return "shoes";
    }

    @GetMapping("/manage/purchases")
    public String getReports(Model model) {
        List<Purchase> purchases = purchaseService.findAll();
        model.addAttribute("purchases", purchases);
        return "purchases";
    }

    @GetMapping("/manage/password")
    public String getPasswordManagement(Model model) {
        model.addAttribute("user", ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUser());
        return "changePassword";
    }

    @PostMapping("/manage/shoes/change")
    public String updateShoes(List<Shoe> shoes) {
        shoeService.saveAll(shoes);
        return "redirect:/admin/manage/shoes";
    }

    @PostMapping("/manage/password/change")
    public String updatePassword(User user) {
        UserAuthentication authentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        User currentUser = authentication.getUser();
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(currentUser);
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }
}
