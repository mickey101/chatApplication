import {
  Avatar,
  CardHeader,
  Typography,
  Card,
  makeStyles,
} from "@material-ui/core";
import React from "react";

const useStyles = makeStyles((themes) => ({
  card: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "flex-start",
    margin: "1rem",
    backgroundColor: "linen",
    width: "95%",
    overflowWrap: "anywhere",
    overflow: "visible",
    borderRadius: "0 25px",
  },
  header: {
    alignItems: "flex-start",
    padding: "0px",
  },
  content: {
    display: "flex",
    flexDirection: "column",
  },
  avatar: {
    backgroundColor: "#f56c6c",
  },
  message: {
    backgroundColor: "#f5f5f5",
    border: "2px #fbf5f5 dashed",
    borderRadius: "10px",
    paddingLeft: "2px",
    margin: "0px",
  },
  date: {
    padding: "4px 1.5rem 4px 4px",
    display: "flex",
    flexDirection: "row",
    justifyContent: "flex-start",
    color: "#9a9a9a",
  },
  name: {
    color: "#9a9a9a",
  },
  para: {
    margin: "0px",
    marginTop: "3px",
    marginRight: "10px",
  },
}));

export const Post = ({ userName: username, message: msg, date: date }) => {
  const classes = useStyles();

  return (
    <Card className={classes.card}>
      <CardHeader
        className={classes.header}
        avatar={
          <Avatar
            className={classes.avatar}
            aria-label="recipe"
            variant="rounded"
          >
            {username != null ? username[0].toUpperCase() : "R"}
          </Avatar>
        }
      />
      <div className={classes.content}>
        <div>
          <div className={classes.name}>{username}</div>
        </div>
        <p className={classes.para}>{msg}</p>
        <div className={classes.date}>
          <Typography variant="overline" component="p">
            {date.substring(0, 10) + " " + date.substring(12, 16)}
          </Typography>
        </div>
      </div>
    </Card>
  );
};
