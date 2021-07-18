import { makeStyles } from "@material-ui/core";

export const useStyles = makeStyles((theme) => ({
  messages: {
    overflowY: "scroll",
    overFlowX: "hidden",
    padding: "1rem",
  },
  input: {
    paddingLeft: "6px",
    paddingBottom: "3px",
    border: "2px grey solid",
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-around",
  },
  box: {
    width: "100%",
    height: "100%",
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
  },
  chat: {
    width: "100%",
    height: "100%",
  },
}));
