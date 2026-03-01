package br.com.projetada.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ProductionRecommendationDTO {
    private Map<String, Integer> itemsToProduce; // Nome do Produto -> Quantidade
    private Double totalEstimatedValue;        // Valor total de venda
}