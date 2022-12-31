package com.simplilearn.praneeth.sportyshoes.controller;

import com.simplilearn.praneeth.sportyshoes.configuration.UserAuthentication;
import com.simplilearn.praneeth.sportyshoes.model.Shoe;
import com.simplilearn.praneeth.sportyshoes.service.PurchaseService;
import com.simplilearn.praneeth.sportyshoes.service.ShoeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ShoeController {

    private final ShoeService shoeService;
    private final PurchaseService purchaseService;

    public ShoeController(ShoeService shoeService, PurchaseService purchaseService) {
        this.shoeService = shoeService;
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public String getShoes(@RequestParam(required = false) String category, Model model) {
        List<Shoe> shoes;
        if (category != null) {
            shoes = shoeService.findByCategory(category);
        } else {
            shoes = shoeService.findAll();
        }
        model.addAttribute("shoes", shoes);
        UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userAuthentication.getUser());
        return "index";
    }

    @PostMapping("/{id}/buy")
    public String buyShoe(@PathVariable("id") long id) {
        Optional<Shoe> shoe = shoeService.findById(id);
        if (!shoe.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        purchaseService.purchase(shoe.get());
        return "redirect:/";
    }
}
