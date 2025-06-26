package com.example.controller;

import com.example.model.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("auditLog", orderService.getAuditLog());
        return "order_list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order());
        return "order_form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
    	System.out.println("edit id: "+id);
        Order order = orderService.getOrderById(id);
        if (order == null) {
            model.addAttribute("error", "Order not found.");
            return "error";
        }
        model.addAttribute("order", order);
        return "order_form";
    }

    @PostMapping
    public String saveOrder(@ModelAttribute Order order, Model model) {
    	System.out.println("In saveOrder: "+order);
        try {
            orderService.saveOrder(order);
            return "redirect:/orders";
        } catch (Exception e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/delete")
    public String deleteOrder(@RequestParam("id") int id, Model model) {
    	System.out.println("delete id: "+id);
        try {
            orderService.deleteOrder(id);
            return "redirect:/orders";
        } catch (Exception e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
            return "error";
        }
    }
}