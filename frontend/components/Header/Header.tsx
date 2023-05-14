import React, { FC, useEffect, useRef, useState } from "react";
import s from "./Header.module.scss";
import cn from "classnames";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGlobe, faGraduationCap } from "@fortawesome/free-solid-svg-icons";

interface Line {
  width: number;
  left: number;
}

const Header: FC = () => {
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
  const blockRefFive = useRef<HTMLLIElement>(null);

  // Жизненный цикл изменяет цвет и позицию линии
  useEffect(() => {
    const handleScroll = (): void => {
      if (window.scrollY === 0) {
        setIsHeaderActive(false);
      }
      if (window.scrollY > 1) {
        setIsHeaderActive(!false);
        setNavColor(1);
        setNavBarPosition({
          width: blockRefFirst.current!.offsetWidth,
          left: blockRefFirst.current!.offsetLeft,
        });
      }
      if (window.scrollY >= 630) {
        setNavColor(2);
        setNavBarPosition({
          width: blockRefSecond.current!.offsetWidth,
          left: blockRefSecond.current!.offsetLeft,
        });
      }
      if (window.scrollY >= 1600) {
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
      if (window.scrollY >= 5400) {
        setNavColor(5);
        setNavBarPosition({
          width: blockRefFive.current!.offsetWidth,
          left: blockRefFive.current!.offsetLeft,
        });
      }
    };
    window.addEventListener("scroll", handleScroll);
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <header
      className={isHeaderActive ? cn(s.header, s.headerActive) : s.header}
    >
      <div className={s.header__content}>
        <FontAwesomeIcon icon={faGraduationCap} className={s.header__logo} />
        <nav className={s.header__nav}>
          <ul className={s.header__list}>
            <li ref={blockRefFirst}>
              <a
                href="#"
                style={
                  navColor === 1 ? { color: "#03d665" } : { color: "#322f55" }
                }
              >
                Программы
              </a>
            </li>
            <li ref={blockRefSecond}>
              <a
                href="#"
                style={
                  navColor === 2 ? { color: "#03d665" } : { color: "#322f55" }
                }
              >
                Курсы
              </a>
            </li>
            <li ref={blockRefThree}>
              <a
                href="#"
                style={
                  navColor === 3 ? { color: "#03d665" } : { color: "#322f55" }
                }
              >
                Блог
              </a>
            </li>
            <li ref={blockRefFour}>
              <a
                href="#"
                style={
                  navColor === 4 ? { color: "#03d665" } : { color: "#322f55" }
                }
              >
                Контакты
              </a>
            </li>
            <li ref={blockRefFive}>
              <a
                href="#"
                style={
                  navColor === 5 ? { color: "#03d665" } : { color: "#322f55" }
                }
              >
                Вопросы
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
          <button><FontAwesomeIcon icon={faGlobe} /></button>
        </div>
      </div>
    </header>
  );
};

export default Header;
