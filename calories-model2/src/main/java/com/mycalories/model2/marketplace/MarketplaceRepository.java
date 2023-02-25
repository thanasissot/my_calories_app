package com.mycalories.model2.marketplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketplaceRepository extends JpaRepository<Marketplace, Long> {
    Optional<Marketplace> findMarketplaceByName(String name);
}
