package br.com.projetada.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetada.domain.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

}
