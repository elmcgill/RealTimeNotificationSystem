import {useState, useEffect} from 'react';
import {over} from 'stompjs';
import SockJS from 'sockjs-client';
import { Login } from './components/Login/Login';
import { Notifications } from './components/Notifications/Notifications';

let stompClient = null;

function App() {

  const [user, setUser] = useState();
  const [connected, setConnected] = useState(false);
  const [notifications, setNotifications] = useState([]);

  const connect = () => {
    let sock = new SockJS('http://localhost:8001/ws');
    stompClient = over(sock);
    stompClient.connect({}, onConnected, onError);
    setConnected(true);
  }

  const onConnected = () => {
    stompClient.subscribe(`/notification/${user.userId}/post`, handleNotification);
  }

  const onError = (err) => {
    console.log(err);
  }

  const onUserLogin = (user) => {
    setUser(user);
  }

  const handleNotification = (payload) => {
    let payloadData = JSON.parse(payload.body);

    switch(payloadData){
      case "POST":
        let d = new Date().toUTCString();
        let notification = `New post posted at: ${d}`;
        notifications.push(notification);
        setNotifications([...notifications]);
        break;
    }
  }

  useEffect(() => {
    if(user && !connected){
      connect();
    }
    
  }, [user, notifications])

  return (
    <>
      {!user && <Login passUser={onUserLogin}/>}
      {user && 
        <div>
          <h1>Live Notification Feed</h1>
          {notifications.map((notification) => {
              return <p key={notification}>{notification}</p>
          })}
        </div>
      }
    </>
  )
}

export default App
