package CodeBank.com.Master.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CodeBank.com.Master.Dto.CustomerLoanResponse;
import CodeBank.com.Master.Dto.CustomerRequest;
import CodeBank.com.Master.service.LoanService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<CustomerLoanResponse> checkEligibility(@Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(loanService.checkEligibility(request));
    }
   
}
