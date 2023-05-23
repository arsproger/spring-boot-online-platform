import React, { FC } from "react";
import s from "./BurgerMenu.module.scss";

import cn from "classnames";

interface IBurgerMenu {
  isHeaderActive: boolean;
  setIsHeaderActive: (active: boolean) => void;
  menuActive: boolean;
  setMenuActive: (active: boolean) => void;
}

const BurgerMenu: FC<IBurgerMenu> = ({
  isHeaderActive,
  setIsHeaderActive,
  menuActive,
  setMenuActive,
}) => {
  const handleClick = () => {
    setIsHeaderActive(!isHeaderActive);
    setMenuActive(!menuActive);
  };

  return (
    <div className={s.container} onClick={handleClick}>
      <div className={menuActive ? cn(s.burgerMenu, s.active) : s.burgerMenu}>
        <span></span>
      </div>
    </div>
  );
};

export default BurgerMenu;
