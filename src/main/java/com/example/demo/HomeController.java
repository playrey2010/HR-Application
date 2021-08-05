package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class HomeController {
    ArrayList<Department> departments = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    boolean preloaded = false;


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/")
    public String index(Model model){
        if (!preloaded){
            preloaded = true;

            User user1 = new User("super", "super@man.com", "password", "Super", "Man", true);
            userRepository.save(user1);
            Employee employee1 = new Employee(user1);


            Department itDpt = new Department();
            itDpt.setName("IT");
            employee1.setDepartment(itDpt);
            itDpt.addEmployee(employee1);

            departments.add(itDpt);
        }
        model.addAttribute("departments", departments);

        return "index";
    }

    @RequestMapping("/viewEmployee/{id}")
    public String viewEmployee(@PathVariable long id, Model model){
        Employee employee = null;
        for (Department department: departments){
            if (department.hasEmployeeById(id)){
                employee = department.findEmployeeById(id);
                break;
            }
        }
        model.addAttribute("employee", employee);
        return "viewEmployee";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departments);
        return "employeeForm";
    }




    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout=true";
    }


    @RequestMapping("/admin")
    public String admin () {
        return "admin";
    }

    @RequestMapping("/secure")
    public String secure(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
                                          BindingResult result, Model model){
        if (result.hasErrors()){
            user.clearPassword();
            model.addAttribute("user", user);
            return "register";
        }
        else {
            model.addAttribute("user", user);
            model.addAttribute("message", "New user account created");
            user.setEnabled(true);
            userRepository.save(user);


            Role role = new Role(user.getUsername(), "ROLE_USER");
            roleRepository.save(role);


            if (!employeeExists(departments, user)){
                Employee employee = new Employee(user);

            }



            model.addAttribute("departments", departments);
            model.addAttribute("employees", employees);
        }
        return "index";
    }

    static boolean employeeExists(ArrayList<Department> departments, User user){
        long employeeId = user.getId();
        boolean answer = false;
        for (Department department: departments){
            if (department.hasEmployeeById(employeeId)){
                answer = true;
                break;
            }
        }
        return answer;
    }

}
