package br.com.projetada.application.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductCreateDTO {
    private String code;
    private String name;
    private Double price;
    private List<IngredientQuantityDTO> ingredients;
    
}


