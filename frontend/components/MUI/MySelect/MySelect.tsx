import React from "react";
import s from "./MySelect.module.scss";

import { Select } from "antd";

const MySelect = () => {
  const handleChange = (value: string) => {
    console.log(`selected ${value}`);
  };
  return (
    <Select
      className={s.mySelect}
      defaultValue="Select"
      style={{ width: 300 }}
      onChange={handleChange}
      options={[
        { value: "rating", label: "С наивысшим рейтингом" },
        { value: "price", label: "По цене" },
      ]}
    />
  );
};

export default MySelect;
