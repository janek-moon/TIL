const { json } = require("body-parser");
const express = require("express");

const app = express();

app.get("/", (req, res) => {
  res.send(`
    <h1>Hello from this NodeJS app!</h1>
    <h2>This is New!</h2>
    <p>Try sending a request to /error and see what happens</p>
  `);
});

app.get("/hello", (req, res) => {
  res.json({ message: "Hello from this NodeJS app!" });
});

app.get("/error", (req, res) => {
  process.exit(1);
});

app.listen(7070);
