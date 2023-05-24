import React, { FC } from "react";
import s from "./CategoriesList.module.scss";

import Link from "next/link";
import Image from "next/image";
import design from "../../public/design.png";

export const categories = [
  {
    id: 1,
    name: "Дизайн",
    image: design,
  },
  {
    id: 2,
    name: "Разработка",
    image: design,
  },
  {
    id: 3,
    name: "Маркетинг",
    image: design,
  },
  {
    id: 4,
    name: "ИТ и ПО",
    image: design,
  },
  {
    id: 5,
    name: "Личностный рост",
    image: design,
  },
  {
    id: 6,
    name: "Бизнес",
    image: design,
  },
  {
    id: 7,
    name: "Фотография",
    image: design,
  },
  {
    id: 8,
    name: "Музыка",
    image: design,
  },
];

const Categories: FC = () => {
  return (
    <section className={s.categories} id="categories">
      <h2>Популярные категории</h2>
      <div className={s.categories__list}>
        {categories.map((category) => {
          return (
            <Link
              className={s.categories__item}
              href={`/categories/${category.id}`}
              key={category.id}
            >
              <Image src={category.image} alt="categories image" />
              <h2>{category.name}</h2>
            </Link>
          );
        })}
      </div>
    </section>
  );
};

export default Categories;
