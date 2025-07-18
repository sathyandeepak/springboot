import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const EmployeeList = ({ employees = [], onUpdate, onDelete }) => {
  const [editIndex, setEditIndex] = useState(-1);
  const [editData, setEditData] = useState({
    name: "",
    email: "",
    password: "",
    userName: "",
    roleNames: ""
  });

  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    sessionStorage.clear();
    alert("Logged out successfully!");
    navigate("/login");
  };

  const startEditing = (index, employee) => {
    setEditIndex(index);
    setEditData({
      name: employee.name,
      email: employee.email,
      password: employee.password,
      userName: employee.userName,
      roleNames: employee.roleNames ? employee.roleNames.join(", ") : ""
    });
  };

  const handleChange = (e) => {
    setEditData({ ...editData, [e.target.name]: e.target.value });
  };

  const saveEdit = () => {
    const updatedEmployee = {
      ...employees[editIndex],
      ...editData,
      roleNames: editData.roleNames.split(",").map((r) => r.trim())
    };
    onUpdate(editIndex, updatedEmployee);
    setEditIndex(-1);
  };

  return (
    <div style={{ textAlign: "center" }}>
      <div style={{ textAlign: "right", marginRight: "20px", marginBottom: "10px" }}>
        <button
          onClick={handleLogout}
          style={{ backgroundColor: "#333", color: "white", padding: "6px 12px", border: "none", cursor: "pointer" }}
        >
          Logout
        </button>
      </div>

      <h2>Employee Details</h2>
      <table border="1" style={{ margin: "0 auto" }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Username</th>
            <th>Roles</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {employees.length === 0 ? (
            <tr>
              <td colSpan="7">No employees yet.</td>
            </tr>
          ) : (
            employees.map((employee, index) => (
              <tr key={index}>
                <td>{employee.empId}</td>
                <td>
                  {editIndex === index ? (
                    <input
                      name="name"
                      value={editData.name}
                      onChange={handleChange}
                    />
                  ) : (
                    <Link to={`/employee/${employee.empId}/tasks`}>
                      {employee.name}
                    </Link>
                  )}
                </td>
                <td>
                  {editIndex === index ? (
                    <input
                      name="email"
                      value={editData.email}
                      onChange={handleChange}
                    />
                  ) : (
                    employee.email
                  )}
                </td>
                <td>
                  {editIndex === index ? (
                    <input
                      name="password"
                      value={editData.password}
                      onChange={handleChange}
                    />
                  ) : (
                    employee.password
                  )}
                </td>
                <td>
                  {editIndex === index ? (
                    <input
                      name="userName"
                      value={editData.userName}
                      onChange={handleChange}
                    />
                  ) : (
                    employee.userName
                  )}
                </td>
                <td>
                  {editIndex === index ? (
                    <input
                      name="roleNames"
                      value={editData.roleNames}
                      onChange={handleChange}
                      placeholder="Comma separated"
                    />
                  ) : (
                    employee.roleNames?.join(", ")
                  )}
                </td>
                <td>
                  {editIndex === index ? (
                    <>
                      <button
                        onClick={saveEdit}
                        style={{ backgroundColor: "green", color: "white", marginRight: "5px" }}
                      >
                        Save
                      </button>
                      <button onClick={() => setEditIndex(-1)}>Cancel</button>
                    </>
                  ) : (
                    <>
                      <button
                        onClick={() => startEditing(index, employee)}
                        style={{ backgroundColor: "green", color: "white", marginRight: "5px" }}
                      >
                        Update
                      </button>
                      <button
                        onClick={() => onDelete(index)}
                        style={{ backgroundColor: "red", color: "white" }}
                      >
                        Delete
                      </button>
                    </>
                  )}
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default EmployeeList;
