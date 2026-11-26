package com.hostelfinder.controller;

import com.hostelfinder.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    private boolean isNotAdmin(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return role == null || !role.equals("ADMIN");
    }

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login?error";

        model.addAttribute("pendingHostels", adminService.getPendingHostels());
        model.addAttribute("pendingCount", adminService.getPendingCount());
        model.addAttribute("approvedCount", adminService.getApprovedListingsCount());
        model.addAttribute("openReports", adminService.getOpenReports());

        return "admin-dashboard";
    }

    @GetMapping("/admin/approve/{id}")
    public String approveListing(@PathVariable Long id, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login?error";
        adminService.approveHostel(id);
        return "redirect:/admin/dashboard";
    }

    // UPDATED: Handle POST request for rejection with reason
    @PostMapping("/admin/reject/{id}")
    public String rejectListing(@PathVariable Long id, @RequestParam("reason") String reason, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login?error";
        adminService.rejectHostel(id, reason);
        return "redirect:/admin/dashboard";
    }

    // NEW: Deactivate a listing
    @GetMapping("/admin/deactivate/{id}")
    public String deactivateListing(@PathVariable Long id, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login?error";
        adminService.deactivateHostel(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/suspend/{id}")
    public String suspendUser(@PathVariable Long id, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login?error";
        adminService.suspendUser(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/report/resolve/{id}")
    public String resolveReport(@PathVariable Long id, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login?error";
        adminService.resolveReport(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/campuses")
    public String showCampuses(Model model, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login?error";
        model.addAttribute("campuses", adminService.getAllCampuses());
        return "campuses";
    }

    @PostMapping("/admin/campuses/add")
    public String addCampus(@RequestParam("name") String name, HttpSession session) {
        if (isNotAdmin(session)) return "redirect:/login?error";
        adminService.addCampus(name);
        return "redirect:/admin/campuses";
    }
}