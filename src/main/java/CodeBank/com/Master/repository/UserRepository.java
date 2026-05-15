package CodeBank.com.Master.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import CodeBank.com.Master.Model.User;

public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByCpf(String cpf);


}
