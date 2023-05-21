import React, { FC } from "react";
import s from "./Categories.module.scss";

import Link from "next/link";
import Image from "next/image";
import design from '../../public/design.png'

const Categories: FC = () => {
  return (
    <section className={s.categories} id="categories">
      <h2 >Популярные категории</h2>
      <div className={s.categories__list}>
        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h1>Дизайн</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
        <Image src={design} alt="Categories image"/>
          <h1>Разработка</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h1>Маркетинг</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h1>ИТ и ПО</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h1>Личностный рост</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h1>Бизнес</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h1>Фотография</h1>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h1>Музыка</h1>
        </Link>
      </div>
    </section>
  );
};

export default Categories;
