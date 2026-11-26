package com.hostelfinder.service;

import com.hostelfinder.entity.Campus;
import com.hostelfinder.entity.Hostel;
import com.hostelfinder.entity.Report;
import com.hostelfinder.entity.User;
import com.hostelfinder.repository.CampusRepository;
import com.hostelfinder.repository.HostelRepository;
import com.hostelfinder.repository.ReportRepository;
import com.hostelfinder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final HostelRepository hostelRepository;
    private final CampusRepository campusRepository;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    public AdminService(HostelRepository hostelRepository, CampusRepository campusRepository, UserRepository userRepository, ReportRepository reportRepository) {
        this.hostelRepository = hostelRepository;
        this.campusRepository = campusRepository;
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }

    public List<Hostel> getPendingHostels() {
        return hostelRepository.findByStatus("PENDING");
    }

    public long getPendingCount() {
        return hostelRepository.findByStatus("PENDING").size();
    }

    public void approveHostel(Long id) {
        Hostel hostel = hostelRepository.findById(id).orElseThrow();
        hostel.setStatus("APPROVED");
        hostel.setRejectionReason(null); // Clear any old reason
        hostelRepository.save(hostel);
    }

    // UPDATED: Reject with a reason
    public void rejectHostel(Long id, String reason) {
        Hostel hostel = hostelRepository.findById(id).orElseThrow();
        hostel.setStatus("REJECTED");
        hostel.setRejectionReason(reason);
        hostelRepository.save(hostel);
    }

    // NEW: Deactivate a listing
    public void deactivateHostel(Long id) {
        Hostel hostel = hostelRepository.findById(id).orElseThrow();
        hostel.setStatus("DEACTIVATED");
        hostelRepository.save(hostel);
    }

    public long getApprovedListingsCount() {
        return hostelRepository.findByStatus("APPROVED").size();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void suspendUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setRole("SUSPENDED");
        userRepository.save(user);
    }

    public List<Campus> getAllCampuses() {
        return campusRepository.findAll();
    }

    public void addCampus(String name) {
        Campus campus = new Campus();
        campus.setName(name);
        campusRepository.save(campus);
    }

    public List<Report> getOpenReports() {
        return reportRepository.findByStatus("OPEN");
    }

    public void resolveReport(Long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow();
        report.setStatus("RESOLVED");
        reportRepository.save(report);
    }
}