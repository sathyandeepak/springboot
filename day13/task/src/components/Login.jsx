import { useState } from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom"

const Login = () =>{
    const [userName,setUserName] = useState("");
    const [password,setPassword] = useState("");
    const navigate = useNavigate();
    

    async function handleLogin(event){
        event.preventDefault();
        try{
            const token = await axios.post("http://localhost:3001/api/auth/login",{userName,password})
            console.log(token);
            alert("Login Sucessful")
            
            navigate('/employeeList');

        }catch(e){
            console.log("Login error:",e);
            alert("Invalid Credentials")
        }
        console.log("Form Submitted");
    }
  
    return (
    <div className="d-flex justify-content-center align-items-center" style={{ minHeight: '80vh' }}>
      <div className="card p-4 shadow" style={{ width: '100%', maxWidth: '400px' }}>
        <h2 className="text-center mb-4">Login</h2>
        <form onSubmit={handleLogin}>
          <div className="mb-3">
            <label htmlFor="userName" className="form-label">User Name</label>
            <input
              id="userName"
              name="userName"
              value={userName}
              type="text"
              className="form-control"
              onChange={e => setUserName(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input
              id="login-password"
              name="password"
              type="password"
              value={password}
              className="form-control"
              onChange={e => setPassword(e.target.value)}
            />
          </div>
          <button className="btn btn-dark w-100">Login</button>
        </form>
      </div>
    </div>
  );
};

export default Login;