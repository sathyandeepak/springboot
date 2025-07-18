import { Routes, Route, Link } from 'react-router-dom';
import { useState } from 'react';
import Signup from './components/Signup';
import Login from './components/Login';
import EmployeeList from './components/EmployeeList';
import Header from './components/Header'; 
import Footer from './components/Footer'; 
import CreateTask from './components/CreateTask';
import ViewTasks from './components/ViewTasks';
import axios from "axios";

function App() {
  const [employees, setEmployees] = useState([]);

  const addEmployee = (employee) => {
    setEmployees([...employees, employee]);
  };

  const updateEmployee = async (index, updatedEmployee) => {
    const employeeToUpdate = employees[index];

    try {
      await axios.put(`http://localhost:3001/employee/${employeeToUpdate.empId}`, updatedEmployee);
      const updated = [...employees];
      updated[index] = { ...employeeToUpdate, ...updatedEmployee };
      setEmployees(updated);
    } catch (error) {
      console.error("Error updating employee:", error);
      alert("Failed to update employee in database.");
    }
  };

  const deleteEmployee = async (index) => {
    const employeeToDelete = employees[index];

    try {
      await axios.delete(`http://localhost:3001/employee/${employeeToDelete.empId}`);
      const updated = employees.filter((_, i) => i !== index);
      setEmployees(updated);
    } catch (error) {
      console.error("Error deleting employee:", error);
      alert("Failed to delete employee from database.");
    }
  };

  return (
    <div>
      <Header />

      <main className="container my-5">
        <Routes>
          <Route path="/signup" element={<Signup onSignup={addEmployee} />} />
          <Route path="/login" element={<Login />} />
          {/* <Route path = "/addEmployee" element = {<AddEmployee />} /> */}
          <Route path="/employeeList" element={
            <EmployeeList
              employees={employees}
              onUpdate={updateEmployee}
              onDelete={deleteEmployee}
            />
          } />

        <Route path="/employee/:empId/create-task" element={<CreateTask />} />
        <Route path="/employee/:empId/view-tasks" element={<ViewTasks />} />

        </Routes>
      </main>
      <Footer />
            
    </div>
  );
}

export default App;
