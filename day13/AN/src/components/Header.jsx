import { Link } from 'react-router-dom';

const Header = () => (
  <header className="bg-dark text-white p-3 shadow-sm">
    <div className="container d-flex justify-content-between align-items-center">
      <h2 className="m-0">Employee Management System</h2>
      <nav>
        <Link to="/signup" className="btn btn-outline-light me-2">Signup</Link>
        <Link to="/login" className="btn btn-outline-light me-2">Login</Link>
        <Link to="/employeeList" className="btn btn-outline-light">Employees</Link>
      </nav>
    </div>
  </header>
);

export default Header;
