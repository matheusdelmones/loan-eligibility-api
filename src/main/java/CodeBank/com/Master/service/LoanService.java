package CodeBank.com.Master.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import CodeBank.com.Master.Dto.CustomerLoanResponse;
import CodeBank.com.Master.Dto.CustomerRequest;
import CodeBank.com.Master.Dto.LoanResponse;
import CodeBank.com.Master.Model.User;
import CodeBank.com.Master.repository.UserRepository;


@Service
public class LoanService {

    private final UserRepository userRepository;

    public LoanService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CustomerLoanResponse checkEligibility(CustomerRequest request) {

    if (userRepository.findByCpf(request.cpf()).isPresent()) {
        throw new IllegalArgumentException("CPF ja cadastrado");
    } else {
        userRepository.save(new User(request.name(), request.age(), request.income(), request.location(), request.cpf()));
    }

    List<LoanResponse> availableLoans = new ArrayList<>();

    if (request.income() >= 5000) {
        availableLoans.add(new LoanResponse("CONSIGNMENT", 2));
    }

    if (request.income() <= 3000 || 
        (request.income() >= 3000 && request.income() <= 5000 && request.age() < 30 && "SP".equalsIgnoreCase(request.location()))) {
        availableLoans.add(new LoanResponse("PERSONAL", 4));
        availableLoans.add(new LoanResponse("GUARANTEED", 3));
    }

    return new CustomerLoanResponse(request.name(), availableLoans);
    }



}
