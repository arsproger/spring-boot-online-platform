import React, { FC, PropsWithChildren, useState } from "react";

import Header from "../Header/Header";
import Menu from "../Menu/Menu";
import Footer from "../Footer/Footer";

const Layout: FC<PropsWithChildren> = ({ children }) => {
  // Состояние меню
  const [menuActive, setMenuActive] = useState<boolean>(false);

  return (
    <>
      <Header menuActive={menuActive} setMenuActive={setMenuActive}/>
      <Menu menuActive={menuActive} setMenuActive={setMenuActive}/>
      {children}
      <Footer />
    </>
  );
};

export default Layout;
