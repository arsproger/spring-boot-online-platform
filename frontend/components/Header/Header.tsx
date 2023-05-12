import React, { FC } from "react";
import s from "./Header.module.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGraduationCap, faPhone } from "@fortawesome/free-solid-svg-icons";
import { faInstagram, faTelegram } from "@fortawesome/free-brands-svg-icons";

const Header: FC = () => {
  return (
    <header className={s.header}>
      <div className={s.aboveHeader}>
        <span>Все меню</span>

        <div className={s.contacts}>
          <span>+996707777777</span>
          <FontAwesomeIcon icon={faInstagram} className={s.social}/>
          <FontAwesomeIcon icon={faPhone} className={s.social}/>
          <FontAwesomeIcon icon={faTelegram} className={s.social}/>
        </div>
      </div>
      <div className={s.header__content}>
        <FontAwesomeIcon icon={faGraduationCap} className={s.logo} />
        <nav className={s.header__nav}>
          <ul className={s.header__list}>
            <li>
              <a href="#">Программы</a>
            </li>
            <li>
              <a href="#">Курсы</a>
            </li>
            <li>
              <a href="#">Блог</a>
            </li>
            <li>
              <a href="#">Контакты</a>
            </li>
          </ul>
        </nav>

        <div className={s.header__buttons}>
          <button>Eng</button>
          <button>Kgz</button>
          <button>Rus</button>
        </div>
      </div>
    </header>
  );
};

export default Header;
