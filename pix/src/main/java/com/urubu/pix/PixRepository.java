package com.urubu.pix;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PixRepository extends JpaRepository<User, Long> {
    
}
