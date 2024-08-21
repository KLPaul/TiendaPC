package com.example.demo.controllers;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.entity.Categoria;
import com.example.demo.models.entity.Producto;
import com.example.demo.models.service.ICategoriaService;
import com.example.demo.models.service.IProductoService;



@Controller
@SessionAttributes("producto")
public class ProductoController {
	
	@Autowired
	private IProductoService prService;
	
	@Autowired
	private ICategoriaService catService;
	
	@RequestMapping(value="/Inicio", method=RequestMethod.GET)
	public String Inicio() {
		
		return "/layout/layout";
		
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        // Para convertir el ID de categoría a un objeto Categoria
        binder.registerCustomEditor(Categoria.class, "categoria", new PropertyEditorSupport() {
            @Override
            public void setAsText(String id) {
                if (id != null && !id.isEmpty()) {
                    setValue(catService.findOne(Long.valueOf(id)));
                } else {
                    setValue(null);
                }
            }
        });

        // Para manejar la fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	
	@GetMapping("/buscar")
    public String buscar(@RequestParam(name="nombreProducto", required=false) String nombreProducto, Model model) {
        if (nombreProducto != null) {
            model.addAttribute("productos", prService.findByNombreProducto(nombreProducto));
        } else {
            model.addAttribute("productos", prService.findAll());
        }
        return "listarProducto";  
    }
	
	@RequestMapping(value="/listarProducto", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de Productos");
		model.addAttribute("productos", prService.findAll());
		
		return "listarProducto";
	}
	
	@RequestMapping(value="/formProducto")
	public String crear(Map<String, Object> model) {
		
		Producto producto = new Producto();
		model.put("producto", producto);
		model.put("titulo", "Abrir nuevo producto");
		
		model.put("categorias", catService.findAll());
		return "formProducto";
		
	}
	
	@RequestMapping(value="/formProducto", method=RequestMethod.POST)
	public String guardar(Producto producto,RedirectAttributes flash ) {
		
		String mensajeFls =(producto.getIdProducto() != null)? "El registro de producto se ha editado con exito" : "El registro de producto se ha creado con exito";
		prService.save(producto);
		flash.addFlashAttribute("success", mensajeFls);
		return "redirect:listarProducto"; 
		
	}
	
	@RequestMapping(value="/formProducto/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Producto producto = null;
		if(id>0) {
			
			producto = prService.findOne(id);
			
			if(producto == null) {
				
				flash.addFlashAttribute("info", "El producto no existe en la base de datos");
				
				return "redirect:/listarProducto";
			}
			
		}else {
			flash.addFlashAttribute("error", "El ID del producto no puede ser cero");
			return "redirect:/listarProducto";
		}
		
		model.put("producto", producto);
		model.put("titulo", "Editar del Producto");
		
		return "formProducto";
	}
	
	@GetMapping(value="/reporte/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash ) {
		
		Producto producto = prService.findOne(id);
		
		if(producto == null) {
			
			flash.addFlashAttribute("error", "El Producto no se encuentra en la base de datos");
			return "redirect:/listar";
		}
		
		model.put("producto", producto);
		
		model.put("titulo", "Informacion del producto de: " +producto.getNombreProducto());
		
		model.put("asunto","Numero de Serie: " +producto.getIdProducto());
		
		model.put("nombreProducto", producto.getNombreProducto());
		
		model.put("categoria",producto.getCategoria().getNombreCategoria());
		
		model.put("cantidad",producto.getCantidad());
		
		model.put("ubicacion",producto.getUbicacion());
		
		model.put("fecha",producto.getFechaCaducidad());

		return "reporte";
	}
	
	@RequestMapping(value="/eliminarProducto/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id>0) {
			prService.delete(id);
			flash.addFlashAttribute("success", "Producto eliminado con exito");
		}
		
		return "redirect:/listarProducto";
	}
	


}
