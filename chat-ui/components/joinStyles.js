import { makeStyles } from "@material-ui/core";

export const useStyles = makeStyles((theme) => ({
  root: {
    position: "absolute",
    padding: "0",
    margin: "0",
    top: "0",
    left: "0",
    width: "100%",
    height: "100%",
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
  },
  container: {
    backgroundColor: "#cfe8fc",
    width: "50%",
    height: "50%",
    borderRadius: "25px",
    boxShadow: "0 0 15px #00214B",
    display: "flex",
    flexDirection: "column",
    justifyContent: "space-around",
  },
  div: {
    paddingLeft: "6px",
    paddingBottom: "3px",
    // border: "2px red solid",
    display: "flex",
    flexDirection: "column",
    justifyContent: "space-evenly",
  },
  textField: {
    padding: "8px 0",
  },
  header: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "center",
    marginTop: "1.5rem",
    marginBottom: "1.5rem",
  },
}));
