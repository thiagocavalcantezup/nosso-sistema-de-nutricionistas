package br.com.zup.edu.nutricionistas.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.nutricionistas.model.Nutricionista;
import br.com.zup.edu.nutricionistas.model.NutricionistaDTO;
import br.com.zup.edu.nutricionistas.repository.NutricionistaRepository;

@RestController
@RequestMapping(NutricionistaController.BASE_URI)
public class NutricionistaController {

    public final static String BASE_URI = "/nutricionistas";

    private final NutricionistaRepository nutricionistaRepository;

    public NutricionistaController(NutricionistaRepository nutricionistaRepository) {
        this.nutricionistaRepository = nutricionistaRepository;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid NutricionistaDTO nutricionistaDTO,
                                       UriComponentsBuilder uriComponentsBuilder) {
        Nutricionista nutricionista = nutricionistaRepository.save(
            nutricionistaDTO.paraNutricionista()
        );

        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}")
                                           .buildAndExpand(nutricionista.getId())
                                           .toUri();

        return ResponseEntity.created(location).build();
    }

}
