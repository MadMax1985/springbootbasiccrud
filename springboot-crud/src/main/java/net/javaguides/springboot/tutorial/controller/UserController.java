package net.javaguides.springboot.tutorial.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.tutorial.entity.User;
import net.javaguides.springboot.tutorial.repository.UserRepository;

@Controller
@RequestMapping("/")
public class UserController {

	private final UserRepository studentRepository;

	@Autowired
	public UserController(UserRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("403")
    public String error403() {
        return "/error/403";
    }

	@GetMapping("users/signup")
	public String showSignUpForm(User student) {
		return "add-user";
	}

	@GetMapping("users/list")
	public String showUpdateForm(Model model) {
		model.addAttribute("users", studentRepository.findAll());
		return "index";
	}

	@PostMapping("users/add")
	public String addStudent(@Valid User student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-user";
		}
		
		studentRepository.save(student);
		return "redirect:list";
	}

	@GetMapping("users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		model.addAttribute("user", student);
		return "update-user";
	}

	@PostMapping("users/update/{id}")
	public String updateStudent(@PathVariable("id") long id, @Valid User student, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			student.setId(id);
			return "update-user";
		}

		studentRepository.save(student);
		model.addAttribute("users", studentRepository.findAll());
		return "index";
	}

	@GetMapping("users/delete/{id}")
	public String deleteStudent(@PathVariable("id") long id, Model model) {
		User student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		studentRepository.delete(student);
		model.addAttribute("users", studentRepository.findAll());
		return "index";
	}
}
