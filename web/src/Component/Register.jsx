import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';

const Register = () => {
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: ''
    });
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8080/api/auth/register', formData);
            alert('Registration Successful! Please Login.');
            navigate('/login');
        } catch (error) {
            console.error(error);
            alert('Registration Failed: ' + (error.response?.data || "Backend not running"));
        }
    };

    return (
        <div className="auth-card">
            <h2>Create Account</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <input
                        type="text"
                        name="username"
                        placeholder="Username"
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <input
                        type="email"
                        name="email"
                        placeholder="Email Address"
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit" className="btn-primary">Sign Up</button>
            </form>
            <p className="auth-link">
                Already have an account? <Link to="/login">Login here</Link>
            </p>
        </div>
    );
};

export default Register;