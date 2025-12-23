package com.deel.assignment.service;

import com.deel.assignment.model.Transaction;
import com.deel.assignment.model.User;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    private final List<User> users = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    @PostConstruct
    public void loadData() throws Exception {
        loadUsers();
        loadTransactions();
    }

    private void loadUsers() throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/data/users.csv")
                )
        );

        String line;
        br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            String[] a = line.split(",");
            if (a.length < 2) continue;

            User u = new User();
            u.id = a[0].trim();
            u.name = a[1].trim();
            users.add(u);
        }
    }

    private void loadTransactions() throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/data/transactions.csv")
                )
        );

        String line;
        br.readLine(); // skip header

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            String[] a = line.split(",", 3);
            if (a.length < 3) continue;

            Transaction t = new Transaction();
            t.id = a[0].trim();
            t.description = a[2].trim();
            transactions.add(t);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

