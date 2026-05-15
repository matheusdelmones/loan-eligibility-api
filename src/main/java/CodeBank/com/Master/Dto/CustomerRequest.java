package CodeBank.com.Master.Dto;

import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

    @NotBlank(message = "Nome é obrigatorio")
    String name,

    @NotNull(message = "Idade é obrigatoria") 
    @Min(value = 18, message = "A idade minima é 18 anos")
    Integer age,

    @Min(value = 0, message = "A renda nao pode ser negativa")
    @NotNull(message = "Renda é obrigatoria") 
    Float income,

    @NotBlank(message = "Localização é obrigatoria") 
    String location,

    @NotBlank(message = "CPF é obrigatorio")
    @CPF
    String cpf

    ) 
    {
       
}
