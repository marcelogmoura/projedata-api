package br.com.projetada.api.controller;

import br.com.projetada.application.dto.MaterialDTO;
import br.com.projetada.domain.Material;
import br.com.projetada.infrastructure.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MaterialController {

    private final MaterialRepository repository;

    @GetMapping
    public List<Material> listAll() {
        return repository.findAll();
    }    
    

    @PostMapping
    public ResponseEntity<Material> create(@RequestBody MaterialDTO dto) {
        Material material = new Material();
        material.setCode(dto.getCode());
        material.setName(dto.getName());
        material.setStockQuantity(dto.getStockQuantity());
        
        return ResponseEntity.ok(repository.save(material));
    }
    
    
}