/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.repository;

import io.sevenluck.chat.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author loki
 */
public interface UserRepository extends CrudRepository<User, Long>{
    
}
