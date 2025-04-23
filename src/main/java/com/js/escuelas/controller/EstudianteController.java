package com.js.escuelas.controller;

import com.js.escuelas.model.Estudiante;
import com.js.escuelas.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Este método es para convertir la fecha a LocalDate de manera correcta
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null && !text.trim().isEmpty()) {
                    setValue(LocalDate.parse(text.trim(), dateFormatter));
                } else {
                    setValue(null); // Si la fecha es vacía o nula, se establece como null
                }
            }
        });
    }
    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public String getAll(Model model){
        List<Estudiante> estudiantes = estudianteService.getAll();
        model.addAttribute("estudiantes", estudiantes);
        return "index";
    }

    @GetMapping("/nombre")
    @PreAuthorize("hasAuthority('READ')")
    public String buscarPorNombre(@RequestParam String nombre, Model model) {
        List<Estudiante> estudiantes = estudianteService.findByNombre(nombre);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("nombreBuscado", nombre);
        return "index";
    }

    @GetMapping("/agregar")
    @PreAuthorize("hasAuthority('CREATE')")
    public String agregarEstudiante(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "agregar";
    }

    @PostMapping("/guardar")
    @PreAuthorize("hasAuthority('CREATE')")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteService.save(estudiante);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public String update(Estudiante estudiante, Model model){
        estudiante = estudianteService.findEstudiante(estudiante);
        model.addAttribute("estudiante", estudiante);
        return "agregar";
    }

    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public String delete(Estudiante estudiante){
        estudianteService.delete(estudiante.getId());
        return "redirect:/";
        }

        @GetMapping("/login")
        public String login(){
            return "login";
        }

        @GetMapping("/errores/403")
        public String accesDenied(){
            return "errores/403";
        }


}
