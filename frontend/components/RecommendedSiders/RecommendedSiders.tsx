import React, { FC } from "react";
import { Swiper, SwiperSlide } from "swiper/react";

import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";

import { Navigation, Pagination, Autoplay, Keyboard } from "swiper";
import MyButton from "../MUI/MyButton/MyButton";

const RecommendedSiders: FC = () => {
  return (
    <Swiper
      navigation={true}
      pagination={{
        clickable: true,
      }}
      autoplay={{
        delay: 5000,
        disableOnInteraction: false,
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
        <div className="mySwiper__content">
          <div className="mySwiper__info">
            <h1>
              <span>Курс</span> Java
            </h1>
            <p>
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsam
              sequi repellendus voluptate ab perferendis fuga sit iste mollitia
              id. Magnam dignissimos aut, officiis obcaecati voluptas aspernatur
              impedit ullam voluptates ab.
            </p>
            <MyButton>Перейти</MyButton>
          </div>
          <img src="./slideFirst.jpg" alt="Slide image" />
        </div>
      </SwiperSlide>
      <SwiperSlide>
        <div className="mySwiper__content">
          <div className="mySwiper__info">
            <h1>
              <span>Курс</span> Java
            </h1>
            <p>
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsam
              sequi repellendus voluptate ab perferendis fuga sit iste mollitia
              id. Magnam dignissimos aut, officiis obcaecati voluptas aspernatur
              impedit ullam voluptates ab.
            </p>
          </div>
          <img src="./slideSecond.png" alt="Slide image" />
        </div>
      </SwiperSlide>
      <SwiperSlide>
        <div className="mySwiper__content">
          <div className="mySwiper__info">
            <h1>
              <span>Курс</span> Java
            </h1>
            <p>
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsam
              sequi repellendus voluptate ab perferendis fuga sit iste mollitia
              id. Magnam dignissimos aut, officiis obcaecati voluptas aspernatur
              impedit ullam voluptates ab.
            </p>
          </div>
          <img src="./slideFirst.jpg" alt="Slide image" />
        </div>
      </SwiperSlide>
      <SwiperSlide>
        <div className="mySwiper__content">
          <div className="mySwiper__info">
            <h1>
              <span>Курс</span> Java
            </h1>
            <p>
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsam
              sequi repellendus voluptate ab perferendis fuga sit iste mollitia
              id. Magnam dignissimos aut, officiis obcaecati voluptas aspernatur
              impedit ullam voluptates ab.
            </p>
          </div>
          <img src="./slideSecond.png" alt="Slide image" />
        </div>
      </SwiperSlide>
    </Swiper>
  );
};

export default RecommendedSiders;
