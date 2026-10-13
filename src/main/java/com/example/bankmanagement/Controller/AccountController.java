        package com.example.bankmanagement.Controller;

import com.example.bankmanagement.Api.ApiResponse;
import com.example.bankmanagement.Model.Account;
import com.example.bankmanagement.Model.Customer;
import com.example.bankmanagement.Model.User;
import com.example.bankmanagement.Service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(accountService.getAll());
    }

    @GetMapping("/my-accounts")
    public ResponseEntity<?> getMyAccounts(
            @AuthenticationPrincipal User user) {

        return ResponseEntity.status(200)
                .body(accountService.getMyAccounts(user.getId()));
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@AuthenticationPrincipal Customer customer, @RequestBody Account account) {
        accountService.add(customer.getId(), account);

        return ResponseEntity.status(200).body(new ApiResponse("Account added successfully"));
    }

    @PutMapping("/update/{accountId}")
    public ResponseEntity<?> update(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId, @RequestBody Account account) {

        accountService.update(customer.getId(), accountId, account);

        return ResponseEntity.status(200)
                .body(new ApiResponse("Account updated successfully"));
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId) {

        accountService.delete(customer.getId(), accountId);

        return ResponseEntity.status(200).body(new ApiResponse("Account deleted successfully"));
    }

    @GetMapping("/details/{accountId}")
    public ResponseEntity<?> getDetails(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId) {

        return ResponseEntity.status(200).body(accountService.getDetails(customer.getId(), accountId));
    }

    @PutMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity<?> deposit(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId, @PathVariable Integer amount) {

        accountService.deposit(customer.getId(), accountId, amount);

        return ResponseEntity.status(200).body(new ApiResponse("Deposit completed successfully"));
    }

    @PutMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity<?> withdraw(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId, @PathVariable Integer amount) {

        accountService.Withdraw(customer.getId(), accountId, amount);

        return ResponseEntity.status(200)
                .body(new ApiResponse("Withdrawal completed successfully"));
    }

    @PutMapping("/transfer/{accountOneId}/{accountTwoId}/{amount}")
    public ResponseEntity<?> transfer(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountOneId, @PathVariable Integer accountTwoId, @PathVariable Integer amount) {

        accountService.transfer(customer.getId(), accountOneId, accountTwoId, amount);

        return ResponseEntity.status(200).body(new ApiResponse("Transfer completed successfully"));
    }

    @PutMapping("/block/{accountId}")
    public ResponseEntity<?> block(@AuthenticationPrincipal User admin, @PathVariable Integer accountId) {

        accountService.block(admin.getId(), accountId);

        return ResponseEntity.status(200)
                .body(new ApiResponse("Account blocked successfully"));
    }
}
