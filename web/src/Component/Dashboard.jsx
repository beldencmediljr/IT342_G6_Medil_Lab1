import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
    const [user, setUser] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const loggedInUser = localStorage.getItem('user');
        if (!loggedInUser) {
            navigate('/login');
        } else {
            setUser(loggedInUser.replace(/"/g, ''));
        }
    }, [navigate]);

    const handleLogout = () => {
        localStorage.removeItem('user');
        navigate('/login');
    };

    return (
        <div className="dashboard-container">
            <h2>Dashboard</h2>
            <h3 style={{ margin: '20px 0', color: '#555' }}>Welcome, {user}!</h3>
            <p>You have successfully logged in to the secure area.</p>
            <button onClick={handleLogout} className="btn-logout">Logout</button>
        </div>
    );
};

export default Dashboard;