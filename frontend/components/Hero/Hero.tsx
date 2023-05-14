import React, { FC } from "react";
import s from "./Hero.module.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faInstagram, faTelegram } from "@fortawesome/free-brands-svg-icons";
import { faPhone } from "@fortawesome/free-solid-svg-icons";

const Hero: FC = () => {
  return (
    <section className={s.hero}>
      <header className={s.hero__header}>
        <span>Все меню</span>

        <div className={s.contacts}>
          <span>+996 707 777 777</span>
          <FontAwesomeIcon icon={faInstagram} className={s.social} />
          <FontAwesomeIcon icon={faPhone} className={s.social} />
          <FontAwesomeIcon icon={faTelegram} className={s.social} />
        </div>
      </header>

      <div className={s.hero__title}>
        <h1>Образовательная <br /> онлайн-платформа</h1>
        <p>
          Курсы и видео-лекции по дизайну, копирайтингу, <br /> SMM, таргету и многим
          другим направлениям
        </p>
      </div>

      <div className={s.sky}>
        <div className={s.sun}>
          <img
            src="https://assets.website-files.com/5de973c9519095863a2344df/5e3448b93087e0c52585ec62_sun.svg"
            alt="Sun image"
            className={s.sunAnimated}
          />
        </div>

        {/* <div className={s.x1}>
          <div className={s.cloud}></div>
        </div>

        <div className={s.x2}>
          <div className={s.cloud}></div>
        </div>

        <div className={s.x3}>
          <div className={s.cloud}></div>
        </div>

        <div className={s.x4}>
          <div className={s.cloud}></div>
        </div> */}
      </div>
    </section>
  );
};

export default Hero;
