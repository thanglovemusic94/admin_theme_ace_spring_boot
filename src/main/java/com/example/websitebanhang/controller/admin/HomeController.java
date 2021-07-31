package com.example.websitebanhang.controller.admin;

import com.example.websitebanhang.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"", "/index.html"})
    public String index() {
        return "admin/layout";
    }

    @GetMapping("/product")
    public String index(Model model){
        return "/admin/product/index";
    }


    @GetMapping("/blank.html")
    public String blank() {
        return "blank";
    }

    @GetMapping("/buttons.html")
    public String buttons() {
        return "buttons";
    }

    @GetMapping("/calendar.html")
    public String calendar() {
        return "calendar";
    }

    @GetMapping("/content-slider.html")
    public String contentSlider() {
        return "content-slider";
    }

    @GetMapping("/dropzone.html")
    public String dropzone() {
        return "dropzone";
    }

    @GetMapping("/elements.html")
    public String elements() {
        return "elements";
    }

    @GetMapping("/email.html")
    public String email() {
        return "email";
    }

    @GetMapping("/email-confirmation.html")
    public String emailConfirmation() {
        return "email-confirmation";
    }

    @GetMapping("/email-contrast.html")
    public String emailContrast() {
        return "email-contrast";
    }

    @GetMapping("/email-navbar.html")
    public String emailNavbar() {
        return "email-navbar";
    }

    @GetMapping("/email-newsletter.html")
    public String emailNewsletter() {
        return "email-newsletter";
    }

    @GetMapping("/error-404.html")
    public String error404() {
        return "error-404";
    }

    @GetMapping("/error-500.html")
    public String error500() {
        return "error-500";
    }

    @GetMapping("/faq.html")
    public String faq() {
        return "faq";
    }

    @GetMapping("/form-elements.html")
    public String formElements() {
        return "form-elements";
    }

    @GetMapping("/form-elements-2.html")
    public String formElements2() {
        return "form-elements-2s";
    }

    @GetMapping("/form-wizard.html")
    public String formWizard() {
        return "form-wizard";
    }

    @GetMapping("/gallery.html")
    public String gallery() {
        return "gallery";
    }

    @GetMapping("/grid.html")
    public String grid() {
        return "grid";
    }

    @GetMapping("/inbox.html")
    public String inbox() {
        return "inbox";
    }

    @GetMapping("/invoice.html")
    public String invoice() {
        return "invoice";
    }

    @GetMapping("/jqgrid.html")
    public String jqgrid() {
        return "jqgrid";
    }

    @GetMapping("/jquery-ui.html")
    public String jqueryUi() {
        return "jquery-ui";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/mobile-menu-1.html")
    public String mobileMenu1() {
        return "mobile-menu-1";
    }

    @GetMapping("/mobile-menu-2.html")
    public String mobileMenu2() {
        return "mobile-menu-2";
    }

    @GetMapping("/mobile-menu-3.html")
    public String mobileMenu3() {
        return "mobile-menu-3";
    }

    @GetMapping("/nestable-list.html")
    public String nestableList() {
        return "nestable-list";
    }

    @GetMapping("/pricing.html")
    public String pricing() {
        return "pricing";
    }

    @GetMapping("/profile.html")
    public String profile() {
        return "profile";
    }

    @GetMapping("/search.html")
    public String search() {
        return "search";
    }

    @GetMapping("/tables.html")
    public String tables() {
        return "tables";
    }

    @GetMapping("/timeline.html")
    public String timeline() {
        return "timeline";
    }

    @GetMapping("/top-menu.html")
    public String topMenu() {
        return "top-menu";
    }

    @GetMapping("/treeview.html")
    public String treeview() {
        return "treeview";
    }

    @GetMapping("/two-menu-1.html")
    public String twomenu1() {
        return "two-menu-1";
    }

    @GetMapping("/two-menu-2.html")
    public String twomenu2() {
        return "two-menu-2";
    }

    @GetMapping("/typography.html")
    public String typography() {
        return "typography";
    }

    @GetMapping("/widgets.html")
    public String widgets() {
        return "widgets";
    }

    @GetMapping("/wysiwyg.html")
    public String wysiwyg() {
        return "wysiwyg";
    }

}
