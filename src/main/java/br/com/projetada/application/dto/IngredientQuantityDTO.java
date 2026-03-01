package br.com.projetada.application.dto;

import lombok.Data;

@Data
public class IngredientQuantityDTO {
    private Long materialId;
    private Double quantity;
}