package br.com.zup.edu.nutricionistas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.edu.nutricionistas.model.Nutricionista;

public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {

}
