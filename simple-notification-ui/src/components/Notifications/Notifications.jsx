import React, {useEffect} from 'react'

export const Notifications = ({notifications}) => {

    useEffect(() => {
        console.log("Notification", notifications);
    }, [notifications])

  return (
    <div>
        <h1>Live Notification Feed</h1>
        {notifications.map((notification) => {
            return <p key={notification}>{notification}</p>
        })}
    </div>
  )
}
