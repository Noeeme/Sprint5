package com.sprint.sprint5.repositories;

import com.sprint.sprint5.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
