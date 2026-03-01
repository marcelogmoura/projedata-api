package br.com.projetada.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetada.application.dto.ProductionRecommendationDTO;
import br.com.projetada.application.service.ProductionService;
import br.com.projetada.domain.shared.Result;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/production")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Liberando para o Vue.js consumir depois
public class ProductionController {

    private final ProductionService productionService;

    @GetMapping("/optimize")
    public ResponseEntity<?> getOptimization() {
        Result<ProductionRecommendationDTO> result = productionService.calculateOptimization();

        if (!result.isSuccess()) {
            return ResponseEntity.badRequest().body(result.getError());
        }

        return ResponseEntity.ok(result.getValue());
    }
}