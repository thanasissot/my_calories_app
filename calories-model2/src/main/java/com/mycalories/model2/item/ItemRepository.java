package com.mycalories.model2.item;

import com.mycalories.model2.marketplace.Marketplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<List<Item>> findAllByMarketplace(Marketplace marketplace);
}
