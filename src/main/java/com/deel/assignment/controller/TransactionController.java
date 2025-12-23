package com.deel.assignment.controller;



import com.deel.assignment.model.Transaction;
import com.deel.assignment.service.CsvService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TransactionController {

    private final CsvService csvService;

    public TransactionController(CsvService csvService) {
        this.csvService = csvService;
    }

    @GetMapping("/similar-transactions")
    public Map<String, Object> similar(@RequestParam String text) {

        List<Transaction> transactions = csvService.getTransactions();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Transaction t : transactions) {
            if (t.description.toLowerCase().contains(text.toLowerCase())) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", t.id);
                map.put("description", t.description);
                result.add(map);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("transactions", result);
        response.put("total_number_of_tokens_used", text.split(" ").length);

        return response;
    }
}

