// TASK 1 - Update query by id


// Controller
@PutMapping("/{id}")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public String updateEmployee(@PathVariable int id, @RequestBody UserDetailsDto reg){
        return employeeService.updateEmployeeById(id, reg);
}


// Service
public String updateEmployeeById(int id, UserDetailsDto employee) {
        Optional<RegisterDetails> existingEmp = regRepo.findById(id);

        if (existingEmp.isPresent()) {
            RegisterDetails existingEmployee = existingEmp.get();

            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPassword(employee.getPassword());
            existingEmployee.setUserName(employee.getUserName());

            Set<Roles> roles = new HashSet<>();
            for (String roleName : employee.getRoleNames()) {
                Roles role = roleRepo.findByRoleName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                roles.add(role);
            }
            existingEmployee.setRoles(roles);

            regRepo.save(existingEmployee);
            return "Employee updated successfully";
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
}
