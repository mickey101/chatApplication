import { Button, TextField } from "@material-ui/core";
import { Post } from "../../components/chat/Post";
import React, { useState, useEffect, useRef } from "react";
import { postMessage } from "../../components/chat/chatLogic";
import { useStyles } from "../../components/chat/chatStyles";

const chat = () => {
  const classes = useStyles();
  const [messages, setMessages] = useState([]);
  const [message, setMessage] = useState("");
  const [isValid, setIsValid] = useState(true);
  const [updateAvailable, setUpdateAvailable] = useState(false);
  const myRef = useRef(null);

  const scroll = () => myRef.current.scrollIntoView({ behavior: "smooth" });

  useEffect(() => {
    fetch(`http://localhost:8080/rooms/home`, {
      method: "GET",
    })
      .then((result) => {
        if (!result.ok) {
          throw new Error("Unable to fetch Message");
        }
        return result.text();
      })
      .then((data) => {
        data = JSON.parse(data);
        setMessages(data);
        scroll();
      })
      .catch((error) => {
        console.log("Error ", error);
      });
  }, [updateAvailable]);

  useEffect(() => {
    const evtSource = new EventSource("http://localhost:8080/events/home");

    evtSource.addEventListener("update", () => {
      // update the state to force message update
      setUpdateAvailable(!updateAvailable);
    });

    evtSource.onerror = function () {
      evtSource.close();
    };
    return () => {
      evtSource.removeEventListener("update", () => {
        evtSource.close();
      });
      evtSource.removeEventListener("error", () => {
        evtSource.close();
      });
    };
  }, [messages]);

  return (
    <>
      <div>
        <div className={classes.messages}>
          {messages.length &&
            messages.map((msg) => {
              return (
                <Post
                  key={msg.id}
                  message={msg.message}
                  userName={msg.userName}
                  date={msg.date}
                />
              );
            })}
        </div>
        <div ref={myRef} className={classes.input}>
          <TextField
            id="Message-entry"
            error={!isValid}
            label={"Message"}
            fullWidth={true}
            variant="standard"
            value={message}
            onChange={(event) => {
              setMessage(event.target.value);
            }}
            onKeyPress={(ev) => {
              if (ev.key === "Enter") {
                ev.preventDefault();
                if (ev.target.value !== "") {
                  ev.target.value = "";
                  postMessage({
                    setMessages,
                    setMessage,
                    setIsValid,
                    setUpdateAvailable,
                    updateAvailable,
                    message,
                    messages,
                  });
                }
              }
            }}
          />
          <Button
            onClick={() =>
              postMessage({
                setMessages,
                setMessage,
                setIsValid,
                setUpdateAvailable,
                updateAvailable,
                message,
                messages,
              })
            }
            disabled={message === ""}
            variant={"contained"}
            color={"primary"}
          >
            Send
          </Button>
        </div>
      </div>
    </>
  );
};
export default chat;
