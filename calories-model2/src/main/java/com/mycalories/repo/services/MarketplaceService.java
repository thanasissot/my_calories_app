package com.mycalories.repo.services;

import com.mycalories.model2.marketplace.Marketplace;
import com.mycalories.model2.marketplace.MarketplaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketplaceService {
    private final MarketplaceRepository marketplaceRepository;

    public List<Marketplace> getAllMarketplaces() {
        return this.marketplaceRepository.findAll();
    }

    public Marketplace getMarketplaceByName(String name) {
        return this.marketplaceRepository.findMarketplaceByName(name).orElse(null);
    }

    public Marketplace createMarketplace(Marketplace marketplace) {
        return this.marketplaceRepository.save(marketplace);
    }
}
