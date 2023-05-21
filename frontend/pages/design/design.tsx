import React, { FC, useEffect, useState } from "react";
import s from "./design.module.scss";

import Image from "next/image";
import Link from "next/link";
import { cards, ICards } from "../../constants/cardData";

import Rating from "@/components/Rating/Rating";
import MyButton from "@/components/MUI/MyButton/MyButton";

const Design: FC = () => {
  const [cardData, setCardData] = useState<ICards[]>(cards);

  const [rating, setRating] = useState<number>(0);

  const handleChange = (newRating: number) => {
    setRating(newRating);
  };

  const handleClick = (id: number) => {
    setCardData(
      cardData.map((card) =>
        card.id === id ? { ...card, rating: rating } : card
      )
    );
  };

  return (
    <section className={s.card}>
      <h2 className={s.pageTitle}>Все курсы по теме "Дизайн"</h2>

      <header className={s.card__header}>
        <div className={s.filteredButton}>
          {/* <MyButton type={}></MyButton> */}
          <button type="button">Фильтировать</button>

          <select>
            <option value="rating">С наивысшим рейтингом</option>
            <option value="price">По цене</option>
          </select>
        </div>

        <span>{cardData.length} результата</span>
      </header>

      <ul className={s.card__list}>
        {cardData.map((card) => (
          <li className={s.card__item} key={card.id}>
            <Link href="" className={s.card__link}>
              <div className={s.card__content}>
                <div className={s.card__image}>
                  <Image
                    src={card.poster}
                    alt="Card image"
                    width={300}
                    height={200}
                  />

                  <div className={s.blackout}>
                    <span>Нажмите</span>
                  </div>
                </div>

                <ul className={s.content__list}>
                  <li>
                    <h3>{card.title}</h3>
                  </li>
                  <li>
                    <p className={s.card__desciption}>{card.desciption}</p>
                  </li>
                  <li>
                    <p className={s.card__creator}>{card.creator}</p>
                  </li>
                  <li>
                    <pre>{card.rating} </pre>
                    <span onClick={() => handleClick(card.id)}>
                      <Rating value={card.rating} onChange={handleChange} />
                    </span>
                  </li>
                  <li>
                    <p className={s.card__size}>{card.size}</p>
                  </li>
                </ul>
              </div>

              <span>{card.price} $</span>
            </Link>
          </li>
        ))}
      </ul>
    </section>
  );
};

export default Design;
