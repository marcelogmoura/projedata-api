package br.com.projetada.application.dto;

import lombok.Data;

@Data
public class MaterialDTO {
    private String code;
    private String name;
    private Double stockQuantity;
}