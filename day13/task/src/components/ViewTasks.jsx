import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

function ViewTasks() {
  const { empId } = useParams();
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await axios.get(`http://localhost:3001/employee/${empId}/tasks`);
        setTasks(response.data);
      } catch (error) {
        console.error("Error fetching tasks:", error);
        alert("Failed to fetch tasks.");
      }
    };

    fetchTasks();
  }, [empId]);

  return (
    <div className="container mt-4">
      <h2>Tasks for Employee #{empId}</h2>
      {tasks.length === 0 ? (
        <p>No tasks assigned.</p>
      ) : (
        <ul className="list-group">
          {tasks.map((task, index) => (
            <li key={index} className="list-group-item">
              <strong>{task.taskName}</strong>
              <p>{task.description}</p>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default ViewTasks;
