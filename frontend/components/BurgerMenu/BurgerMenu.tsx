import React, { FC } from "react";
import s from "./BurgerMenu.module.scss";

import cn from "classnames";

interface HeaderProps {
  isHeaderActive: boolean;
  setIsHeaderActive: (active: boolean) => void;
  menuActive: boolean;
  setMenuActive: (active: boolean) => void;
}

const BurgerMenu: FC<HeaderProps> = ({
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
