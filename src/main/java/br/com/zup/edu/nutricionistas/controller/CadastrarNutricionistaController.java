package br.com.zup.edu.nutricionistas.controller;

import br.com.zup.edu.nutricionistas.model.Nutricionista;
import br.com.zup.edu.nutricionistas.repository.NutricionistaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/nutricionistas")
public class CadastrarNutricionistaController {
    private final NutricionistaRepository repository;

    public CadastrarNutricionistaController(NutricionistaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NutricionistaRequest request,
                                       UriComponentsBuilder uriComponentsBuilder) {

        Nutricionista nutricionista = request.paraNutricionista();

        repository.save(nutricionista);

        URI location = uriComponentsBuilder.path("/nutricionistas/{id}")
                .buildAndExpand(nutricionista.getId())
                .toUri();

        return created(location).build();

    }
}
