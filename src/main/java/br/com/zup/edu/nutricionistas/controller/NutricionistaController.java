package br.com.zup.edu.nutricionistas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(NutricionistaController.BASE_URI)
public class NutricionistaController {

    public final static String BASE_URI = "/nutricionistas";

}
