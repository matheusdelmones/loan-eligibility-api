package CodeBank.com.Master.Dto;

import java.util.List;

public record CustomerLoanResponse(String customer, List<LoanResponse> loans)  {
    
}
