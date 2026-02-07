import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';

const Login = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', formData);
            if (response.status === 200) {
                localStorage.setItem('user', JSON.stringify(formData.username));
                alert('Login Successful!');
                navigate('/dashboard');
            }
        } catch (error) {
            console.error(error);
            alert('Login Failed: ' + (error.response?.data || "Backend not running"));
        }
    };

    return (
        <div className="auth-card">
            <h2>Welcome Back</h2>
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
                        type="password"
                        name="password"
                        placeholder="Password"
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit" className="btn-primary">Login</button>
            </form>
            <p className="auth-link">
                Don't have an account? <Link to="/register">Register here</Link>
            </p>
        </div>
    );
};

export default Login;