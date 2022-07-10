package com.sim.shoppingmall.domain.board;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardJpaRepository extends JpaRepository<Board,Long> {

}
