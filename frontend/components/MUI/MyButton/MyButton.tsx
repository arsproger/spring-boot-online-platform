import React, { FC, PropsWithChildren } from "react";
import { Button } from "antd";
import s from "./MyButton.module.scss";

const MyButton: FC<PropsWithChildren> = ({ children, ...props }) => (
  <Button {...props} type="primary">{children}</Button>
);

export default MyButton;