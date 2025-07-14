// TASK 2 - find by roles specified


// Controller
@GetMapping("/roles/{role}")
@PreAuthorize("hasRole('ADMIN')")
public List<RegisterDetails> getEmployeesByRoles(@PathVariable String role){
      return employeeService.findEmployeesByRole(role);
}


// Service
public List<RegisterDetails> findEmployeesByRole(String roleName){
      List<RegisterDetails> employees = new ArrayList<>();

      for(RegisterDetails registerDetails : regRepo.findAll()){
          for(Roles role : registerDetails.getRoles()){
              if(role.getRoleName().equals(roleName.toUpperCase())){
                  employees.add(registerDetails);
              }
          }
      }
      return employees;
}
