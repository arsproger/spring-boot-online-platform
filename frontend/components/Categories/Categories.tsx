import React, { FC } from "react";
import s from "./Categories.module.scss";
import Link from "next/link";

const Categories: FC = () => {
  return (
    <div className={s.categories} id="categories">
      <h1 className={s.categories__title}>Популярные категории</h1>
      <div className={s.categories__list}>
        <Link className={s.categories__item} href={"/design/design"}>
          <img src="./design.png" alt="Categories image" />
          <h1>Дизайн</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <img src="./design.png" alt="Categories image" />
          <h1>Разработка</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <img src="./design.png" alt="Categories image" />
          <h1>Маркетинг</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <img src="./design.png" alt="Categories image" />
          <h1>ИТ и ПО</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <img src="./design.png" alt="Categories image" />
          <h1>Личностный рост</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <img src="./design.png" alt="Categories image" />
          <h1>Бизнес</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <img src="./design.png" alt="Categories image" />
          <h1>Фотография</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <img src="./design.png" alt="Categories image" />
          <h1>Музыка</h1>
        </Link>
      </div>
    </div>
  );
};

export default Categories;
