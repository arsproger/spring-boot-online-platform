import React, { FC } from "react";
import s from "./RecommendedSiders.module.scss";

import Image from "next/image";
import { Swiper, SwiperSlide } from "swiper/react";
import { Navigation, Pagination, Autoplay, Keyboard } from "swiper";
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";

import MyButton from "../MUI/MyButton/MyButton";
import slideFirst from "../../public/slideFirst.jpg";
import slideSecond from "../../public/slideSecond.png";

const RecommendedSiders: FC = () => {
  return (
    <Swiper
      speed={2500}
      autoplay={{
        delay: 2500,
        disableOnInteraction: false,
      }}
      navigation={true}
      pagination={{
        clickable: true,
      }}
      keyboard={{
        enabled: true,
      }}
      loop={true}
      grabCursor={true}
      modules={[Navigation, Pagination, Autoplay, Keyboard]}
      className="mySwiper"
      id="recommendations"
    >
      <SwiperSlide>
        <div className={s.mySwiper__content}>
          <div className={s.mySwiper__info}>
            <h2>
              <span>Курс</span> Java
            </h2>
            <p>
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsam
              sequi repellendus voluptate ab perferendis fuga sit iste mollitia
              id. Magnam dignissimos aut, officiis obcaecati voluptas aspernatur
              impedit ullam voluptates ab.
            </p>
            <MyButton>Перейти</MyButton>
          </div>
          <Image src={slideFirst} alt="slide image" />
        </div>
      </SwiperSlide>

      <SwiperSlide>
        <div className={s.mySwiper__content}>
          <div className={s.mySwiper__info}>
            <h2>
              <span>Курс</span> Java
            </h2>
            <p>
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsam
              sequi repellendus voluptate ab perferendis fuga sit iste mollitia
              id. Magnam dignissimos aut, officiis obcaecati voluptas aspernatur
              impedit ullam voluptates ab.
            </p>
            <MyButton>Перейти</MyButton>
          </div>
          <Image src={slideSecond} alt="slide image" />
        </div>
      </SwiperSlide>

      <SwiperSlide>
        <div className={s.mySwiper__content}>
          <div className={s.mySwiper__info}>
            <h2>
              <span>Курс</span> Java
            </h2>
            <p>
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsam
              sequi repellendus voluptate ab perferendis fuga sit iste mollitia
              id. Magnam dignissimos aut, officiis obcaecati voluptas aspernatur
              impedit ullam voluptates ab.
            </p>
            <MyButton>Перейти</MyButton>
          </div>
          <Image src={slideFirst} alt="slide image" />
        </div>
      </SwiperSlide>

      <SwiperSlide>
        <div className={s.mySwiper__content}>
          <div className={s.mySwiper__info}>
            <h2>
              <span>Курс</span> Java
            </h2>
            <p>
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsam
              sequi repellendus voluptate ab perferendis fuga sit iste mollitia
              id. Magnam dignissimos aut, officiis obcaecati voluptas aspernatur
              impedit ullam voluptates ab.
            </p>
            <MyButton>Перейти</MyButton>
          </div>
          <Image src={slideSecond} alt="slide image" />
        </div>
      </SwiperSlide>
    </Swiper>
  );
};

export default RecommendedSiders;
