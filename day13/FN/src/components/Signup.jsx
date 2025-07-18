import { useState } from "react";
import axios from "axios";
import {useNavigate} from 'react-router-dom';
                                                              
const Signup = ({onSignup}) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userName, setUserName] = useState("");
  const [roleNames, setRoleNames] = useState([]);

  const navigate = useNavigate();

  const handleRoleChange = (e) =>{
    const role = e.target.value;
    if(e.target.checked){
      setRoleNames([...roleNames,role]);
    }
    else{
      setRoleNames(roleNames.filter(r=>r !== role));
    }
  };

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      const response = await axios.post("http://localhost:3001/api/auth/register", {
      name,                      
      email,
      password,
      userName,                  
      roleNames 
});

       if (onSignup){
            onSignup({ name: userName, email: `${userName}@gmail.com` ,password:password,userName:userName,roleNames:roleNames});
            }


navigate('/login');


      console.log(response.data);
      alert("Registration Successful");
    } catch (e) {
      console.log("Register error", e);
      alert("Register error!!");
    }
  }

  
  return (
    <div className="d-flex justify-content-center align-items-center" style={{ minHeight: '80vh' }}>
      <div className="card p-4 shadow" style={{ width: '100%', maxWidth: '500px' }}>
        <h2 className="text-center mb-4">Register</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">Name:</label>
            <input type="text" id="name" value={name} className="form-control" onChange={e => setName(e.target.value)} />
          </div>

          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email:</label>
            <input type="text" id="email" value={email} className="form-control" onChange={e => setEmail(e.target.value)} />
          </div>

          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password:</label>
            <input type="password" id="password" value={password} className="form-control" onChange={e => setPassword(e.target.value)} />
          </div>

          <div className="mb-3">
            <label htmlFor="userName" className="form-label">Username:</label>
            <input type="text" id="userName" value={userName} className="form-control" onChange={e => setUserName(e.target.value)} />
          </div>

          <div className="mb-3">
            <label className="form-label">Roles:</label><br />
            {["ADMIN", "USER", "ROLE_ADMIN", "ROLE_USER"].map(role => (
              <div className="form-check" key={role}>
                <input
                  type="checkbox"
                  className="form-check-input"
                  value={role}
                  id={role}
                  onChange={handleRoleChange}
                />
                <label htmlFor={role} className="form-check-label">{role}</label>
              </div>
            ))}
          </div>

          <button type="submit" className="btn btn-dark w-100">Register</button>
        </form>
      </div>
    </div>
  );
};

export default Signup;