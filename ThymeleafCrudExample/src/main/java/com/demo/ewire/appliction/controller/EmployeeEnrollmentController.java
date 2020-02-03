package com.demo.ewire.appliction.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.ewire.appliction.dao.employeeDAO;
import com.demo.ewire.appliction.model.Employee;

@Controller
public class EmployeeEnrollmentController {

	@Autowired
	private employeeDAO EmployeeDao;

	@RequestMapping(value = "/enroll", method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "enroll";
	}

	@RequestMapping(value = "/")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveRegistration(@Valid Employee employee, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println("has errors");
			return "enroll";
		}

		EmployeeDao.save(employee);

		return "redirect:/viewemployees";
	}

	@RequestMapping(value = "/viewemployees")
	public ModelAndView getAll() {

		List<Employee> list = EmployeeDao.findAll();
		return new ModelAndView("viewemployees", "list", list);
	}

	@RequestMapping(value = "/delete")
	public void deleteAll() {
		String table = "employee";
		EmployeeDao.deleteAllFromTable(table);
	}

	@RequestMapping(value = "/editemployee/{id}")
	public String edit(@PathVariable int id, ModelMap model) {

		Employee employee = EmployeeDao.findOne(id);
		model.addAttribute("employee", employee);
		return "editemployee";
	}

	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public ModelAndView editsave(@ModelAttribute("employee") Employee p) {

		Employee employee = EmployeeDao.findOne(p.getId());

		employee.setFirstName(p.getFirstName());
		employee.setLastName(p.getLastName());
		employee.setCountry(p.getCountry());
		employee.setEmail(p.getEmail());
		employee.setSection(p.getSection());
		employee.setSex(p.getSex());

		EmployeeDao.save(employee);
		return new ModelAndView("redirect:/viewemployees");
	}

	@RequestMapping(value = "/deleteemployee/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id) {
		Employee employee = EmployeeDao.findOne(id);
		EmployeeDao.delete(employee);
		return new ModelAndView("redirect:/viewemployees");
	}

	@ModelAttribute("sections")
	public List<String> intializeSections() {
		List<String> sections = new ArrayList<String>();
		sections.add("Graduate");
		sections.add("Post Graduate");
		sections.add("Reasearch");
		return sections;
	}

	/*
	 * Method used to populate the country list in view. Note that here you can call
	 * external systems to provide real data.
	 */
	@ModelAttribute("countries")
	public List<String> initializeCountries() {

		List<String> countries = new ArrayList<String>();
		countries.add("INDIA");
		countries.add("USA");
		countries.add("CANADA");
		countries.add("FRANCE");
		countries.add("GERMANY");
		countries.add("ITALY");
		countries.add("OTHER");
		return countries;
	}

}
