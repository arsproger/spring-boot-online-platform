import React, { FC, useEffect, useRef, useState } from "react";
import s from "./Statistics.module.scss";

import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper";
import "swiper/css";
import "swiper/css/pagination";

const Statistics: FC = () => {
  const ref = useRef<HTMLTableSectionElement>(null);
  const [inView, setInView] = useState<boolean>(false);

  useEffect(() => {
    const handleScroll = () => {
      const { innerHeight, pageYOffset } = window;
      if (
        ref.current &&
        ref.current.offsetTop <= innerHeight + pageYOffset - 50
      ) {
        setInView(true);
      } else {
        setInView(false);
      }
    };

    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, [ref]);

  return (
    <section className={s.statistics} ref={ref}>
      {inView && (
        <Swiper
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
          grabCursor={true}
          modules={[Pagination]}
          breakpoints={{
            425: {
              slidesPerView: 1,
            },
            768: {
              slidesPerView: 2,
            },
            1024: {
              slidesPerView: 3,
            },
            1440: {
              slidesPerView: 4,
            },
          }}
          className={s.statisticsSlider}
        >
          <SwiperSlide>
            <div className={s.statistics__item}>
              <span className={`${s.__num} ${s.__num1}`}></span>
              <p>теоретических и практических курсов для разного уровня</p>
            </div>
          </SwiperSlide>

          <SwiperSlide>
            <div className={s.statistics__item}>
              <span className={`${s.__num} ${s.__num1}`}></span>
              <p>теоретических и практических курсов для разного уровня</p>
            </div>
          </SwiperSlide>

          <SwiperSlide>
            <div className={s.statistics__item}>
              <span className={`${s.__num} ${s.__num1}`}></span>
              <p>теоретических и практических курсов для разного уровня</p>
            </div>
          </SwiperSlide>

          <SwiperSlide>
            <div className={s.statistics__item} id="categories">
              <span className={`${s.__num} ${s.__num1}`}></span>
              <p>теоретических и практических курсов для разного уровня</p>
            </div>
          </SwiperSlide>
        </Swiper>
      )}
    </section>
  );
};

export default Statistics;
