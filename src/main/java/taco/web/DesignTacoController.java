package taco.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import taco.Ingredient;
import taco.Ingredient.Type;
import taco.Taco;



@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FlTO","fLOUR tORTILLA",Type.WRAP),
				new Ingredient("COTO","Corn Tortilla",Type.WRAP),
				new Ingredient("GRBF","Ground Beef",Type.PROTEIN),
				new Ingredient("GARN","Carnitas",Type.PROTEIN),
				new Ingredient("LETC","Lettuce",Type.VEGGIES)
				);
		
		Type[] types = Ingredient.Type.values();
		for(Type type : types){
			model.addAttribute(type.toString().toLowerCase(),
			filterBytype(ingredients,type));
		}
		
		model.addAttribute("taco",new Taco());
		
		return "design";
	}
	
	private List<Ingredient> filterBytype(List<Ingredient> ingredients, Type type){
			return ingredients.stream().filter(x-> x.getType().equals(type)).collect(Collectors.toList());
		}
	}

