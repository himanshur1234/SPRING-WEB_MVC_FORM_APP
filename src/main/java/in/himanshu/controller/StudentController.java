package in.himanshu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.himanshu.binding.Student;
import in.himanshu.entity.StudentEntity;
import in.himanshu.repository.StudentRepository;

@Controller
public class StudentController {
	private void loadFormData(Model model) {
		List<String> coursesList =new ArrayList<>();
		coursesList.add("Java");
		coursesList.add("DevOps");
		coursesList.add("AWS");
		coursesList.add("Python");
		
		List<String> timingsList =new ArrayList<>();
		timingsList.add("Morning");
		timingsList.add("Afternoon");
		timingsList.add("Evening");
		
		Student student = new Student();
		
		model.addAttribute("student",student);
		model.addAttribute("courses",coursesList);
		model.addAttribute("timings",timingsList);
	}
	@Autowired
	private StudentRepository repo;
	//method to load student data
	@GetMapping("/")
	public String loadForm(Model model) {
		
		loadFormData(model);
		return "index";
	}

	//method to save student form data
	@PostMapping("/save")
	public String handleSubmit(Student s,Model model) {
		StudentEntity entity=new StudentEntity();
		//copy data from binding data to entity obj
		BeanUtils.copyProperties(s, entity);
		
		entity.setTimings(Arrays.toString(s.getTimings()));
		repo.save(entity);
		
		model.addAttribute("msg","Student Saved");
		loadFormData(model);
		
		return "index";
	}
	
	
	//method to display save student data
}
