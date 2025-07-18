import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function EmployeeList({ employees, onUpdate, onDelete }) {
  const [searchTerm, setSearchTerm] = useState("");
  const navigate = useNavigate();


  const filteredEmployees = employees.filter((emp) =>
    emp.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  
  const handleLogout = () => {
    localStorage.removeItem("token"); 
    alert("Logged out successfully");
    navigate("/login");
  };

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h2>Employee List</h2>
        <button className="btn btn-danger" onClick={handleLogout}>Logout</button>
      </div>

      <div className="mb-3">
        <input
          type="text"
          className="form-control"
          placeholder="Search by employee name..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Emp ID</th>
            <th>Name</th>
            <th>Actions</th>
            <th>Task Options</th>
          </tr>
        </thead>
        <tbody>
          {filteredEmployees.map((emp, index) => (
            <tr key={emp.empId}>
              <td>{emp.empId}</td>
              <td>{emp.name}</td>
              <td>
                <button
                  className="btn btn-warning btn-sm me-2"
                  onClick={() => onUpdate(index, emp)}
                >
                  Update
                </button>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => onDelete(index)}
                >
                  Delete
                </button>
              </td>
              <td>
                <Link
                  to={`/employee/${emp.empId}/create-task`}
                  className="btn btn-success btn-sm me-2"
                >
                  Create Task
                </Link>
                <Link
                  to={`/employee/${emp.empId}/view-tasks`}
                  className="btn btn-primary btn-sm"
                >
                  View Tasks
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default EmployeeList;
