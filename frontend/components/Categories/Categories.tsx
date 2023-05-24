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
          <h2>Дизайн</h2>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
        <Image src={design} alt="Categories image"/>
          <h2>Разработка</h2>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h2>Маркетинг</h2>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h2>ИТ и ПО</h2>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h2>Личностный рост</h2>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h2>Бизнес</h2>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h2>Фотография</h2>
        </Link>

        <Link className={s.categories__item} href={"/design/design"}>
          <Image src={design} alt="Categories image"/>
          <h2>Музыка</h2>
        </Link>
      </div>
    </section>
  );
};

export default Categories;
