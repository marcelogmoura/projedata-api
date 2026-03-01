package br.com.projetada.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetada.application.dto.ProductCreateDTO;
import br.com.projetada.domain.Material;
import br.com.projetada.domain.Product;
import br.com.projetada.domain.ProductIngredient;
import br.com.projetada.infrastructure.repository.MaterialRepository;
import br.com.projetada.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;
    private final MaterialRepository materialRepository;

    @GetMapping
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @PostMapping
    public Product create(@RequestBody ProductCreateDTO dto) {
        // 1. Cria a base do produto
        Product product = new Product();
        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setIngredients(new ArrayList<>());

        // 2. Associa os ingredientes buscando no banco pelo ID
        dto.getIngredients().forEach(ingDto -> {
            Material material = materialRepository.findById(ingDto.getMaterialId())
                    .orElseThrow(() -> new RuntimeException("Material not found"));
            
            ProductIngredient ingredient = new ProductIngredient();
            ingredient.setProduct(product);
            ingredient.setMaterial(material);
            ingredient.setQuantityRequired(ingDto.getQuantity());
            
            product.getIngredients().add(ingredient);
        });

        return productRepository.save(product);
    }
}