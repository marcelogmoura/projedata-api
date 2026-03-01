package br.com.projetada.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.projetada.application.dto.ProductionRecommendationDTO;
import br.com.projetada.domain.Material;
import br.com.projetada.domain.Product;
import br.com.projetada.domain.ProductIngredient;
import br.com.projetada.domain.shared.Result;
import br.com.projetada.infrastructure.repository.MaterialRepository;
import br.com.projetada.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductionService {

    private final ProductRepository productRepository;
    private final MaterialRepository materialRepository;

    public Result<ProductionRecommendationDTO> calculateOptimization() {
        // mais caro -> mais barato
        List<Product> products = productRepository.findAllByOrderByPriceDesc();
        
        Map<String, Integer> itemsToProduce = new HashMap<>();
        Double totalValue = 0.0;
        
        for (Product product : products) {
            int count = 0;
            boolean canProduce = true;

            while (canProduce) {
                // ingredientes têm estoque suficiente
                for (ProductIngredient ingredient : product.getIngredients()) {
                    Material material = ingredient.getMaterial();
                    if (material.getStockQuantity() < ingredient.getQuantityRequired()) {
                        canProduce = false;
                        break;
                    }
                }

                if (canProduce) {
                    // diminui o estoque subtraindo direto do objeto Material
                    for (ProductIngredient ingredient : product.getIngredients()) {
                        Material material = ingredient.getMaterial();
                        double newStock = material.getStockQuantity() - ingredient.getQuantityRequired();
                        material.setStockQuantity(newStock);
                    }
                    count++;
                }
            }

            if (count > 0) {
                itemsToProduce.put(product.getName(), count);
                totalValue += (product.getPrice() * count);
            }
        }

        return Result.success(new ProductionRecommendationDTO(itemsToProduce, totalValue));
    }
}