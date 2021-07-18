import React, { useState } from "react";
import { TextField, Typography, Button, Container } from "@material-ui/core";
import { login } from "./joinLogic";
import { useStyles } from "./joinStyles";

export const Join = () => {
  const classes = useStyles();
  const [userName, setUserName] = useState("");
  const [isValid, setIsValid] = useState(true);

  return (
    <div className={classes.root}>
      <Container className={classes.container} maxWidth="xl">
        <div className={classes.header}>
          <Typography variant="h6"> Join the chat!</Typography>
        </div>
        <div className={classes.div}>
          <TextField
            className={classes.textField}
            id="username-entry"
            error={!isValid}
            label={"UserName"}
            helperText={isValid ? "" : "Username taken!"}
            variant="outlined"
            onChange={(event) => {
              setUserName(event.target.value);
            }}
            onKeyPress={(ev) => {
              if (ev.key === "Enter") {
                ev.preventDefault();
                ev.target.value = "";
                login({ setIsValid, userName });
              }
            }}
          />
          <Button
            onClick={() => login({ setIsValid, userName })}
            disabled={userName === ""}
            variant={"contained"}
            color={"primary"}
          >
            Join
          </Button>
        </div>
      </Container>
    </div>
  );
};
