import React, { FC } from "react";
import s from "./Categories.module.scss";

const Categories: FC = () => {
  return (
    <div className={s.categories}>
      <div className={s.categories__item}></div>
    </div>
  );
};

export default Categories;
