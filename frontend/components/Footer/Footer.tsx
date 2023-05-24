import React, { FC } from "react";
import s from "./Footer.module.scss";

import { faGraduationCap, faPhone } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faInstagram, faTelegram } from "@fortawesome/free-brands-svg-icons";

const Footer: FC = () => {
  return (
    <footer className={s.footer} id="contacts">
      <div className={s.footer__content}>
        <div className={s.footer__contacts}>
          <FontAwesomeIcon icon={faGraduationCap} className={s.footer__logo} />
          <p>
            Образовательная онлайн-платформа для развития и тренировки навыков в
            сфере информационных технологий
          </p>

          <div className={s.footer__socialMedia}>
            <FontAwesomeIcon icon={faInstagram} className={s.footer__social} />
            <FontAwesomeIcon icon={faPhone} className={s.footer__social} />
            <FontAwesomeIcon icon={faTelegram} className={s.footer__social} />
          </div>
        </div>

        <ul className={s.footer__list}>
          <li>
            <a href="#" className={s.footer__title}>
              Обучение
            </a>
          </li>

          <li>
            <a href="#">Курсы</a>
          </li>

          <li>
            <a href="#">Вебинары</a>
          </li>

          <li>
            <a href="#">Тренажеры</a>
          </li>

          <li>
            <a href="#">Воркшопы</a>
          </li>
        </ul>

        <ul className={s.footer__list}>
          <li>
            <a href="#" className={s.footer__title}>
              О нас
            </a>
          </li>

          <li>
            <a href="#">О платформе</a>
          </li>

          <li>
            <a href="#">Преподаватели</a>
          </li>

          <li>
            <a href="#">Тарифы</a>
          </li>

          <li>
            <a href="#">Отзывы</a>
          </li>
        </ul>

        <ul className={s.footer__list}>
          <li>
            <a href="#" className={s.footer__title}>
              Контакты
            </a>
          </li>

          <li>
            <a href="#">Связаться с нами</a>
          </li>

          <li>
            <a href="#">Консультация</a>
          </li>

          <li>
            <a href="#">Реквизиты</a>
          </li>

          <li>
            <a href="#">Поддержка</a>
          </li>
        </ul>

        <ul className={s.footer__list}>
          <li>
            <a href="#" className={s.footer__title}>
              Возникли вопросы?
            </a>
          </li>

          <li>
            <a href="#">Напишите нам на почту test@gmail.com</a>
          </li>
        </ul>
      </div>

      <footer className={s.subFooter}>
        <FontAwesomeIcon icon={faGraduationCap} className={s.subFooter__logo} />

        <p>© 2023 Izatis, Inc.</p>
      </footer>
    </footer>
  );
};

export default Footer;
