import React, { FC, useEffect, useRef, useState } from "react";
import s from "./Header.module.scss";

import cn from "classnames";
import Link from "next/link";
import { useRouter } from "next/router";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGlobe, faGraduationCap } from "@fortawesome/free-solid-svg-icons";

import MyButton from "../MUI/MyButton/MyButton";
import BurgerMenu from "../BurgerMenu/BurgerMenu";
import MyModal from "../MyModal/MyModal";

interface IHeaderProps {
  menuActive: boolean;
  setMenuActive: (active: boolean) => void;
}

interface ILine {
  width: number;
  left: number;
}

const Header: FC<IHeaderProps> = ({ menuActive, setMenuActive }) => {
  // Состояние - для модалки
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

  // Состояние - для header (для цвета)
  const [isHeaderActive, setIsHeaderActive] = useState<boolean>(false);

  // Состояние - для navbar (для линии)
  const [navBarPosition, setNavBarPosition] = useState<ILine>({
    width: 0,
    left: 0,
  });

  // Состояние - для изменение цвета навигации
  const [navColor, setNavColor] = useState<number>(0);

  // Чтобы получить информацию о текущем маршруте
  const { pathname } = useRouter();

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

      if (window.scrollY > 1 && blockRefFirst.current) {
        setIsHeaderActive(!false);
        setNavColor(1);
        setNavBarPosition({
          width: blockRefFirst.current.offsetWidth,
          left: blockRefFirst.current.offsetLeft,
        });
      }

      if (window.scrollY >= 600 && blockRefSecond.current) {
        setNavColor(2);
        setNavBarPosition({
          width: blockRefSecond.current.offsetWidth,
          left: blockRefSecond.current.offsetLeft,
        });
      }

      if (window.scrollY >= 1200 && blockRefThree.current) {
        setNavColor(3);
        setNavBarPosition({
          width: blockRefThree.current.offsetWidth,
          left: blockRefThree.current.offsetLeft,
        });
      }

      if (window.scrollY >= 1800 && blockRefFour.current) {
        setNavColor(4);
        setNavBarPosition({
          width: blockRefFour.current.offsetWidth,
          left: blockRefFour.current.offsetLeft,
        });
      }
    };
    window.addEventListener("scroll", handleScroll);
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, [menuActive]);

  return (
    <>
      <MyModal isModalOpen={isModalOpen} setIsModalOpen={setIsModalOpen} />

      <header
        className={isHeaderActive ? cn(s.header, s.headerActive) : s.header}
      >
        <nav className={s.header__nav}>
          <Link href={"/"}>
            <FontAwesomeIcon
              icon={faGraduationCap}
              className={s.header__logo}
            />
          </Link>

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

          <div className={s.header__buttons}>
            {pathname === "/signUp/signUp" ? (
              <Link href="/signIn/signIn" className={s.header__signButton}>
                <MyButton
                  background="#7329c2"
                  hoverBackground="#03d665"
                  type="primary"
                >
                  Войти
                </MyButton>
              </Link>
            ) : (
              <Link href="/signUp/signUp" className={s.header__signButton}>
                <MyButton
                  background="#7329c2"
                  hoverBackground="#03d665"
                  type="primary"
                >
                  Регистрация
                </MyButton>
              </Link>
            )}

            <div className={s.header__languageButton}>
              <MyButton
                background="#7329c2"
                hoverBackground="#03d665"
                onClick={() => setIsModalOpen(true)}
              >
                <FontAwesomeIcon icon={faGlobe} />
              </MyButton>
            </div>

            <BurgerMenu
              isHeaderActive={isHeaderActive}
              setIsHeaderActive={setIsHeaderActive}
              menuActive={menuActive}
              setMenuActive={setMenuActive}
            />
          </div>
        </nav>
      </header>
    </>
  );
};

export default Header;
