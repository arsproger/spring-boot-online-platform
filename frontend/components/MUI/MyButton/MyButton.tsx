import React, { FC, ReactNode, useRef } from "react";
import Button, { ButtonProps } from "antd/lib/button";
import s from "./MyButton.module.scss";

interface IMyButtonProps extends ButtonProps {
  children: ReactNode;
  background?: string;
  hoverBackground?: string;
}

const MyButton: FC<IMyButtonProps> = ({
  children,
  background,
  hoverBackground,
  type,
  icon,
  loading,
  ghost,
  onClick,
  ...props
}) => {
  // С помощью useRef получаем элемента
  const spanElement = useRef<HTMLSpanElement>(null);

  // Функция - при входе курсора
  const handleMouseEnter = () => {
    if (spanElement.current) {
      spanElement.current.style.height = "100%";
      spanElement.current.style.top = "auto";
      spanElement.current.style.bottom = "0";
    }
  };

  // Функция - при выходе курсора
  const handleMouseLeave = () => {
    if (spanElement.current) {
      spanElement.current.style.height = "0";
      spanElement.current.style.top = "0";
    }
  };

  return (
    <Button
      className={s.myButton}
      style={{ background }}
      type={type}
      htmlType="submit"
      icon={icon}
      loading={loading}
      onClick={onClick}
      onMouseEnter={handleMouseEnter}
      onMouseLeave={handleMouseLeave}
      {...props}
    >
      {children}

      <span
        className={s.myButton__hover}
        style={{ background: hoverBackground }}
        ref={spanElement}
      ></span>
    </Button>
  );
};

export default MyButton;
