import React, { FC } from "react";
import s from "./Hero.module.scss";

import { useRouter } from "next/router";

import en from "../../locales/EN/translation.json";
import ru from "../../locales/RU/translation.json";

const Hero: FC = () => {
  const { locale } = useRouter();

  // Функции - для смены текста
  const t = locale === "ru" ? ru : en;

  return (
    <section className={s.hero}>
      <div className={s.hero__title}>
        <div className={s.hero__title_container}>
          <h1>{t.title}</h1>
        </div>

        <div className={s.hero__subTitle_container}>
          <p>{t.subTitle}</p>
        </div>
      </div>

      <div className={s.sky}>
        <div className={s.sun}>
          <img
            src="https://assets.website-files.com/5de973c9519095863a2344df/5e3448b93087e0c52585ec62_sun.svg"
            alt="sun image"
            className={s.sunAnimated}
          />
        </div>

        <div className={s.x1}>
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
        </div>

        <div className={s.x5}>
          <div className={s.cloud}></div>
        </div>
      </div>
    </section>
  );
};

export default Hero;
