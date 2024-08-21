package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.entity.Categoria;
import com.example.demo.models.service.ICategoriaService;

@Controller
@SessionAttributes("categoria")
public class CategoriaController {
	
	@Autowired
	private ICategoriaService catService;
	
	@GetMapping("/apiCategorias")
	@ResponseBody
    public List<Categoria> listarCategorias() {
        return catService.findAll();
    }
	
	@RequestMapping(value="/listarCategoria", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de Categorias");
		model.addAttribute("categorias", catService.findAll());
		
		return "listarCategoria";
	}
	
	@RequestMapping(value="/formCategoria")
	public String crear(Map<String, Object> model) {
		
		Categoria categoria = new Categoria();
		model.put("categoria", categoria);
		model.put("titulo", "Abrir nueva categoria");
		return "formCategoria";
		
	}
	
	@RequestMapping(value="/formCategoria", method=RequestMethod.POST)
	public String guardar(Categoria categoria,RedirectAttributes flash ) {
		
		String mensajeFls =(categoria.getIdCategoria() != null)? "El registro de categoria se ha editado con exito" : "El registro de categoria se ha creado con exito";
		catService.save(categoria);
		flash.addFlashAttribute("success", mensajeFls);
		return "redirect:listarCategoria"; 
		
	}
	
	@RequestMapping(value="/formCategoria/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Categoria categoria = null;
		if(id>0) {
			
			categoria = catService.findOne(id);
			
			if(categoria == null) {
				
				flash.addFlashAttribute("info", "La categoria no existe en la base de datos");
				
				return "redirect:/listarCategoria";
			}
			
		}else {
			flash.addFlashAttribute("error", "El ID de la categoria no puede ser cero");
			return "redirect:/listarCategoria";
		}
		
		model.put("categoria", categoria);
		model.put("titulo", "Editar la Categoria");
		
		return "formCategoria";
	}
	
	@RequestMapping(value="/eliminarCategoria/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id>0) {
			catService.delete(id);
			flash.addFlashAttribute("success", "Categoria eliminado con exito");
		}
		
		return "redirect:/listarCategoria";
	}

}
