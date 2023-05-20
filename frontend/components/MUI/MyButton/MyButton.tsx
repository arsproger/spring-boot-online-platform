import React, { FC, ReactNode } from "react";
import s from "./MyButton.module.scss";

import { Button } from "antd";

interface MyButtonProps {
  children: ReactNode;
  onClick?: () => void;
}

const MyButton: FC<MyButtonProps> = ({ children, onClick, ...props }) => (
  <Button className={s.myButton} onClick={onClick} {...props} type="primary">
    {children}
  </Button>
);

export default MyButton;
