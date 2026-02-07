import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Register from './Component/Register';
import Login from './Component/Login';
import Dashboard from './Component/Dashboard';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        {/* Navigation Bar */}
        <nav className="navbar">
          <Link to="/" className="nav-brand">MyApp</Link>
          <div className="nav-links">
            <Link to="/register">Register</Link>
            <Link to="/login">Login</Link>
          </div>
        </nav>

        {/* Main Content Area */}
        <div className="main-content">
          <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<Login />} />
            <Route path="/dashboard" element={<Dashboard />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;