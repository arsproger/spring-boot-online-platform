import React, { useEffect, useState } from "react";
import s from "./categories.module.scss";
import axios from "axios";

import Image from "next/image";
import Link from "next/link";
import { cards, ICards } from "../../constants/cardData";

import Rating from "@/components/Rating/Rating";
import MyButton from "@/components/MUI/MyButton/MyButton";
import MySelect from "@/components/MUI/MySelect/MySelect";
import { useRouter } from "next/router";
import { categories } from "@/components/CategoriesList/CategoriesList";

interface IData {
  name: string;
  description: string;
  price: number;
  language: string;
}

export default function () {
  // Состояние - для карточек
  const [cardsData, setCardsData] = useState<ICards[]>(cards);
  // Состояние - для объекта из массива categories
  const [category, setCategory] = useState<any>({});
  // Состояние - для рейтинга
  const [rating, setRating] = useState<number>(0);

  const { query } = useRouter();

  // Получает объект из массива categories
  useEffect(() => {
    if (!!query.id) {
      const category = categories.find(({ id }) => id === +query.id);
      setCategory(category);
    }
  }, []);

  // Функция - при каждом изменении рейтинга
  const handleChange = (newRating: number) => {
    setRating(newRating);
  };

  // Функция - при клике рейтинга
  const handleClick = (event: any, id: number) => {
    event.preventDefault();
    setCardsData(
      cardsData.map((card) =>
        card.id === id ? { ...card, rating: rating } : card
      )
    );
  };

  // Отправляем get запрос для карточек
  const getCard = async () => {
    const BASE_URL = "http://localhost:8080/course";
    try {
      const response = await axios.get(BASE_URL);

      setCardsData(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getCard();
  }, []);
  return (
    <section className={s.card}>
      <h2 className={s.pageTitle}>Все курсы по теме "{category.name}"</h2>

      <header className={s.card__header}>
        <div className={s.filteredButton}>
          <MyButton>Фильтировать</MyButton>

          <MySelect />
        </div>

        <span className={s.result}>{cardsData.length} результата</span>
      </header>

      <ul className={s.card__list}>
        {cardsData.map((card) => (
          <li className={s.card__item} key={card.id}>
            <Link href={`/courses/${card.id}`} className={s.card__link}>
              <div className={s.card__content}>
                <div className={s.card__image}>
                  <Image
                    src={
                      "https://img.freepik.com/premium-photo/word-design-written-top-colorful-geometric-3d-shapes_2227-1663.jpg"
                    }
                    alt="сard image"
                    width={300}
                    height={200}
                  />

                  <div className={s.blackout}>
                    <span>Нажмите</span>
                  </div>
                </div>

                <ul className={s.content__list}>
                  <li>
                    <h3>{card.name}</h3>
                  </li>
                  <li>
                    <p className={s.card__desciption}>{card.description}</p>
                  </li>
                  <li>
                    <p className={s.card__creator}>{card.creator}</p>
                  </li>
                  <li>
                    <pre>{card.price} </pre>
                    <span onClick={(event) => handleClick(event, card.price)}>
                      <Rating value={card.price} onChange={handleChange} />
                    </span>
                  </li>
                  <li>
                    <p className={s.card__size}>{card.price}</p>
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
}
