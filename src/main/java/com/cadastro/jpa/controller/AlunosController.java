package com.cadastro.jpa.controller;

import com.cadastro.jpa.entity.Aluno;
import com.cadastro.jpa.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;

@Transactional
@Controller
@RequestMapping("/alunos")
public class AlunosController {

    @Autowired
    AlunoRepository alunoRepository;

    @RequestMapping( value = { "/" , "" ,"/home" })
    public ModelAndView homePrincipal(){
        //Retorna a view que deve ser chamada, no caso principal (principal.jsp) que esta dentro da pasta /home
        return new ModelAndView("alunos/home");
    }

    @GetMapping("/form")
    public String form(Aluno aluno) {
        return "/alunos/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("alunos", alunoRepository.pessoas());
        return new ModelAndView("/alunos/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Aluno aluno){
        alunoRepository.save(aluno);
        return new ModelAndView("redirect:/alunos/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        alunoRepository.remove(id);
        return new ModelAndView("redirect:/alunos/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("aluno", alunoRepository.pessoa(id));
        return new ModelAndView("/alunos/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Aluno aluno) {
        alunoRepository.update(aluno);
        return new ModelAndView("redirect:/alunos/list");
    }
}
