package com.ead.authuser.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ead.authuser.models.UserModel;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

}
