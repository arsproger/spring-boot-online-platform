import React, { FC, useEffect, useRef, useState } from "react";
import s from "./Header.module.scss";
import cn from "classnames";
import Link from "next/link";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGlobe, faGraduationCap } from "@fortawesome/free-solid-svg-icons";

import MyButton from "../MUI/MyButton/MyButton";
import BurgerMenu from "../BurgerMenu/BurgerMenu";

interface HeaderProps {
  menuActive: boolean;
  setMenuActive: (active: boolean) => void;
}

interface Line {
  width: number;
  left: number;
}

const Header: FC<HeaderProps> = ({ menuActive, setMenuActive }) => {
  // Состояние - для header (для цвета)
  const [isHeaderActive, setIsHeaderActive] = useState<boolean>(false);

  // Состояние - для navbar (для линии)
  const [navBarPosition, setNavBarPosition] = useState<Line>({
    width: 0,
    left: 0,
  });

  // Состояние - для изменение цвета навигации
  const [navColor, setNavColor] = useState<number>(0);

  // С помощью useRef получаем размер и позицию элемента
  const blockRefFirst = useRef<HTMLLIElement>(null);
  const blockRefSecond = useRef<HTMLLIElement>(null);
  const blockRefThree = useRef<HTMLLIElement>(null);
  const blockRefFour = useRef<HTMLLIElement>(null);

  // Жизненный цикл изменяет цвет и позицию линии
  useEffect(() => {
    const handleScroll = (): void => {
      if (window.scrollY === 0) {
        if (!menuActive) {
          setIsHeaderActive(false);
        }
      }

      if (window.scrollY > 1) {
        setIsHeaderActive(!false);
        setNavColor(1);
        setNavBarPosition({
          width: blockRefFirst.current!.offsetWidth,
          left: blockRefFirst.current!.offsetLeft,
        });
      }

      if (window.scrollY >= 600) {
        setNavColor(2);
        setNavBarPosition({
          width: blockRefSecond.current!.offsetWidth,
          left: blockRefSecond.current!.offsetLeft,
        });
      }

      if (window.scrollY >= 1100) {
        setNavColor(3);
        setNavBarPosition({
          width: blockRefThree.current!.offsetWidth,
          left: blockRefThree.current!.offsetLeft,
        });
      }

      if (window.scrollY >= 2600) {
        setNavColor(4);
        setNavBarPosition({
          width: blockRefFour.current!.offsetWidth,
          left: blockRefFour.current!.offsetLeft,
        });
      }
    };
    window.addEventListener("scroll", handleScroll);
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, [menuActive]);

  return (
    <header
      className={isHeaderActive ? cn(s.header, s.headerActive) : s.header}
    >
      <div className={s.header__content}>
        <Link href={"/"}>
          <FontAwesomeIcon icon={faGraduationCap} className={s.header__logo} />
        </Link>

        <nav className={s.header__nav}>
          <ul className={s.header__list}>
            <li ref={blockRefFirst}>
              <a
                href="#"
                style={
                  navColor === 1 ? { color: "#03d665" } : { color: "#322f55" }
                }
              >
                Главная
              </a>
            </li>
            <li ref={blockRefSecond}>
              <a
                href="#categories"
                style={
                  navColor === 2 ? { color: "#03d665" } : { color: "#322f55" }
                }
              >
                Курсы
              </a>
            </li>
            <li ref={blockRefThree}>
              <a
                href="#recommendations"
                style={
                  navColor === 3 ? { color: "#03d665" } : { color: "#322f55" }
                }
              >
                Рекомендации
              </a>
            </li>
            <li ref={blockRefFour}>
              <a
                href="#contacts"
                style={
                  navColor === 4 ? { color: "#03d665" } : { color: "#322f55" }
                }
              >
                Контакты
              </a>
            </li>
            <span
              className={s.animateLine}
              style={{
                left: navBarPosition.left,
                width: navBarPosition.width,
              }}
            ></span>
          </ul>
        </nav>

        <div className={s.header__buttons}>
          <Link href="/signUp/signUp" className={s.header__signButton}>
            <MyButton>Регистрация</MyButton>
          </Link>

          <div className={s.header__languageButton}>
            <button>
              <FontAwesomeIcon icon={faGlobe} />
            </button>
          </div>

          <BurgerMenu
            isHeaderActive={isHeaderActive}
            setIsHeaderActive={setIsHeaderActive}
            menuActive={menuActive}
            setMenuActive={setMenuActive}
          />
        </div>
      </div>
    </header>
  );
};

export default Header;
