import React, { FC, useEffect, useState } from "react";
import s from "./design.module.scss";
import axios from "axios";

import Image from "next/image";
import Link from "next/link";
import { cards, ICards } from "../../constants/cardData";

import Rating from "@/components/Rating/Rating";
import MyButton from "@/components/MUI/MyButton/MyButton";
import { Select } from "antd";
import { Option } from "antd/es/mentions";
import MySelect from "@/components/MUI/MySelect/MySelect";

interface IData {
  name: string
  description: string
  price: number
  language: string
}
const Design: FC = () => {
  const [cardData, setCardData] = useState<ICards[]>(cards);
  const [data, setData] = useState<IData[]>([]);

  
  const [rating, setRating] = useState<number>(0);

  const handleChange = (newRating: number) => {
    setRating(newRating);
  };

  const handleClick = (event: any, id: number) => {
    event.preventDefault();
    setCardData(
      cardData.map((card) =>
        card.id === id ? { ...card, rating: rating } : card
      )
    );
  };

  const handle = async () => {
    const BASE_URL = "http://localhost:8080/course";
    try {
      const response = await axios.get(BASE_URL);

      setData(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    handle();
  }, []);

  return (
    <section className={s.card}>
      <h2 className={s.pageTitle}>Все курсы по теме "Дизайн"</h2>

      <header className={s.card__header}>
        <div className={s.filteredButton}>
          <MyButton>Фильтировать</MyButton>

          <MySelect />
        </div>

        <span className={s.result}>{cardData.length} результата</span>
      </header>

      <ul className={s.card__list}>
        {data.map((card) => (
          <li className={s.card__item}>
            <Link href="" className={s.card__link}>
              <div className={s.card__content}>
                <div className={s.card__image}>
                  <Image
                    src={"https://img.freepik.com/premium-photo/word-design-written-top-colorful-geometric-3d-shapes_2227-1663.jpg"}
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
                    <h3>{card.name}</h3>
                  </li>
                  <li>
                    <p className={s.card__desciption}>{card.description}</p>
                  </li>
                  <li>
                    <p className={s.card__creator}>{card.language}</p>
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
};

export default Design;
