package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.CartElement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@Controller

public class CartController {


    // TODO: 03.03.2021 пока фейковые данные, для разработки ui 
    @ModelAttribute("cartElementList")
    public List<CartElement> cartElementList() {

        CartElement cartElement = new CartElement();
        cartElement.setAuthor("Борис Васильевич Бедный");
        cartElement.setTitle("Жизнь");
        cartElement.setPrice(154);

        CartElement cartElement1 = new CartElement();
        cartElement1.setAuthor("Борис Васильевич Маяковский");
        cartElement1.setTitle("Трубы");
        cartElement1.setPrice(100);
        cartElement1.setPriceOld(150);
        cartElement1.setSale(30);

        CartElement cartElement2 = new CartElement();
        cartElement2.setAuthor("Борис Васильевич Маяковский");
        cartElement2.setTitle("Зубы");
        cartElement2.setPrice(123);
        cartElement2.setPriceOld(150);
        cartElement2.setSale(29);

        return Arrays.asList(cartElement, cartElement1, cartElement2);
    }


    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @GetMapping("/postponed")
    public String postponedPage(){
        return "postponed";
    }
}
