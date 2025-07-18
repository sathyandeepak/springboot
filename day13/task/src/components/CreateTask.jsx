import { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

function CreateTask() {
  const { empId } = useParams();
  const [taskName, setTaskName] = useState("");
  const [description, setDescription] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post(`http://localhost:3001/employee/${empId}/tasks`, {
        taskName,
        description,
      });
      alert("Task created successfully!");
      navigate(`/employee/${empId}/view-tasks`);
    } catch (error) {
      console.error("Error creating task:", error);
      alert("Failed to create task");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Create Task for Employee #{empId}</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Task Name</label>
          <input
            type="text"
            className="form-control"
            value={taskName}
            onChange={(e) => setTaskName(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Description</label>
          <textarea
            className="form-control"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          ></textarea>
        </div>
        <button className="btn btn-dark">Create Task</button>
      </form>
    </div>
  );
}

export default CreateTask;
