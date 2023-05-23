import React, { FC } from "react";
import s from "./Menu.module.scss";

import cn from "classnames";

interface IMenuProps {
  menuActive: boolean;
  setMenuActive: (active: boolean) => void;
}

const Menu: FC<IMenuProps> = ({ menuActive, setMenuActive }) => {
  return (
    <div
      className={menuActive ? cn(s.menu, s.active) : s.menu}
      onClick={() => setMenuActive(!menuActive)}
    >
      <div className={s.blur}></div>

      <div className={s.menu__content} onClick={(e) => e.stopPropagation()}>
        <header className={s.menu__header}>Menu</header>
        <ul className={s.menu__list}>
          <li>
            <a href="#">Главная</a>
          </li>
          <li>
            <a href="#">Курсы</a>
          </li>
          <li>
            <a href="#">Рекомендации</a>
          </li>
          <li>
            <a href="#">Контакты</a>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Menu;
