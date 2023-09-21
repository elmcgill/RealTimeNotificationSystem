import React, {useState} from 'react'
import axios from 'axios';

export const Login = ({passUser}) => {

    const [loginInfo, setLoginInfo] = useState({
        email: '',
        password: ''
    });

    const handleChange = (e) => {
        setLoginInfo({
            ...loginInfo,
            [e.target.name]: e.target.value
        })
    }

    const handleLogin = async (e) => {
        e.preventDefault();
        let res = await axios.post("http://localhost:8000/api/v1/users/login", loginInfo);
        console.log(res.data);
        passUser(res.data);
    }

    return (
        <div>
            <h1>Login To Recieve Notifications</h1>
            <form>
                <h2>Enter your email...</h2>
                <input name="email" placeholder="email" value={loginInfo.email} onChange={handleChange}/>
                <h2>Enter your password...</h2>
                <input name="password" placeholder="password" value={loginInfo.password} onChange={handleChange} />
            </form>
            <button onClick={handleLogin}>Login</button>
        </div>
  )
}
