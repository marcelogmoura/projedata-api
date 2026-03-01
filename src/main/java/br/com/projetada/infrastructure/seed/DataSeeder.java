package br.com.projetada.infrastructure.seed;
import br.com.projetada.domain.*;
import br.com.projetada.infrastructure.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final MaterialRepository materialRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // 1. Criando Matérias-Primas (Insumos)
        Material iron = new Material(null, "M01", "Iron", 1000.0);
        Material wood = new Material(null, "M02", "Wood", 500.0);
        materialRepository.saveAll(Arrays.asList(iron, wood));

        // 2. Criando Produto 1 (Cadeira de Madeira - Barata)
        Product chair = new Product(null, "P01", "Wooden Chair", 50.0, null);
        productRepository.save(chair);
        
        ProductIngredient chairWood = new ProductIngredient(null, chair, wood, 100.0);
        chair.setIngredients(Arrays.asList(chairWood));
        productRepository.save(chair); // Atualiza com ingrediente

        // 3. Criando Produto 2 (Mesa de Luxo - Cara)
        Product table = new Product(null, "P02", "Luxury Table", 200.0, null);
        productRepository.save(table);
        
        ProductIngredient tableWood = new ProductIngredient(null, table, wood, 300.0);
        ProductIngredient tableIron = new ProductIngredient(null, table, iron, 50.0);
        table.setIngredients(Arrays.asList(tableWood, tableIron));
        productRepository.save(table);
        
        System.out.println(">> Banco de dados populado com sucesso para testes!");
    }
}
