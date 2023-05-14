import React, { FC, useEffect, useRef, useState } from "react";
import s from "./Statistics.module.scss";

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
        <>
          <div className={s.statistics__item}>
            <span className={`${s.__num} ${s.__num1}`}></span>
            <p>теоретических и практических курсов для разного уровня</p>
          </div>

          <div className={s.statistics__item}>
            <span className={`${s.__num} ${s.__num1}`}></span>
            <p>теоретических и практических курсов для разного уровня</p>
          </div>

          <div className={s.statistics__item}>
            <span className={`${s.__num} ${s.__num1}`}></span>
            <p>теоретических и практических курсов для разного уровня</p>
          </div>

          <div className={s.statistics__item}>
            <span className={`${s.__num} ${s.__num1}`}></span>
            <p>теоретических и практических курсов для разного уровня</p>
          </div>
        </>
      )}
    </section>
  );
};

export default Statistics;
