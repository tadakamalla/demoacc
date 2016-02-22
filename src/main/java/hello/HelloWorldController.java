package hello;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.Employee;
 

 
@Controller
@RequestMapping("employees")
public class HelloWorldController {
 
    Employee employee = new Employee();
 
    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Employee getEmployeeInJSON(@PathVariable String name) {
 
   	 employee.setName(name);
   	 employee.setEmail("tadakamalla@gmail.com");
 
   	 return employee;
 
    }
 
    @RequestMapping(value = "/{name}.xml", method = RequestMethod.GET, produces = "application/xml")
    public @ResponseBody Employee getEmployeeInXML(@PathVariable String name) {
 
   	 employee.setName(name);
   	 employee.setEmail("tadakamalla@gmail.com");
 
   	 return employee;
 
    }
    
    @RequestMapping(value = "/post/employee", method = RequestMethod.POST,   consumes={"application/json"})
    
     @ResponseBody 
     public String post(@RequestBody String json) throws IOException {
    	    
    	    ObjectMapper mapper = new ObjectMapper();
    	    
    	    employee = mapper.readValue(json, Employee.class);
    	    
    	    //do some things with json, put some header information in json
    	    System.out.println(employee.getEmail());
    	    System.out.println(employee.getName());
    	    
    	    return mapper.writeValueAsString(employee);
    	    
    	}
   
}