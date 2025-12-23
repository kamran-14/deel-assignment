package com.deel.assignment.controller;


import com.deel.assignment.model.Transaction;
import com.deel.assignment.model.User;
import com.deel.assignment.service.CsvService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MatchController {

    private final CsvService csvService;

    public MatchController(CsvService csvService) {
        this.csvService = csvService;
    }

    @GetMapping("/match-users/{transactionId}")
    public Map<String, Object> match(@PathVariable String transactionId) {

        // 1. Get users and transactions from CSV
        List<User> users = csvService.getUsers();
        List<Transaction> transactions = csvService.getTransactions();

        // 2. Find transaction by ID
        Transaction tx = null;
        for (Transaction t : transactions) {
            if (t.id.equals(transactionId)) {
                tx = t;
                break;
            }
        }

        // 3. Match users with transaction description
        List<Map<String, Object>> matchedUsers = new ArrayList<>();

        if (tx != null) {
            for (User u : users) {
                if (tx.description.toLowerCase().contains(u.name.toLowerCase())) {
                    Map<String, Object> match = new HashMap<>();
                    match.put("id", u.id);
                    match.put("match_metric", 90);
                    matchedUsers.add(match);
                }
            }
        }

        // 4. Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("users", matchedUsers);
        response.put("total_number_of_matches", matchedUsers.size());

        return response;
    }
}

