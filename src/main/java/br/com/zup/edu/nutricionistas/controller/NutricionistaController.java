package br.com.zup.edu.nutricionistas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.edu.nutricionistas.repository.NutricionistaRepository;

@RestController
@RequestMapping(NutricionistaController.BASE_URI)
public class NutricionistaController {

    public final static String BASE_URI = "/nutricionistas";

    private final NutricionistaRepository nutricionistaRepository;

    public NutricionistaController(NutricionistaRepository nutricionistaRepository) {
        this.nutricionistaRepository = nutricionistaRepository;
    }

}
