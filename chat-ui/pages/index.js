import React from "react";
import { Join } from "../components/join";
import Head from "next/head";

export default function Home() {
  return (
    <div className="container">
      <Head>
        <title>Chat Application</title>
        <link rel="icon" href="/chat.svg" />
      </Head>
      <>
        <Join />
      </>
    </div>
  );
}
