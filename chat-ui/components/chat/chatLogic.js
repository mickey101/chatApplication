import Router from "next/router";

export const postMessage = ({
  setMessages,
  setMessage,
  setIsValid,
  setUpdateAvailable,
  updateAvailable,
  message,
  messages,
}) => {
  if (typeof window !== undefined) {
    let userName = window.localStorage.getItem("user");
    if (userName == null || userName === "") {
      Router.push("/join");
    } else {
      fetch(`http://localhost:8080/rooms/message`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          roomName: "home",
          userName: userName,
          message: message,
        }),
      })
        .then((result) => {
          if (!result.ok) {
            throw new Error("Unable to send Message");
          }
          return result.text();
        })
        .then((data) => {
          data = JSON.parse(data);
          let updatedMessage = [...messages];
          updatedMessage.push(data);
          setMessages(updatedMessage);
          setMessage("");
          setIsValid(true);
          setUpdateAvailable(!updateAvailable);
        })
        .catch((error) => {
          console.log("Error ", error);
          setIsValid(false);
        });
    }
  }
};
