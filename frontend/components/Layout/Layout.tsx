import React, { FC, PropsWithChildren } from "react";
import Header from "../Header/Header";
import Footer from "../Footer/Footer";

const Layout: FC<PropsWithChildren> = ({ children }) => {
  return (
    <>
      <Header />
      {children}
      <Footer />
    </>
  );
};

export default Layout;
