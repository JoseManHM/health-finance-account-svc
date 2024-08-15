package com.healthaccountsvc.account.Repository;

import com.healthaccountsvc.account.Entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuarios, Integer> {

}
