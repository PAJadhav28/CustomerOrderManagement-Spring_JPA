package com.example.controller;

import com.example.model.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger LOGGER = Logger.getLogger(CustomerController.class.getName());
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("usaCustomerOrders", customerService.getUSACustomerOrders());
        return "customer_list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, HttpSession session) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        session.removeAttribute("customer");
        LOGGER.info("Showing add form with customer: " + customer);
        return "customer_form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            model.addAttribute("error", "Customer not found.");
            return "error";
        }
        model.addAttribute("customer", customer);
        LOGGER.info("Showing edit form for customer ID: " + id);
        return "customer_form";
    }

    @PostMapping
    public String saveCustomer(@ModelAttribute Customer customer, Model model) {

    	System.out.println("in Ccontroller: "+customer);
        LOGGER.info("Received customer: ID=" + customer.getCustomerId() +
                    ", FirstName=" + customer.getFirstName() +
                    ", LastName=" + customer.getLastName() +
                    ", Email=" + customer.getEmail() +
                    ", Country=" + customer.getCountry());
        try {
            customerService.saveCustomer(customer);
            return "redirect:/customers";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        } catch (Exception e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("id") int id, Model model) {
        try {
            customerService.deleteCustomer(id);
            return "redirect:/customers";
        } catch (Exception e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/summary")
    public String getCustomerSummary(@RequestParam("id") int id, Model model) {
        try {
            Object[] summary = customerService.getCustomerOrderSummary(id);
            model.addAttribute("fullName", summary[0]);
            model.addAttribute("orderCount", summary[1]);
            model.addAttribute("totalSpent", summary[2]);
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("usaCustomerOrders", customerService.getUSACustomerOrders());
            return "customer_list";
        } catch (Exception e) {
            model.addAttribute("error", "Database error: " + e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/debug")
    public String showDebugSessionPage() {
        return "debug_session"; // This maps to /WEB-INF/views/debug_session.jsp
    }

}